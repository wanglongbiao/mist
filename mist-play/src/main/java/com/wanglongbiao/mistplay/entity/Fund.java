package com.wanglongbiao.mistplay.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Fund {
    private Long id;
    private String code;
    private LocalDate date;
    private double netAssetValue;
    private double accumulativeNetValue;
    private double dayOfGrowth;
}
