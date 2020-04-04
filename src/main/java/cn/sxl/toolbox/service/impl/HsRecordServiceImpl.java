package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.HsRecord;
import cn.sxl.toolbox.repository.HsRecordRepository;
import cn.sxl.toolbox.service.HsRecordService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 12:16
 */

@Service
public class HsRecordServiceImpl implements HsRecordService {

    private final HsRecordRepository hsRecordRepository;

    public HsRecordServiceImpl(HsRecordRepository hsRecordRepository) {
        this.hsRecordRepository = hsRecordRepository;
    }

    @Override
    public List<HsRecord> getAllHsRecord() {
        return hsRecordRepository.findAll();
    }

    @Override
    public HsRecord addHsRecord(HsRecord hsRecord) {
        return hsRecordRepository.saveAndFlush(hsRecord);
    }

    @Override
    public HsRecord modifyHsRecord(HsRecord hsRecord) {
        return hsRecordRepository.saveAndFlush(hsRecord);
    }

    @Override
    public void deleteHsRecord(int hsRecordId) {
        hsRecordRepository.deleteById(hsRecordId);
    }

    @Override
    public List<Date> getAllDate() {
        return hsRecordRepository.findAllDate();
    }

    @Override
    public List<HsRecord> searchHsRecordByDate(Date date) {
        return hsRecordRepository.findHesRecordByDate(date);
    }

    @Override
    public Integer computeChangedScore(List<HsRecord> hsRecordList, Boolean isSearch) {
        int hsRecordSize = hsRecordList.size() - 1;
        int changedScore = 0;

        if (hsRecordList.size() == 1) {
            return hsRecordList.get(0).getIncrement();
        }

        if (isSearch) {
            for (HsRecord hsRecord : hsRecordList) {
                changedScore += hsRecord.getIncrement();
            }
        } else {
            changedScore = hsRecordList.get(0).getScore() - hsRecordList.get(hsRecordSize).getScore() + hsRecordList.get(hsRecordSize).getIncrement();
        }

        return changedScore;
    }

    @Override
    public List<HsRecord> searchHsRecordByNameAndDate(String name, Date date) {
        return hsRecordRepository.findHsRecordByNameAndDate(name, date);
    }

    @Override
    public List<Date> searchDateBetween(Date beginDate, Date endDate) {

        if (beginDate == null) {
            List<Date> dateList = getAllDate();
            beginDate = dateList.get(dateList.size() - 1);
        }

        if (endDate == null) {
            endDate = new Date();
        }

        return hsRecordRepository.findDateBetween(beginDate, endDate);
    }

    @Override
    public Integer getMaxScore() {
        return hsRecordRepository.findMaxScore();
    }
}
