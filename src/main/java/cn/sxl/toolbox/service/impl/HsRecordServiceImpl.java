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
    public List<HsRecord> getHsRecordByDate(Date date) {
        return hsRecordRepository.findHearthStoneRecordByDate(date);
    }

    @Override
    public Integer computeChangedScore(Date date) {
        List<HsRecord> hsRecordList = getHsRecordByDate(date);
        int hsRecordSize = hsRecordList.size() - 1;

        if (hsRecordList.size() == 1) {
            return hsRecordList.get(0).getIncrement();
        }

        return hsRecordList.get(0).getScore() - hsRecordList.get(hsRecordSize).getScore();
    }
}
