package cn.sxl.host.service;

import cn.sxl.host.entity.ProgramHost;
import cn.sxl.host.vo.ProgramVO;

/**
 * @author SxL
 * @since 1.2.0
 * 2019-09-03 15:54
 */
public interface ProgramHostService {
    /**
     * 将 Host 添加到方案中
     * @param programHost 对应实体
     */
    void addHostToProgram(ProgramHost programHost);

    /**
     * 删除 Program 下的所有 Host
     * @param programId Program ID
     * @since 1.3.0
     */
    void clearHostList(int programId);
}
