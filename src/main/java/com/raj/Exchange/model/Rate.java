package com.raj.Exchange.model;


import com.fasterxml.jackson.annotation.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("code")
    private String code;

    @JsonProperty("mid")
    private Double mid;


   // @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

   // @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

  //  @JsonProperty("code")
    public String getCode() {
        return code;
    }

   // @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

   // @JsonProperty("mid")
    public Double getMid() {
        return mid;
    }

   // @JsonProperty("mid")
    public void setMid(Double mid) {
        this.mid = mid;
    }

}