package cn.sxl.host.controller;

import cn.sxl.host.entity.Host;
import cn.sxl.host.entity.Program;
import cn.sxl.host.service.HostService;
import cn.sxl.host.service.ProgramService;
import cn.sxl.host.util.ResultUtil;
import cn.sxl.host.vo.ProgramVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 06:18
 */

@RestController
@RequestMapping(value = "/program")
public class ProgramController {

    private final ProgramService programService;
    private final HostService hostService;

    public ProgramController(ProgramService programService, HostService hostService) {
        this.programService = programService;
        this.hostService = hostService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Program> getProgram(@PathVariable("name") String name) {
        Program program = programService.getProgramByName(name);

        return ResponseEntity.ok(program);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Program>> getAllProgram(){
        List<Program> programList = programService.getAllProgram();

        return ResponseEntity.ok(programList);
    }

    @GetMapping(value = "/pretty/{name}")
    public ResponseEntity<String> getProgramAfterPretty(@PathVariable("name") String name) {
        ProgramVO programVO = new ProgramVO();

        Program program = programService.getProgramByName(name);
        List<Host> hostList =hostService.getHostListByProgramId(program.getId());

        programVO.setProgram(program);
        programVO.setHostList(hostList);

        String prettyResult = ResultUtil.prettyResult(programVO);

        return ResponseEntity.ok(prettyResult);
    }

    @PostMapping(value = {"/",""})
    public ResponseEntity<Program> addProgram(@RequestBody Program program){
        Program added = programService.addProgram(program);
        return ResponseEntity.ok(added);
    }
}
