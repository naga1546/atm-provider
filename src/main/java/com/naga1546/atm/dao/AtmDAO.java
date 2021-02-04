package com.naga1546.atm.dao;

import com.naga1546.atm.model.Atm;
import com.naga1546.atm.model.Atms;
import com.naga1546.atm.service.AtmDataProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AtmDAO {

    @Autowired
    AtmDataProvider atmDataProvider;

    public Atms getAllAtms() throws Exception {
        //return list;
        return atmDataProvider.getAtms();
    }

    public Atms getAtmsInCity(String city) throws Exception {
        Atms atms = atmDataProvider.getAtms();
        List<Atm> atmsInCity = atms.getAtms().stream()
            .filter((atm) -> city.equalsIgnoreCase(atm.getAddress().getCity())).collect(
                Collectors.toList());
        Atms result = new Atms();
        result.setAtms(atmsInCity);
        return result;
    }
}
