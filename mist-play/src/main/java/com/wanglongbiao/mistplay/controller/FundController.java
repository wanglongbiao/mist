package com.wanglongbiao.mistplay.controller;

import com.wanglongbiao.mistplay.service.FundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fund")
@RequiredArgsConstructor
public class FundController {
    private final FundService fundService;
    /**
     * 根据基金代码，计算什么时候回本，什么时候收益 10%，什么时候回撤 10%，最大回撤，最大收益率
     */
    public void calculateByNo(String fundNo, String startDate){
        fundService.calculateByNo(fundNo, startDate);

    }
}
