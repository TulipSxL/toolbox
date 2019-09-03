package cn.sxl.host.controller;

import cn.sxl.host.entity.Host;
import cn.sxl.host.service.HostService;
import cn.sxl.host.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:11
 */

@RestController
@RequestMapping(value = "/host")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Host> getHost(@PathVariable("id") int id) {
        Host host = hostService.getHostById(id);

        return ResponseEntity.ok(host);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Host>> getAllHost(){
        List<Host> hostList = hostService.getAllHost();

        return ResponseEntity.ok(hostList);
    }

    @GetMapping(value = "/pretty")
    public ResponseEntity<String> getAllHostAfterPretty() {
        List<Host> hostList = hostService.getAllHost();
        String prettyResult = ResultUtil.prettyResult(hostList);

        return ResponseEntity.ok(prettyResult);
    }

    @PostMapping(value = {"/",""})
    public ResponseEntity<Host> addHost(@RequestBody Host host){
        Host added = hostService.addHost(host);
        return ResponseEntity.ok(added);
    }
}
