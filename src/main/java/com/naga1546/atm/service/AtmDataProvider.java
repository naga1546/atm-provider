package com.naga1546.atm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naga1546.atm.model.Atm;
import com.naga1546.atm.model.Atms;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AtmDataProvider {
    private static final String ATM_URL = "https://www.ing.nl/api/locator/atms/";
    private Client client;
    private WebTarget target;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    private Atms allAtms;
    private AtomicLong lastRefreshTime;
    private boolean readFromRemote;

    public AtmDataProvider() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        lastRefreshTime = new AtomicLong();
        readFromRemote = true;
    }

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();
        target = client.target(ATM_URL);
        try {
            refreshAtms();
        } catch (IOException e) {
            log.error("Error initializing all Atms", e);
        }
    }

    public Atms getAtms() {
        return allAtms;
    }

    @Scheduled(fixedDelay = 60000L)
    public void refreshAtms() throws IOException {
        log.info("refreshAtms started");
        long lastRefreshTimeInMillis = lastRefreshTime.get();
        if (lastRefreshTimeInMillis < System.currentTimeMillis() - 1000L * 60 * 5 &&
            lastRefreshTime.compareAndSet(lastRefreshTimeInMillis, System.currentTimeMillis())) {
            log.info("Refreshing ATM List");
            try {
                allAtms = getAtmsInternal();
            } catch (IOException e) {
                lastRefreshTime.set(lastRefreshTimeInMillis);
                throw e;
            }
        }
    }

    private Atms getAtmsInternal() throws IOException {
        /*Atms atms = target
            .request(MediaType.APPLICATION_JSON)
            .get(Atms.class);*/ //TODO - Getting JSON Parse Exception with the API response as it is not in proper JSON format.
        if (readFromRemote) {
            log.info("Loading Atms from Remote");
            String response = restTemplate.getForObject(ATM_URL, String.class);
            response = response.substring(response.indexOf('['));
            List<Atm> atmList = objectMapper.readValue(response, new TypeReference<List<Atm>>() {
            });
            Atms atms = new Atms();
            atms.setAtms(atmList);
            log.info("atms size: {}", atms.getAtms().size());
            return atms;
        }
        log.info("Loading Atms from Local");
        Atms atms = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("atms.json"), Atms.class);
        log.info("atms size: {}", atms.getAtms().size());
        return atms;
    }

    public void printPulls() {
        log.info("Github: {}", client.target("https://api.github.com/json-api/json-api/pulls").request(MediaType.APPLICATION_JSON)
            .get(Map.class));
    }
}
