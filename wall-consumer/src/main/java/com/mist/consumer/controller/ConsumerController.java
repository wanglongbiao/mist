package com.mist.consumer.controller;

import com.mist.consumer.service.ConsumerService;
import com.wanglongbiao.mist.entity.R;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {
//    private final PaymentFeignService paymentFeignService;

    @Resource
    private ConsumerService consumerService;

    @SneakyThrows
    @RequestMapping("/info")
    public R<String> info() {
        return R.ok(consumerService.getServerAddr());
    }
}
