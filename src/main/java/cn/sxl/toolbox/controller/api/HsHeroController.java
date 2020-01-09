package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.HsHero;
import cn.sxl.toolbox.service.HsHeroService;
import com.google.common.collect.Lists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SxL
 * @since 1.7.0
 * 2020-01-09 15:15
 */

@RestController
@RequestMapping("/api/hs_hero")
public class HsHeroController {

    private final HsHeroService hsHeroService;

    public HsHeroController(HsHeroService hsHeroService) {
        this.hsHeroService = hsHeroService;
    }

    @GetMapping("")
    public ResponseEntity<List<String>> getAllOnlineHero() {
        List<String> onlineHeroList = Lists.newArrayList();
        List<HsHero> allOnlineHsHero = hsHeroService.getAllOnlineHsHero();

        for (HsHero onlineHsHero : allOnlineHsHero) {
            onlineHeroList.add(onlineHsHero.getName());
        }

        return ResponseEntity.ok(onlineHeroList);
    }
}
