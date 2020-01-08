package cn.sxl.toolbox.service;

import cn.sxl.toolbox.entity.Amount;
import cn.sxl.toolbox.vo.AmountVO;

import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:33
 */
public interface AmountService {

    /**
     * 按照年份获取所有数据
     *
     * @return 列表
     */
    List<AmountVO> getAllAmount();

    /**
     * 修改额度
     *
     * @param amount 额度
     * @return 修改后的额度
     */
    Amount modifyAmount(Amount amount);

    /**
     * 新增一个Amount
     *
     * @param amount 新增数据
     * @return 增加后的数据
     */
    Amount addAmount(Amount amount);

    /**
     * 清除当日消费
     */
    void clearConsumptionAndUpdateAverage();

    /**
     * 重置当日消费和平均额度
     * @return 重置后
     */
    Amount recalculateAverage();

    /**
     *  根据年份和月份获取
     * @param year 年份
     * @param month 月份
     * @return 实例
     */
    Amount getByYearAndMonth(String year, String month);

    /**
     * 使用当前月份和年份获取
     * @return 实例
     */
    Amount getByYearAndMonth();
}
