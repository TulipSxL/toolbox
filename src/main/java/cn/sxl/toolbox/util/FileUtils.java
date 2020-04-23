package cn.sxl.toolbox.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author sxl
 * @since 2020/4/22 下午3:13
 */

@Log4j2
public class FileUtils {

    public static Map<String, List<String>> listFile(String path) {
        File dir = new File(path);
        Map<String, List<String>> dirMap = Maps.newHashMap();
        List<String> fileList = Lists.newArrayList();
        List<String> dirList = Lists.newArrayList();

        String[] files = dir.list();

        if (files != null) {
            for (String s : files) {
                File file = new File(dir, s);
                if (file.isFile()) {
                    fileList.add(file.getName());
                } else {
                    dirList.add(file.getName());
                }
            }
        }

        dirMap.put("file", fileList);
        dirMap.put("dir", dirList);

        return dirMap;
    }


    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return 是否上传成功
     */
    public static boolean upload(MultipartFile file, String path, String fileName){

        File dest = new File(path + "/" + fileName);

        try {
            // 保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException | IOException e) {
            log.error("upload failed! " + e.getMessage());
            return false;
        }

    }

    /**
     * 获取文件后缀
     * @param fileName 文件名
     * @return 文件后缀
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
