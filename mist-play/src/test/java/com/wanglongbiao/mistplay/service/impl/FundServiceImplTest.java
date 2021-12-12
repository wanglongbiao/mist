package com.wanglongbiao.mistplay.service.impl;

import com.wanglongbiao.mistplay.service.FundService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class FundServiceImplTest {
    @Autowired
    private FundService fundService;

    @Test
    public void test1() {
        log.info("start");
        fundService.queryNetValueListByNoAndStartDate("005918", "2018-01-01");
    }
}