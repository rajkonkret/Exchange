package com.raj.Exchange.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gold {
    @JsonProperty("data")
    private String data;
    @JsonProperty("cena")
    private Double cena;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("cena")
    public Double getCena() {
        return cena;
    }

    @JsonProperty("cena")
    public void setCena(Double cena) {
        this.cena = cena;
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
