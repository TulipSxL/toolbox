package cn.sxl.toolbox.controller.api.fs;

import cn.sxl.toolbox.util.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author sxl
 * @since 2020/4/21 下午10:14
 */
@RestController
@RequestMapping("/api/fs")
public class FileSystemController {

    private final String DIR_SUFFIX = "/root/web";

    @GetMapping("")
    public ResponseEntity<Map<String, List<String>>> getFileList(@RequestParam(value = "targetPath", defaultValue = "/") String targetPath) {
        if (!new File(targetPath).exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!new File(targetPath).isDirectory()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Map<String, List<String>> map = FileUtils.listFile(targetPath);
        //TODO online must this
//        Map<String, List<String>> map = FileUtils.listFile(DIR_SUFFIX + targetPath);

        return ResponseEntity.ok(map);
    }

}
