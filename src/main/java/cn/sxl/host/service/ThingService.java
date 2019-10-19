package cn.sxl.host.service;

import cn.sxl.host.entity.Thing;
import cn.sxl.host.vo.ThingVO;

import java.util.List;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:12
 */

public interface ThingService {

    /**
     * 获取某个类别的 thing
     * @param categoryName 类别名
     * @return thing 列表
     */
    List<Thing> getThingsByCategoryName(String categoryName);

    /**
     * 添加物件
     * @param thing 待添加
     * @return 添加的物件
     */
    Thing addThing(Thing thing);

    /**
     * 根据物件名称查找
     * @param thingName 名称
     * @return 物件实体
     */
    ThingVO getThingVoByName(String thingName);

    /**
     * 改变物件的种类
     * @param thing 待改变的实体
     * @return 改变后的实体
     */
    Thing changeCategory(Thing thing);

    /**
     * 删除物件
     * @param thingId 物件
     * @return 删除的物件
     */
    Thing removeThing(int thingId);
}
