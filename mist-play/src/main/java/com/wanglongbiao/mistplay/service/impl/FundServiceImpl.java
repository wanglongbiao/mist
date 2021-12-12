package com.wanglongbiao.mistplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanglongbiao.mistplay.entity.Fund;
import com.wanglongbiao.mistplay.entity.tiantian.EasyMoneyFund;
import com.wanglongbiao.mistplay.entity.tiantian.Lsjzlist;
import com.wanglongbiao.mistplay.mapper.FundMapper;
import com.wanglongbiao.mistplay.service.FundService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements FundService {
    private final RestTemplate restTemplate = new RestTemplate();

    @SneakyThrows
    @Override
    public List<?> queryNetValueListByNoAndStartDate(String fundCode, String startDate) {
        long currentTimeMillis = System.currentTimeMillis();
        int pageIndex = 1;
        int pageSize = 20;
        String url = "http://api.fund.eastmoney.com/f10/lsjz?fundCode={1}&startDate={2}&pageIndex={3}&pageSize={4}&endDate=&_=" + currentTimeMillis;
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
        ResponseEntity<EasyMoneyFund> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EasyMoneyFund.class, fundCode, startDate, pageIndex, pageSize);
        EasyMoneyFund body = exchange.getBody();
        assert body != null;
        List<Lsjzlist> list = body.getData().getLsjzList();
        int totalCount = body.getTotalCount();
        for (int i = 2; i <= totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1); i++) {
            TimeUnit.MILLISECONDS.sleep(567);
            log.info("request page {}", i);
            EasyMoneyFund fundData = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EasyMoneyFund.class, fundCode, startDate, pageIndex, pageSize).getBody();
            assert fundData != null;
            list.addAll(fundData.getData().getLsjzList());
        }
        Collections.reverse(list);
        List<Fund> fundList = new ArrayList<>();
        list.forEach(data -> fundList.add(Fund.builder().fundCode(fundCode).fundName("天弘沪深300ETF联接C")
                .netValue(data.getDwjz()).growthRate(data.getJzzzl()).netDate(LocalDate.parse(data.getFsrq())).build()));
        log.info("finished, size {}", fundList.size());
        saveBatch(fundList);
        return fundList;
    }

    public static void main(String[] args) {
        new FundServiceImpl().queryNetValueListByNoAndStartDate("005918", "2010-01-01");
    }

    @Override
    public void calculateByNo(String fundNo, String startDate) {

    }

    @Override
    public void noticeEstimated() {

    }


}
