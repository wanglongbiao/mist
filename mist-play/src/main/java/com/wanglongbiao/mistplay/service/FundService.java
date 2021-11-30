package com.wanglongbiao.mistplay.service;

import java.util.List;

public interface FundService {
    void calculateByNo(String fundNo, String startDate);

    List<?> getByNoAndStartDate(String fundNo, String startDate);
}
