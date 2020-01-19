package cn.sxl.toolbox.controller.api;

import cn.sxl.toolbox.entity.Host;
import cn.sxl.toolbox.entity.Program;
import cn.sxl.toolbox.entity.ProgramHost;
import cn.sxl.toolbox.service.HostService;
import cn.sxl.toolbox.service.ProgramHostService;
import cn.sxl.toolbox.service.ProgramService;
import cn.sxl.toolbox.util.ResultUtils;
import cn.sxl.toolbox.vo.ProgramVO;
import com.google.common.collect.Lists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 06:18
 */

@RestController
@RequestMapping("/api/program")
public class ProgramController {

    private final ProgramService programService;
    private final HostService hostService;
    private final ProgramHostService programHostService;

    public ProgramController(ProgramService programService, HostService hostService, ProgramHostService programHostService) {
        this.programService = programService;
        this.hostService = hostService;
        this.programHostService = programHostService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProgramVO> getProgram(@PathVariable("name") String name) {
        ProgramVO programVO = setProgramVO(name);

        return ResponseEntity.ok(programVO);
    }

    @GetMapping("")
    public ResponseEntity<List<Program>> getAllProgram() {
        List<Program> programList = programService.getAllProgram();

        return ResponseEntity.ok(programList);
    }

    @GetMapping("/host")
    public ResponseEntity<List<ProgramVO>> getProgramWithHostList() {
        List<ProgramVO> programVoList = Lists.newArrayList();
        List<Program> programList = programService.getAllProgram();

        for (Program program : programList) {
            ProgramVO programVO = setProgramVO(program.getName());
            programVoList.add(programVO);
        }

        return ResponseEntity.ok(programVoList);
    }

    @GetMapping("/pretty/{name}")
    public ResponseEntity<String> getProgramAfterPretty(@PathVariable("name") String name) {
        ProgramVO programVO = setProgramVO(name);

        String prettyResult = ResultUtils.prettyResult(programVO);

        return ResponseEntity.ok(prettyResult);
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        Program added = programService.addProgram(program);
        return ResponseEntity.ok(added);
    }

    @PostMapping("/{name}/host")
    public ResponseEntity<ProgramVO> addHostList(@PathVariable("name") String programName, @RequestBody List<Host> hostList) {
        ProgramHost programHost;
        List<Host> newHostList = Lists.newArrayList();
        Program program = programService.getProgramByName(programName);
        List<Host> existHostList = hostService.getHostListByProgramId(program.getId());


        for (Host host : hostList) {
            if (!existHostList.contains(host)) {
                newHostList.add(host);
            }
        }

        for (Host host : newHostList) {
            programHost = setProgramHost(program.getId(), host.getId());
            programHostService.addHostToProgram(programHost);
        }

        ProgramVO programVO = setProgramVO(programName);

        return ResponseEntity.ok((programVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Program> removeProgram(@PathVariable int id) {
        programService.removeProgramById(id);

        return ResponseEntity.ok(programService.getProgramById(id));
    }

    @PutMapping("")
    public ResponseEntity<Program> modifyProgram(@RequestBody Program program) {
        Program modified = programService.modifyProgram(program);

        return ResponseEntity.ok(modified);
    }

    @PutMapping("/{id}/host")
    public ResponseEntity<ProgramVO> modifyHostList(@PathVariable("id") int programId, @RequestBody List<Host> hostList) {
        ProgramHost programHost;
        Program program = programService.getProgramById(programId);
        programHostService.clearHostList(programId);

        for (Host host : hostList) {
            programHost = setProgramHost(program.getId(), host.getId());
            programHostService.addHostToProgram(programHost);
        }

        ProgramVO programVO = setProgramVO(program.getName());

        return ResponseEntity.ok((programVO));
    }

    private ProgramVO setProgramVO(String programName) {
        ProgramVO programVO = new ProgramVO();

        Program program = programService.getProgramByName(programName);
        List<Host> hostList = hostService.getHostListByProgramId(program.getId());

        programVO.setProgram(program);
        programVO.setHostList(hostList);

        return programVO;
    }

    private ProgramHost setProgramHost(int programId, int hostId) {
        return new ProgramHost(programId, hostId);
    }
}
