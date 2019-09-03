package cn.sxl.host.controller;

import cn.sxl.host.entity.Host;
import cn.sxl.host.entity.Program;
import cn.sxl.host.entity.ProgramHost;
import cn.sxl.host.service.HostService;
import cn.sxl.host.service.ProgramHostService;
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
    private final ProgramHostService programHostService;

    public ProgramController(ProgramService programService, HostService hostService, ProgramHostService programHostService) {
        this.programService = programService;
        this.hostService = hostService;
        this.programHostService = programHostService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<ProgramVO> getProgram(@PathVariable("name") String name) {
        ProgramVO programVO = setProgramVO(name);

        return ResponseEntity.ok(programVO);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Program>> getAllProgram(){
        List<Program> programList = programService.getAllProgram();

        return ResponseEntity.ok(programList);
    }

    @GetMapping(value = "/pretty/{name}")
    public ResponseEntity<String> getProgramAfterPretty(@PathVariable("name") String name) {
        ProgramVO programVO = setProgramVO(name);

        String prettyResult = ResultUtil.prettyResult(programVO);

        return ResponseEntity.ok(prettyResult);
    }

    @PostMapping(value = {"/",""})
    public ResponseEntity<Program> addProgram(@RequestBody Program program){
        Program added = programService.addProgram(program);
        return ResponseEntity.ok(added);
    }

    @PostMapping(value = "/{name}/host")
    public ResponseEntity<ProgramVO> addHostList(@PathVariable("name") String programName, @RequestBody List<Host> hostList){
        ProgramHost programHost;
        Program program = programService.getProgramByName(programName);

        for (Host host : hostList) {
            programHost = setProgramHost(program.getId(), host.getId());
            programHostService.addHostToProgram(programHost);
        }

        ProgramVO programVO = setProgramVO(programName);

        return ResponseEntity.ok((programVO));
    }

    private ProgramVO setProgramVO(String programName){
        ProgramVO programVO = new ProgramVO();

        Program program = programService.getProgramByName(programName);
        List<Host> hostList =hostService.getHostListByProgramId(program.getId());

        programVO.setProgram(program);
        programVO.setHostList(hostList);

        return programVO;
    }

    private ProgramHost setProgramHost(int programId, int hostId) {
        return new ProgramHost(programId, hostId);
    }
}
