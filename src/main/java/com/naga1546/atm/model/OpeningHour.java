package com.naga1546.atm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dayOfWeek",
    "hours"
})
public class OpeningHour {

    @JsonProperty("dayOfWeek")
    private Integer dayOfWeek;
    @JsonProperty("hours")
    private List<Hour> hours = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dayOfWeek")
    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    @JsonProperty("dayOfWeek")
    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @JsonProperty("hours")
    public List<Hour> getHours() {
        return hours;
    }

    @JsonProperty("hours")
    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
