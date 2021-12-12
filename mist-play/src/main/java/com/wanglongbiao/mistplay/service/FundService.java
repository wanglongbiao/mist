package com.wanglongbiao.mistplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanglongbiao.mistplay.entity.Fund;

import java.util.List;

/**
 * 基金查询、模拟计算服务接口
 */
public interface FundService extends IService<Fund> {
    /**
     * 查询某个基金的历史净值
     *
     * @param fundNo    基金编码
     * @param startDate 开始日期, yyyy-MM-dd
     * @return 基金每天的净值
     */
    List<?> queryNetValueListByNoAndStartDate(String fundNo, String startDate);

    /**
     * 根据基金编码，模拟计算投资收益
     *
     * @param fundNo    基金编码
     * @param startDate 开始日期, yyyy-MM-dd
     */
    void calculateByNo(String fundNo, String startDate);

    /**
     * 根据用户关注的 etf 估算净值，计算出跌幅、涨幅最大的几个，推送给用户
     */
    void noticeEstimated();
}
