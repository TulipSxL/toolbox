package cn.sxl.host.service.impl;

import cn.sxl.host.entity.Host;
import cn.sxl.host.repository.HostRepository;
import cn.sxl.host.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:37
 */

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Host getHostById(int id) {
        Optional<Host> host = hostRepository.findById(id);
        return host.orElse(null);
    }

    @Override
    public List<Host> getAllHost() {
        return hostRepository.findAll();
    }

    @Override
    public Host addHost(Host host) {
        return hostRepository.saveAndFlush(host);
    }

    @Override
    public List<Host> getHostListByProgramId(int programId) {
        return hostRepository.findHostByProgramId(programId);
    }
}
