package cn.sxl.host.service.impl;

import cn.sxl.host.entity.Program;
import cn.sxl.host.repository.ProgramRepository;
import cn.sxl.host.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:37
 */

@Service
public class ProgramServiceImpl implements ProgramService {
    
    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program getProgramByName(String name) {
        return programRepository.findByName(name);
    }

    @Override
    public List<Program> getAllProgram() {
        return programRepository.findAll();
    }

    @Override
    public Program addProgram(Program program) {
        return programRepository.saveAndFlush(program);
    }
}
