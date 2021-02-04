package com.naga1546.atm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naga1546.atm.model.Atms;
import java.io.IOException;
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

@Slf4j
@Component
public class AtmDataProvider {
    private Client client;
    private WebTarget target;

    private Atms allAtms;
    private AtomicLong lastRefreshTime;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();
        target = client.target("https://www.ing.nl/api/locator/atms/");
        lastRefreshTime = new AtomicLong();
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
        // Note: Taking the data from a file as API call is not working.
        Atms atms = new ObjectMapper().readValue(getClass().getClassLoader().getResourceAsStream("atms.json"), Atms.class);
        log.info("atms: {}", atms);
        return atms;
    }

    public void printPulls() {
        log.info("Github: {}", client.target("https://api.github.com/json-api/json-api/pulls").request(MediaType.APPLICATION_JSON)
            .get(Map.class));
    }
}
