package com.raj.Exchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ToChange {
    @JsonProperty("first")
    private String first;
    @JsonProperty("second")
    private String second;
    @JsonProperty("value")
    private Double value;

    @JsonProperty("first")
    public String getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(String first) {
        this.first = first;
    }

    @JsonProperty("second")
    public String getSecond() {
        return second;
    }

    @JsonProperty("second")
    public void setSecond(String second) {
        this.second = second;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }
}


