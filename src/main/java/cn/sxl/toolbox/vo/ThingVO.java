package cn.sxl.toolbox.vo;

import cn.sxl.toolbox.entity.Category;
import lombok.Data;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 22:45
 */

@Data
public class ThingVO {
    private String name;
    private Category category;

    public ThingVO(String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
