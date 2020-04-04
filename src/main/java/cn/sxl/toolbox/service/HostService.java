package cn.sxl.toolbox.service;

import cn.sxl.toolbox.entity.Host;

import java.util.List;

/**
 * @author SxL
 * @since 1.1.0
 * 2019-09-03 03:36
 */

public interface HostService {
    /**
     * 通过 ID 获取 Host 实体
     * @param id ID
     * @return 实体
     */
    Host getHostById(int id);

    /**
     * 获取全部 Host
     * @return Host 列表
     */
    List<Host> getAllHost();

    /**
     * 添加一个 Host
     * @param host 实体
     * @return 添加的实体
     */
    Host addHost(Host host);

    /**
     * 获取某个方案下的 Host 列表
     * @param programId 方案 ID
     * @return Host 列表
     */
    List<Host> getHostListByProgramId(int programId);

    /**
     * 删除 Host
     * @param id Host ID
     * @since 1.3.0
     */
    void removeHostById(int id);

    /**
     * 修改 Host
     * @param host Host 实体
     * @since 1.3.0
     * @return Host
     */
    Host modifyHost(Host host);
}
