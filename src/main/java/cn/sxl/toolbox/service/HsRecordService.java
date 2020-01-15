package cn.sxl.toolbox.service;

import cn.sxl.toolbox.entity.HsRecord;

import java.util.Date;
import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 12:15
 */
public interface HsRecordService {

    /**
     *  获取所有战绩
     * @return 战绩列表
     */
    List<HsRecord> getAllHsRecord();

    /**
     * 添加一个新战绩
     * @param hsRecord 新战绩
     * @return 添加结果
     */
    HsRecord addHsRecord(HsRecord hsRecord);

    /**
     * 编辑战绩
     * @param hsRecord 战绩
     * @return 编辑结果
     */
    HsRecord modifyHsRecord(HsRecord hsRecord);

    /**
     * 删除战绩
     * @param hsRecordId 战绩ID
     */
    void deleteHsRecord(int hsRecordId);

    /**
     * 查询所有日期
     * @return 日期列表
     */
    List<Date> getAllDate();

    /**
     * 根据日期查询
     * @param date 日期
     * @return 查询结果
     */
    List<HsRecord> getHsRecordByDate(Date date);

    /**
     * 计算每天变动的分数
     * @param date 日期
     * @return 分数
     */
    Integer computeChangedScore(Date date);
}
