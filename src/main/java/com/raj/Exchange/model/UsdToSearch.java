package com.raj.Exchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UsdToSearch {
    @JsonProperty("table")
    private String table;

    @JsonProperty("effectiveDate")
    private String effectiveDate;

    @JsonProperty("rates")
    private List<RateToSearch> rates = null;

    @JsonProperty("table")
    public String getTable() {
        return table;
    }

    @JsonProperty("table")
    public void setTable(String table) {
        this.table = table;
    }

    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    @JsonProperty("effectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @JsonProperty("rates")
    public List<RateToSearch> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(List<RateToSearch> rates) {
        this.rates = rates;
    }

}



