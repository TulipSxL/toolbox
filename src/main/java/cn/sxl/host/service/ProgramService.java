package cn.sxl.host.service;

import cn.sxl.host.entity.Program;
import cn.sxl.host.entity.Program;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:36
 */
public interface ProgramService {

    /**
     * 通过 名称 获取 Program 实体
     * @param name 名称
     * @return 实体
     */
    Program getProgramByName(String name);

    /**
     * 通过 ID 获取 Program 实体
     * @param id ID
     * @return 实体
     */
    Program getProgramById(int id);

    /**
     * 获取全部 Program
     * @return Program 列表
     */
    List<Program> getAllProgram();

    /**
     * 添加一个 Program
     * @param program 实体
     * @return 添加的实体
     */
    Program addProgram(Program program);

    /**
     * 删除 Program
     * @param id Program ID
     * @since 1.3.0
     */
    void removeProgramById(int id);

    /**
     * 修改 Program
     * @param program Program 实体
     * @since 1.3.0
     * @return Program
     */
    Program modifyProgram(Program program);
}
