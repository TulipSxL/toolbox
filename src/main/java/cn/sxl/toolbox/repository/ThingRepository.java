package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:10
 */
public interface ThingRepository extends JpaRepository<Thing, Integer> {

    /**
     * 根据类别查找 thing
     *
     * @param categoryName 类别名
     * @return thing 列表
     */
    @Query(value = "SELECT th.id, th.name, th.category_id FROM thing th, category c WHERE th.category_id = c.id AND c.name = ?1",
            nativeQuery = true)
    List<Thing> findAllByCategoryName(String categoryName);

    /**
     *  根据物件名称查找
     * @param thingName 名称
     * @return 物件实体
     */
    Thing findByName(String thingName);
}
