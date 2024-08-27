package br.com.mobiauto.mobiauto_server.configuration.shared;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeConfiguration {

    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui/index.html";
    }
}
