package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.Amount;
import cn.sxl.toolbox.repository.AmountRepository;
import cn.sxl.toolbox.service.AmountService;
import cn.sxl.toolbox.vo.AmountVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:33
 */
@Service
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


}
