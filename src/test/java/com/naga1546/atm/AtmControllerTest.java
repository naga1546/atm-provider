package com.naga1546.atm;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.naga1546.atm.controller.AtmController;
import com.naga1546.atm.model.Atms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmControllerTest {

    @Autowired
    AtmController atmController;

    @Test
    public void testCreatePlace() {
        ResponseEntity<Atms> atms = atmController.getAtms(null);
        assertEquals(2615, atms.getBody().getAtms().size());

        atms = atmController.getAtms("InvalidCityName");
        assertEquals(0, atms.getBody().getAtms().size());

        atms = atmController.getAtms("Ede");
        assertEquals(11, atms.getBody().getAtms().size());
    }

}
