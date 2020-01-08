package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.Amount;
import cn.sxl.toolbox.enums.MonthEnum;
import cn.sxl.toolbox.service.AmountService;
import cn.sxl.toolbox.vo.AmountVO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:33
 */

@RestController
@RequestMapping("/api/amount")
public class AmountController {

    private final AmountService amountService;

    public AmountController(AmountService amountService) {
        this.amountService = amountService;
    }

    @GetMapping("")
    public ResponseEntity<List<AmountVO>> getAllAmount() {
        List<AmountVO> amountList = amountService.getAllAmount();

        return ResponseEntity.ok(amountList);
    }

    @GetMapping("/reset")
    public ResponseEntity<Amount> resetConsumptionAndAverage() {
        Amount amount = amountService.recalculateAverage();

        return ResponseEntity.ok(amount);
    }

    @PutMapping("")
    public ResponseEntity<Amount> modifyAmount(@RequestBody Amount amount) {
        Amount modifyAmount = amountService.modifyAmount(amount);

        return ResponseEntity.ok(modifyAmount);
    }

    @PostMapping("")
    public ResponseEntity<Amount> addAmount(@RequestBody Amount amount) {
        Amount addAmount = amountService.addAmount(amount);

        return ResponseEntity.ok(addAmount);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void addAmount() {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf(MonthEnum.values()[now.get(Calendar.MONTH)]);

        Amount amount = new Amount();
        amount.setYear(year);
        amount.setMonth(month);
        amount.setTotal(1500D);
        amount.setCost(0D);
        amount.setConsumption(0D);

        amountService.addAmount(amount);
    }

    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void clearConsumption() {
        amountService.clearConsumptionAndUpdateAverage();
    }
}
