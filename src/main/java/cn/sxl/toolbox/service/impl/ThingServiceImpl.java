package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.Category;
import cn.sxl.toolbox.entity.Thing;
import cn.sxl.toolbox.repository.CategoryRepository;
import cn.sxl.toolbox.repository.ThingRepository;
import cn.sxl.toolbox.service.ThingService;
import cn.sxl.toolbox.vo.ThingVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:12
 */

@Service
public class ThingServiceImpl implements ThingService {

    private final ThingRepository thingRepository;
    private final CategoryRepository categoryRepository;

    public ThingServiceImpl(ThingRepository thingRepository, CategoryRepository categoryRepository) {
        this.thingRepository = thingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Thing> getThingsByCategoryName(String categoryName) {
        return thingRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public Thing addThing(Thing thing) {
        return thingRepository.saveAndFlush(thing);
    }

    @Override
    public ThingVO getThingVoByName(String thingName) {
        Thing thing = thingRepository.findByName(thingName);
        Optional<Category> optionalCategory = categoryRepository.findById(thing.getCategoryId());

        return new ThingVO(thing.getName(), optionalCategory.orElse(null));
    }

    @Override
    public Thing changeCategory(Thing thing) {
        return thingRepository.saveAndFlush(thing);
    }

    @Override
    public Thing removeThing(int thingId) {
        Optional<Thing> thing = thingRepository.findById(thingId);
        thingRepository.delete(thing.orElse(new Thing()));

        return thing.orElse(null);
    }
}
