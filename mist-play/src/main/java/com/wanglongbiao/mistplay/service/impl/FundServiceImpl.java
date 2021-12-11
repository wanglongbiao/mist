package com.wanglongbiao.mistplay.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wanglongbiao.mistplay.service.FundService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FundServiceImpl implements FundService {
    private final RestTemplate restTemplate = new RestTemplate();
//    private RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory()); // verified
//    private RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    @Override
    public List<?> queryNetValueListByNoAndStartDate(String fundNo, String startDate) {
        long currentTimeMillis = System.currentTimeMillis();
        log.info("time {}", currentTimeMillis);
        String url = "http://api.fund.eastmoney.com/f10/lsjz?fundCode=005918&pageIndex=45&pageSize=20&startDate=2018-05-01&endDate=&_=" + currentTimeMillis;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "api.fund.eastmoney.com");
        headers.set("Proxy-Connection", "keep-alive");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36");
        headers.set("DNT", "1");
        headers.set("Accept", "*/*");
        headers.set("Referer", "http://fundf10.eastmoney.com/");
        headers.set("Accept-Encoding", "gzip, deflate");
        headers.set("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-US;q=0.7,zh-HK;q=0.6");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, httpEntity);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        log.info("hi {} ", exchange.getBody());

        return null;
    }

    public static void main(String[] args) {
        new FundServiceImpl().queryNetValueListByNoAndStartDate("", "");
    }

    @Override
    public void calculateByNo(String fundNo, String startDate) {

    }

    @Override
    public void noticeEstimated() {

    }


}
