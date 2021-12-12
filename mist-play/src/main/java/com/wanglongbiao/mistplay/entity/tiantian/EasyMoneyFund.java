/* Copyright 2021 freecodeformat.com */
package com.wanglongbiao.mistplay.entity.tiantian;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class EasyMoneyFund {
    @JsonProperty("Data")
    private Data data;
    @JsonProperty("ErrCode")
    private int errCode;
    @JsonProperty("ErrMsg")
    private String errMsg;
    @JsonProperty("TotalCount")
    private int totalCount;
    @JsonProperty("Expansion")
    private String expansion;
    @JsonProperty("PageSize")
    private int pageSize;
    @JsonProperty("PageIndex")
    private int pageIndex;

}