package cn.sxl.toolbox.controller.api.tool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author sxl
 * @since 1.8.0
 */

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @GetMapping("encode")
    public ResponseEntity<String> encodeUrl(@RequestParam String url) {
        String encodeUrl;
        try {
            encodeUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(encodeUrl);
    }

    @GetMapping("decode")
    public ResponseEntity<String> decodeUrl(@RequestParam String url) {
        String decodeUrl;
        try {
            decodeUrl = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(decodeUrl);
    }
}
