package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.HsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 12:12
 */
public interface HsRecordRepository extends JpaRepository<HsRecord, Integer> {

    /**
     * 查询所有日期
     * @return 日期列表
     */
    @Query(value = "select hs.date from hs_record hs group by hs.date order by hs.date desc ", nativeQuery = true)
    List<Date> findAllDate();

    /**
     * 根据日期查询
     * @param date 日期
     * @return 查询结果
     */
    @Query(value = "select hs.id, hs.name, hs.rank, hs.score, hs.increment, date_format(hs.time, '%H:%i:%S') as time, hs.date from hs_record hs where hs.date = ?1 order by hs.time desc",
            nativeQuery = true)
    List<HsRecord> findHearthStoneRecordByDate(Date date);
}
