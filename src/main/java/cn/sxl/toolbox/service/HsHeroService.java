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
     * 获取所有上线英雄
     * @return 所有英雄列表
     */
    List<HsHero> getAllOnlineHsHero();
}
