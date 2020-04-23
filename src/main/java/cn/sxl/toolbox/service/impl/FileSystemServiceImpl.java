package cn.sxl.toolbox.service.impl;

import cn.sxl.toolbox.service.FileSystemService;
import cn.sxl.toolbox.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sxl
 * @since 2020/4/22 下午3:42
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Override
    public Boolean upload(MultipartFile file, String path, String fileName) {
        return FileUtils.upload(file, path, fileName);
    }
}
