package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:30
 */
public interface HostRepository extends JpaRepository<Host, Integer> {

    /**
     * 查询某个方案下的 Host 列表
     *
     * @param programId 方案 ID
     * @return Host 列表
     */
    @Query(
            value = "select h.id, h.ip, h.name, h.status from host h ,program_host ph where ph.program_id = ?1 and ph.host_id = h.id and h.status = 1"
            , nativeQuery = true)
    List<Host> findHostByProgramId(int programId);
}