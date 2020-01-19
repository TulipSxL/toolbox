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
    List<HsRecord> searchHsRecordByDate(Date date);

    /**
     * 计算每天变动的分数
     * @param hsRecordList 数据列表
     * @param isSearch 是否为搜索功能
     * @return 分数
     */
    Integer computeChangedScore(List<HsRecord> hsRecordList, Boolean isSearch);

    /**
     * 根据名称查找
     * @param name 名称
     * @param date 日期
     * @return 列表
     */
    List<HsRecord> searchHsRecordByNameAndDate(String name, Date date);

    /**
     * 根据日期查询
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 结果列表
     */
    List<Date> searchDateBetween(Date beginDate, Date endDate);

    /**
     * 查询最高分
     * @return 最高分
     */
    Integer getMaxScore();
}
