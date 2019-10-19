package cn.sxl.host.vo;

import cn.sxl.host.entity.Category;
import lombok.Data;

import java.util.Calendar;

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
