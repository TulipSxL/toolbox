package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.HsHero;
import cn.sxl.toolbox.repository.HsHeroRepository;
import cn.sxl.toolbox.service.HsHeroService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 15:08
 */

@Service
public class HsHeroServiceImpl implements HsHeroService {

    private final HsHeroRepository hsHeroRepository;

    public HsHeroServiceImpl(HsHeroRepository hsHeroRepository) {
        this.hsHeroRepository = hsHeroRepository;
    }

    @Override
    public List<HsHero> getAllOnlineHsHero() {
        return hsHeroRepository.getAllByState(true);
    }
}
