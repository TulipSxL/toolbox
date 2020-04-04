package cn.sxl.toolbox.service;

import cn.sxl.toolbox.entity.HsHero;

import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 15:07
 */

public interface HsHeroService {

    /**
     * 获取全部英雄
     * @return 英雄列表
     */
    List<HsHero> getAllHsHero();

    /**
     * 获取所有上线英雄
     * @return 所有英雄列表
     */
    List<HsHero> getAllOnlineHsHero();

    /**
     * 添加一个新英雄
     * @param hsHero 新英雄
     * @return 添加的英雄
     */
    HsHero addNewHero(HsHero hsHero);

    /**
     * 改变英雄状态
     * 0_下线 1_上线
     * @param id 英雄id
     * @return 改变后的英雄
     */
    HsHero changeHeroState(Integer id);
}
