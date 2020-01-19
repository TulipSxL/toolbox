package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.HsRecord;
import cn.sxl.toolbox.service.HsRecordService;
import cn.sxl.toolbox.vo.HsRecordVO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<List<HsRecordVO>> getAllHsRecord(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "beginDate", required = false) String beginDateStr,
                                                           @RequestParam(value = "endDate", required = false) String endDateStr)
            throws ParseException {
        List<HsRecordVO> hsRecordVoList = Lists.newArrayList();
        List<Date> dateList;
        int highestScore = hsRecordService.getMaxScore();

        if (Strings.isNullOrEmpty(name)) {
            dateList = getDateList(beginDateStr, endDateStr);

            for (Date date : dateList) {
                List<HsRecord> hsRecordList = hsRecordService.searchHsRecordByDate(date);

                setHsRecordVo(hsRecordVoList, highestScore, date, hsRecordList, false);
            }
        } else {
            dateList = getDateList(beginDateStr, endDateStr);

            for (Date date : dateList) {
                List<HsRecord> hsRecordList = hsRecordService.searchHsRecordByNameAndDate(name, date);

                setHsRecordVo(hsRecordVoList, highestScore, date, hsRecordList, true);
            }
        }

        return ResponseEntity.ok(hsRecordVoList);
    }

    private void setHsRecordVo(List<HsRecordVO> hsRecordVoList, int highestScore, Date date, List<HsRecord> hsRecordList, boolean isSearch) {

        if (hsRecordList.size() > 0) {
            HsRecordVO hsRecordVO = new HsRecordVO();
            hsRecordVO.setDate(dateFormat.format(date));
            hsRecordVO.setChangedScore(hsRecordService.computeChangedScore(hsRecordList, isSearch));
            hsRecordVO.setHighestScore(highestScore);
            hsRecordVO.setRecords(hsRecordList);
            hsRecordVoList.add(hsRecordVO);
        }

    }

    @PostMapping("")
    public ResponseEntity<HsRecord> addHsRecord(@RequestBody HsRecord hearthStoneRecord) {

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

    private List<Date> getDateList(String beginDateStr, String endDateStr) throws ParseException {
        List<Date> dateList;
        Date beginDate;
        Date endDate;

        if (Strings.isNullOrEmpty(beginDateStr)) {
            if (Strings.isNullOrEmpty(endDateStr)) {
                dateList = hsRecordService.searchDateBetween(null, null);
            } else {
                endDate = dateFormat.parse(endDateStr);
                dateList = hsRecordService.searchDateBetween(null, endDate);
            }
        } else {
            if (Strings.isNullOrEmpty(endDateStr)) {
                beginDate = dateFormat.parse(beginDateStr);
                dateList = hsRecordService.searchDateBetween(beginDate, null);
            } else {
                beginDate = dateFormat.parse(beginDateStr);
                endDate = dateFormat.parse(endDateStr);
                dateList = hsRecordService.searchDateBetween(beginDate, endDate);
            }
        }
        return dateList;
    }
}
