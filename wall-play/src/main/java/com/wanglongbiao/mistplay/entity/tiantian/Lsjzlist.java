/* Copyright 2021 freecodeformat.com */
package com.wanglongbiao.mistplay.entity.tiantian;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Lsjzlist {
    @JsonProperty("FSRQ")
    private String fsrq;
    @JsonProperty("DWJZ")
    private double dwjz;
    @JsonProperty("LJJZ")
    private double ljjz;
    @JsonProperty("SDATE")
    private String sdate;
    @JsonProperty("ACTUALSYI")
    private String actualsyi;
    @JsonProperty("NAVTYPE")
    private String navtype;
    @JsonProperty("JZZZL")
    private double jzzzl;
    @JsonProperty("SGZT")
    private String sgzt;
    @JsonProperty("SHZT")
    private String shzt;
    @JsonProperty("FHFCZ")
    private String fhfcz;
    @JsonProperty("FHFCBZ")
    private String fhfcbz;
    @JsonProperty("DTYPE")
    private String dtype;
    @JsonProperty("FHSP")
    private String fhsp;
}