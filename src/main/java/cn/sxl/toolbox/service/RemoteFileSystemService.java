package cn.sxl.toolbox.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author sxl
 * @since 2020/4/21 下午9:30
 */
public interface RemoteFileSystemService {

    /**
     * 上传文件
     *
     * @param targetPath 目标路径
     * @param inputStream 输入流
     * @param isKey 是否使用 Key
     * @return 上传文件
     * @throws Exception 上传异常
     */
    boolean uploadFile(String targetPath, InputStream inputStream, Boolean isKey) throws Exception;

    /**
     * 上传文件
     *
     * @param targetPath 目标路径
     * @param file 文件
     * @param isKey 是否使用 Key
     * @return 上传文件
     * @throws Exception 上传异常
     */
    boolean uploadFile(String targetPath, File file, Boolean isKey) throws Exception;

    /**
     * 下载文件
     *
     * @param targetPath 目标路径
     * @param isKey 是否使用 Key
     * @return 下载文件
     * @throws Exception 下载异常
     */
    File downloadFile(String targetPath, Boolean isKey) throws Exception;

    /**
     * 删除文件
     *
     * @param targetPath 目标路径
     * @param isKey 是否使用 Key
     * @return 删除结果
     * @throws Exception 删除异常
     */
    boolean deleteFile(String targetPath, Boolean isKey) throws Exception;

    /**
     * 查看所有文件
     *
     * @param targetPath 目标路径
     * @param isKey 是否使用 Key
     * @return 文件名列表
     * @throws Exception 查看异常
     */
    List<String> listFiles(String targetPath, Boolean isKey) throws Exception;
}
