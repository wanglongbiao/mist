package com.wanglongbiao.mistplay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    /**
     * 计算那些连跌 n 次的股票在买入之后何时可以翻红
     */
    public void calculateDescend() {

    }
}
