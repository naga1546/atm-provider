package com.naga1546.atm.controller;

import javax.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.naga1546.atm.dao.AtmDAO;
import com.naga1546.atm.model.Atms;

@Slf4j
@RestController
@RequestMapping(path = "/atms")
public class AtmController {

    @Autowired
    private AtmDAO atmDao;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<Atms> getAtms(@QueryParam("city") String city) {
        try {
            if (StringUtils.isEmpty(city)) {
                return ResponseEntity.ok(atmDao.getAllAtms());
            } else {
                return ResponseEntity.ok(atmDao.getAtmsInCity(city));
            }
        } catch (Exception e) {
            log.error("Error while processing", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
