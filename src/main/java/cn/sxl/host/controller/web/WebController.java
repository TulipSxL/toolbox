package cn.sxl.host.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SxL
 * @since 1.3.0
 * 2019-09-13 16:28
 */

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
