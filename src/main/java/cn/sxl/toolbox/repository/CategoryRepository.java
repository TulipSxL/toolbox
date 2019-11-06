package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:10
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * 通过名称获取
     * @param categoryName 名称
     * @return 类别实体
     */
    Category findByName(String categoryName);
}
