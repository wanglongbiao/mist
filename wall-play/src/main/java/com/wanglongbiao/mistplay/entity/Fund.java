package com.wanglongbiao.mistplay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("t_fund")
public class Fund {
    @TableId
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
