package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.HsHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 15:07
 */

public interface HsHeroRepository extends JpaRepository<HsHero, Integer> {

    /**
     * 获取所有在线或下线的英雄
     * @param state 是否在线 1为在线
     * @return 英雄列表
     */
    List<HsHero> getAllByState(boolean state);
}
