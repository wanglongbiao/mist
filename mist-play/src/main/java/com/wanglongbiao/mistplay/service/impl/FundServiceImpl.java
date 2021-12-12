package com.wanglongbiao.mistplay.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements FundService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final FundMapper fundMapper;

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
            TimeUnit.MILLISECONDS.sleep(1234);
            log.info("request page {}", i);
            EasyMoneyFund fundData = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EasyMoneyFund.class, fundCode, startDate, i, pageSize).getBody();
            assert fundData != null;
            list.addAll(fundData.getData().getLsjzList());
        }
        Collections.reverse(list);
        List<Fund> fundList = new ArrayList<>();
        list.forEach(data -> fundList.add(Fund.builder()
                .fundCode(fundCode)
                .fundName("天弘沪深300ETF联接C")
                .netValue(data.getDwjz())
                .accumulatedValue(data.getLjjz())
                .growthRate(data.getJzzzl())
                .netDate(LocalDate.parse(data.getFsrq())).build()));
        log.info("finished, size {}", fundList.size());
        saveBatch(fundList);
        return fundList;
    }

    public static void main(String[] args) {
//        new FundServiceImpl().queryNetValueListByNoAndStartDate("005918", "2010-01-01");
    }

    @Override
    public void calculateByNo(String fundCode, String startDate) {
        List<Fund> list = list(Wrappers.lambdaQuery(Fund.class).eq(Fund::getFundCode, fundCode).ge(Fund::getNetDate, LocalDate.parse(startDate)));
        double original = 10000; // 假设有 1w 本金
        double capital = original;
        double stake = 0;
        int up = 0, down = 0;
        for (Fund fund : list) {
            if (capital < 0) {
                log.info("本金用完，game over");
//                break;
            }
            double growthRate = fund.getGrowthRate();
            double accumulatedValue = fund.getAccumulatedValue();
            if (growthRate < 0) {
                down++;
                double buyIn = growthRate * 200 * -1; // 买入多少元 = 降幅 x 基数
                stake += (buyIn / accumulatedValue); // 计算买入的份额
                capital -= buyIn; // 从本金扣除
                log.info("{}, {}%, 买入 {}$, 余额 {}, 持有份额 {}, 总市值：{}", fund.getNetDate(), growthRate, buyIn, capital, stake, stake * accumulatedValue + capital);
            } else if (growthRate > 0) {
                up++;
//                if(up != 0) continue;
                double soldOut = growthRate * 100; // 卖出多少钱的
                double soldOutStake = soldOut / accumulatedValue; // 卖出多少份
                if (stake > soldOutStake) {
                    stake -= soldOutStake;
                    capital += soldOut;
                    log.info("{}, +{}%, 卖出 {}$, 余额 {}, 持有份额 {}, 总市值：{}", fund.getNetDate(), growthRate, soldOut, capital, stake, stake * accumulatedValue + capital);
                }
            }
        }
        double finalSum = stake * list.get(list.size() - 1).getAccumulatedValue() + capital;
        log.info("game over, 收益：{}, 收益率：{}", finalSum, (finalSum - original) / original);
        log.info("区间上涨次数： {}, 下跌次数： {}", up, down);
    }

    @Override
    public void noticeEstimated() {

    }


}
