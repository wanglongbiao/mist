/* Copyright 2021 freecodeformat.com */
package com.wanglongbiao.mistplay.entity.tiantian;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class Data {

    @JsonProperty("LSJZList")
    private List<Lsjzlist> lsjzList;
    @JsonProperty("FundType")
    private String fundType;
    @JsonProperty("SYType")
    private String syType;
    @JsonProperty("isNewType")
    private boolean isNewType;
    @JsonProperty("Feature")
    private String feature;
}