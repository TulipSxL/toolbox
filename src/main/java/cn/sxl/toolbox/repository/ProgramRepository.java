package cn.sxl.toolbox.repository;

import cn.sxl.toolbox.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:35
 */

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    /**
     * 根据 姓名 查找
     * @param name 姓名
     * @return 查找结果
     */
    Program findByName(String name);
}
