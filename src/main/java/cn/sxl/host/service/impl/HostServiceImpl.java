package cn.sxl.host.service.impl;

import cn.sxl.host.entity.Host;
import cn.sxl.host.repository.HostRepository;
import cn.sxl.host.service.HostService;
import org.hibernate.criterion.Example;
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
        Optional<Host> hostOptional = hostRepository.findById(id);
        return hostOptional.orElse(null);
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

    @Override
    public void removeHostById(int id) {
        hostRepository.deleteById(id);
    }

    @Override
    public Host modifyHost(Host host) {
        return hostRepository.saveAndFlush(host);
    }
}
