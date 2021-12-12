package com.wanglongbiao.mistplay.entity;

import java.time.LocalDate;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fund {
    private Long fundId;
    private String fundCode;
    private String fundName;
    private LocalDate netDate;
    private double netValue;
    private double accumulatedValue;
    private double growthRate;
    private Date createTime;
    private Date updateTime;
}
