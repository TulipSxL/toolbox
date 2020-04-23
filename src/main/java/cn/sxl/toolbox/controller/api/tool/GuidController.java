package cn.sxl.toolbox.controller.api.tool;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author sxl
 * @since 20-4-7
 */
@RestController
@RequestMapping("/api/guid")
public class GuidController {

    @GetMapping("")
    public ResponseEntity<String> generateGuid() {
        UUID uuid = UUID.randomUUID();

        return ResponseEntity.ok(uuid.toString());
    }
}
