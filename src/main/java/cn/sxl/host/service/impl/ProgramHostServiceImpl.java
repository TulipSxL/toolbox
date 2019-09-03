package cn.sxl.host.service.impl;

import cn.sxl.host.entity.ProgramHost;
import cn.sxl.host.repository.ProgramHostRepository;
import cn.sxl.host.service.ProgramHostService;
import org.springframework.stereotype.Service;

/**
 * @author SxL
 * @since 1.2.0
 * 2019-09-03 15:54
 */
@Service
public class ProgramHostServiceImpl implements ProgramHostService {

    private final ProgramHostRepository programHostRepository;

    public ProgramHostServiceImpl(ProgramHostRepository programHostRepository) {
        this.programHostRepository = programHostRepository;
    }

    @Override
    public ProgramHost addHostToProgram(ProgramHost programHost) {
        return programHostRepository.saveAndFlush(programHost);
    }
}
