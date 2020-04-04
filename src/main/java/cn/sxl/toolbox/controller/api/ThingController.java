package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.Thing;
import cn.sxl.toolbox.service.ThingService;
import cn.sxl.toolbox.vo.ThingVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SxL
 * @since 1.5.0
 * 2019-10-15 00:13
 */

@RestController
@RequestMapping("/api/thing")
public class ThingController {

    private final ThingService thingService;

    public ThingController(ThingService thingService) {
        this.thingService = thingService;
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Thing>> getThingsByCategory(@PathVariable String categoryName) {
        List<Thing> things = thingService.getThingsByCategoryName(categoryName);

        return ResponseEntity.ok(things);
    }

    @GetMapping("/{thingName}")
    public ResponseEntity<ThingVO> getThingByName(@PathVariable String thingName) {
        ThingVO thingVO = thingService.getThingVoByName(thingName);

        return ResponseEntity.ok(thingVO);
    }

    @PostMapping("")
    public ResponseEntity<Thing> addThing(@RequestBody Thing thing) {
        Thing addThing = thingService.addThing(thing);

        return ResponseEntity.ok(addThing);
    }

    @PutMapping("")
    public ResponseEntity<Thing> changeCategory(@RequestBody Thing thing) {
        Thing changeThing = thingService.changeCategory(thing);

        return ResponseEntity.ok(changeThing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Thing> removeThing(@PathVariable Integer id) {
        Thing thing = thingService.removeThing(id);

        return ResponseEntity.ok(thing);
    }
}
