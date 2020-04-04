package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.HsHero;
import cn.sxl.toolbox.repository.HsHeroRepository;
import cn.sxl.toolbox.service.HsHeroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<HsHero> getAllHsHero() {
        return hsHeroRepository.findAll();
    }

    @Override
    public List<HsHero> getAllOnlineHsHero() {
        return hsHeroRepository.getAllByState(true);
    }

    @Override
    public HsHero addNewHero(HsHero hsHero) {
        return hsHeroRepository.save(hsHero);
    }

    @Override
    public HsHero changeHeroState(Integer id) {
        Optional<HsHero> hsHeroOptional = hsHeroRepository.findById(id);
        HsHero hsHero = hsHeroOptional.orElse(null);

        assert hsHero != null;

        hsHero.setState(!hsHero.getState());
        return hsHeroRepository.saveAndFlush(hsHero);
    }
}
