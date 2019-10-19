package cn.sxl.host.service;

import cn.sxl.host.entity.Category;

import java.util.List;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:11
 */

public interface CategoryService {

    /**
     * 获取全部类别
     * @return 类别列表
     */
    List<Category> getAllCategory() ;

    /**
     * 根据名称查找
     * @param categoryName 名称
     * @return 类别实体
     */
    Category getCategoryByName(String categoryName);

    /**
     * 根据 ID 查找
     * @param categoryId ID
     * @return 类别实体
     */
    Category getCategoryById(int categoryId);
}
