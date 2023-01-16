package hyunsb.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    String name = "정현수";

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", name);

        // templates/hello.html 을 랜더링한다.
        // 이 때, model(date : hello!!!)
        return "hello";
    }
}
