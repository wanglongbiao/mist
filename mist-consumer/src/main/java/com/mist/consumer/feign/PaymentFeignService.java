package com.mist.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mist-payment", path = "/payment")
public interface PaymentFeignService {
    @RequestMapping("/info")
    String getIp();
}