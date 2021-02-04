package com.naga1546.atm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Atms
{
    private List<Atm> atms;
    
    public List<Atm> getAtms() {
        if(atms == null) {
            atms = new ArrayList<>();
        }
        return Collections.unmodifiableList(atms);
    }
 
    public void setAtms(List<Atm> atms) {
        this.atms = atms;
    }
}
