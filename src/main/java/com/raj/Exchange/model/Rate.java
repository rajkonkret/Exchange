package com.raj.Exchange.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
//    @JsonProperty("no")
//    private String no;
    @JsonProperty("code")
    private String code;
//    @JsonProperty("effectiveDate")
//    private String effectiveDate;
//    @JsonProperty("bid")
//    private Double bid;
//    @JsonProperty("ask")
//    private Double ask;
    @JsonProperty("mid")
    private Double mid;

    //    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

//    @JsonProperty("no")
//    public String getNo() {
//        return no;
//    }
//
//    @JsonProperty("no")
//    public void setNo(String no) {
//        this.no = no;
//    }
//
//    @JsonProperty("effectiveDate")
//    public String getEffectiveDate() {
//        return effectiveDate;
//    }
//
//    @JsonProperty("effectiveDate")
//    public void setEffectiveDate(String effectiveDate) {
//        this.effectiveDate = effectiveDate;
//    }

//    @JsonProperty("bid")
//    public Double getBid() {
//        return bid;
//    }
//
//    @JsonProperty("bid")
//    public void setBid(Double bid) {
//        this.bid = bid;
//    }
//
//    @JsonProperty("ask")
//    public Double getAsk() {
//        return ask;
//    }
//
//    @JsonProperty("ask")
//    public void setAsk(Double ask) {
//        this.ask = ask;
//    }

    @JsonProperty("mid")
    public Double getMid() {
        return mid;
    }

    @JsonProperty("mid")
    public void setMid(Double mid) {
        this.mid = mid;
    }

//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
}

