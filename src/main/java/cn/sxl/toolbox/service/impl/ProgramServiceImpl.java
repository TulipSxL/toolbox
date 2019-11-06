package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.entity.Program;
import cn.sxl.toolbox.repository.ProgramRepository;
import cn.sxl.toolbox.service.ProgramService;
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
    public Program getProgramById(int id) {
        Optional<Program> programOptional = programRepository.findById(id);

        return programOptional.orElse(null);
    }

    @Override
    public List<Program> getAllProgram() {
        return programRepository.findAll();
    }

    @Override
    public Program addProgram(Program program) {
        return programRepository.saveAndFlush(program);
    }

    @Override
    public void removeProgramById(int id) {
        programRepository.deleteById(id);
    }

    @Override
    public Program modifyProgram(Program program) {
        return programRepository.saveAndFlush(program);
    }
}
