package com.mist.consumer.service.impl;

import com.mist.consumer.service.ConsumerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    @Value("${server.port}")
    private String port;

    @SneakyThrows
    @Override
    public String getServerAddr() {
        log.info("in service");
        return Inet4Address.getLocalHost().getHostAddress() + ":" + port;
    }
}
