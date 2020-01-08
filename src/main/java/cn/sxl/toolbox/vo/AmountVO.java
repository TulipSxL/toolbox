package cn.sxl.toolbox.vo;

import cn.sxl.toolbox.entity.Amount;
import lombok.Data;

import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:32
 */

@Data
public class AmountVO {

    List<Amount> amountList;
    private String year;
}
