package cn.sxl.toolbox.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sxl
 * @since 2020/4/22 下午3:38
 */
public interface FileSystemService {

    /**
     * 上传文件
     * @return true为成功
     */
    Boolean upload(MultipartFile file, String path, String fileName);
}
