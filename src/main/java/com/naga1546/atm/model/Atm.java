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
    "address",
    "distance",
    "openingHours",
    "functionality",
    "type"
})
public class Atm {

    @JsonProperty("address")
    private Address address;
    @JsonProperty("distance")
    private Integer distance;
    @JsonProperty("openingHours")
    private List<OpeningHour> openingHours = null;
    @JsonProperty("functionality")
    private String functionality;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("distance")
    public Integer getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @JsonProperty("openingHours")
    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    @JsonProperty("openingHours")
    public void setOpeningHours(List<OpeningHour> openingHours) {
        this.openingHours = openingHours;
    }

    @JsonProperty("functionality")
    public String getFunctionality() {
        return functionality;
    }

    @JsonProperty("functionality")
    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
