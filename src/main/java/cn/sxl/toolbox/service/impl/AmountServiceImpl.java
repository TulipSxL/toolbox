package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.Amount;
import cn.sxl.toolbox.repository.AmountRepository;
import cn.sxl.toolbox.service.AmountService;
import cn.sxl.toolbox.util.DateUtil;
import cn.sxl.toolbox.vo.AmountVO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:33
 */

@Service
@Slf4j
public class AmountServiceImpl implements AmountService {

    private final AmountRepository amountRepository;

    public AmountServiceImpl(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }


    @Override
    public List<AmountVO> getAllAmount() {
        List<AmountVO> amountVoList = Lists.newArrayList();

        List<String> yearList = amountRepository.findAllYears();

        for (String year : yearList) {
            AmountVO amountVO = new AmountVO();
            amountVO.setYear(year);
            amountVO.setAmountList(amountRepository.findAllByYear(year));

            amountVoList.add(amountVO);
        }

        return amountVoList;
    }

    @Override
    public Amount modifyAmount(Amount amount) {
        return amountRepository.saveAndFlush(amount);
    }

    @Override
    public Amount addAmount(Amount amount) {
        return amountRepository.saveAndFlush(amount);
    }

    @Override
    public void clearConsumptionAndUpdateAverage() {
        int remainDay = DateUtil.getRemainDays();
        Amount amount = getByYearAndMonth();

        double average = (amount.getTotal() - amount.getCost()) / remainDay;
        amount.setConsumption(0D);
        amount.setAverage(new BigDecimal(average).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        amountRepository.saveAndFlush(amount);

        log.info("remain day is {}, average amount is {}, amount is {}",
                remainDay, average, amount);
    }

    @Override
    public Amount recalculateAverage() {
        int remainDay = DateUtil.getRemainDays();
        Amount amount = getByYearAndMonth();

        double average = (amount.getTotal() - amount.getCost() + amount.getConsumption()) / remainDay;
        amount.setAverage(new BigDecimal(average).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        log.info("remain day is {}, average amount is {}, amount is {}",
                remainDay, average, amount);

        return amountRepository.saveAndFlush(amount);
    }

    @Override
    public Amount getByYearAndMonth(String year, String month) {
        return amountRepository.findByYearAndMonth(year, month);
    }

    @Override
    public Amount getByYearAndMonth() {
        String year = DateUtil.convertYearToString();
        String month = DateUtil.convertMonthToString();

        return amountRepository.findByYearAndMonth(year, month);
    }
}
