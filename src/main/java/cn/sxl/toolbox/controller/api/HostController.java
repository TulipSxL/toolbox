package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.Host;
import cn.sxl.toolbox.service.HostService;
import cn.sxl.toolbox.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:11
 */

@RestController
@RequestMapping("/api/host")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHost(@PathVariable("id") int id) {
        Host host = hostService.getHostById(id);

        return ResponseEntity.ok(host);
    }

    @GetMapping("")
    public ResponseEntity<List<Host>> getAllHost() {
        List<Host> hostList = hostService.getAllHost();

        return ResponseEntity.ok(hostList);
    }

    @GetMapping("/pretty")
    public ResponseEntity<String> getAllHostAfterPretty() {
        List<Host> hostList = hostService.getAllHost();
        String prettyResult = ResultUtil.prettyResult(hostList);

        return ResponseEntity.ok(prettyResult);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Host> addHost(@RequestBody Host host) {
        Host added = hostService.addHost(host);
        return ResponseEntity.ok(added);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Host> removeHost(@PathVariable int id) {
        hostService.removeHostById(id);
        return ResponseEntity.ok(hostService.getHostById(id));
    }

    @PutMapping("")
    public ResponseEntity<Host> modifyHost(@RequestBody Host host) {
        Host modified = hostService.modifyHost(host);

        return ResponseEntity.ok(modified);
    }
}
