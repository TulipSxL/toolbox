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
     * @return 列表
     */
    List<AmountVO> getAllAmount();

    /**
     * 修改额度
     * @param amount 额度
     * @return 修改后的额度
     */
    Amount modifyAmount(Amount amount);

    /**
     * 新增一个Amount
     * @param amount 新增数据
     * @return 增加后的数据
     */
    Amount addAmount(Amount amount);
}
