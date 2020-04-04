package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.HsHero;
import cn.sxl.toolbox.service.HsHeroService;
import com.google.common.collect.Lists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<HsHero>> getAllOnlineHero(@RequestParam(value = "online", required = false, defaultValue = "false") Boolean isOnline) {
        if (isOnline) {
            List<HsHero> onlineHsHero = hsHeroService.getAllOnlineHsHero();

            return ResponseEntity.ok(onlineHsHero);
        } else {
            List<HsHero> heroList = hsHeroService.getAllHsHero();

            return ResponseEntity.ok(heroList);
        }
    }

    @PostMapping("")
    public ResponseEntity<HsHero> addNewHsHero(@RequestBody HsHero hsHero) {
        HsHero newHero = hsHeroService.addNewHero(hsHero);

        return ResponseEntity.ok(newHero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HsHero> changeHeroState(@PathVariable Integer id) {
        //应该检查数据是否存在，自用，忽略
        HsHero changedStateHero = hsHeroService.changeHeroState(id);

        return ResponseEntity.ok(changedStateHero);
    }
}
