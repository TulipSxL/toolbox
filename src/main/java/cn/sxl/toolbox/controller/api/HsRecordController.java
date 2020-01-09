package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.HsRecord;
import cn.sxl.toolbox.service.HsRecordService;
import cn.sxl.toolbox.vo.HsRecordVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 12:16
 */

@RestController
@RequestMapping("/api/hs_record")
@Slf4j
public class HsRecordController {

    private final HsRecordService hsRecordService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public HsRecordController(HsRecordService hsRecordService) {
        this.hsRecordService = hsRecordService;
    }

    @GetMapping("")
    public ResponseEntity<List<HsRecordVO>> getAllHsRecord() {

        List<HsRecordVO> hsRecordVoList = Lists.newArrayList();
        List<Date> dateList = hsRecordService.getAllDate();

        for (Date date : dateList) {
            List<HsRecord> hsRecordList = hsRecordService.getHsRecordByDate(date);

            HsRecordVO hsRecordVO = new HsRecordVO();
            hsRecordVO.setDate(dateFormat.format(date));
            hsRecordVO.setRecords(hsRecordList);

            hsRecordVoList.add(hsRecordVO);
        }

        return ResponseEntity.ok(hsRecordVoList);
    }

    @PostMapping("")
    public ResponseEntity<HsRecord> addHsRecord(@RequestBody HsRecord hearthStoneRecord) {
        log.info("========REQUEST BODY:" + hearthStoneRecord);

        if (hearthStoneRecord.getDate() == null) {
            hearthStoneRecord.setDate(new Date());
        }

        HsRecord hsRecord = hsRecordService.addHsRecord(hearthStoneRecord);

        return ResponseEntity.ok(hsRecord);
    }

    @PutMapping("")
    public ResponseEntity<HsRecord> modifyRecord(@RequestBody HsRecord hearthStoneRecord) {
        HsRecord hsRecord = hsRecordService.modifyHsRecord(hearthStoneRecord);

        return ResponseEntity.ok(hsRecord);
    }

    @DeleteMapping("/{hsRecordId}")
    public ResponseEntity<Integer> removeRecord(@PathVariable int hsRecordId) {
        hsRecordService.deleteHsRecord(hsRecordId);

        return ResponseEntity.ok(hsRecordId);
    }
}
