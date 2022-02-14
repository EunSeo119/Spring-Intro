package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    //그냥 처음 localhost:8080 들어오면 이게 호출됨
    public String home() {
        return "home";
    }
}
