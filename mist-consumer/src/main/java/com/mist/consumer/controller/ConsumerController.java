package com.mist.consumer.controller;

import com.mist.consumer.feign.PaymentFeignService;
import com.wanglongbiao.mist.entity.R;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {
    private final PaymentFeignService paymentFeignService;

    @RequestMapping("/info")
    public R<String> info() {
        return R.ok(paymentFeignService.getIp());
    }
}
