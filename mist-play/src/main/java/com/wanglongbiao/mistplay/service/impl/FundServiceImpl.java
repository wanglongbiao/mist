package com.wanglongbiao.mistplay.service.impl;

import com.wanglongbiao.mistplay.service.FundService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FundServiceImpl implements FundService {
    private RestTemplate restTemplate;
    @Override
    public void calculateByNo(String fundNo, String startDate) {

    }

    @Override
    public List<?> getByNoAndStartDate(String fundNo, String startDate) {
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18304845446397529727_1638195230442&fundCode=005918&pageIndex=1&pageSize=20&startDate=&endDate=&_=1638195268610";
//                    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18304845446397529727_1638195230442&fundCode=005918&pageIndex=2&pageSize=20&startDate=&endDate=&_=1638195604167
        return null;
    }

}
