package cn.sxl.host.service;

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
}
