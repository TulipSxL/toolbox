package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.ProgramHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author SxL
 * @since 1.2.0
 * 2019-09-03 15:53
 */
public interface ProgramHostRepository extends JpaRepository<ProgramHost, Integer> {

    /**
     * 通过ID删除
     * @param programId ID
     */
    @Query(value = "delete from program_host where program_id = ?1", nativeQuery = true)
    void deleteHostByProgramId(int programId);
}
