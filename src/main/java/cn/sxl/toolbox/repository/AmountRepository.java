package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author SxL
 * @since 1.6.0
 * 2019-10-22 15:31
 */
public interface AmountRepository extends JpaRepository<Amount, Integer> {

    /**
     * 查询所有年份
     * @return 年份列表
     */
    @Query(value = "select distinct a.year from amount a ", nativeQuery = true)
    List<String> findAllYears();

    /**
     * 根据年份查询所有
     * @param year 查询年份
     * @return 列表
     */
    List<Amount> findAllByYear(String year);

    /**
     * 根据年份和月份查询
     * @param year 年份
     * @param month 月份
     * @return 唯一数据
     */
    Amount findByYearAndMonth(String year, String month);
}
