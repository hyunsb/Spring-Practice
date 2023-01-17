package hyunsb.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //@RequestParam("가져올 데이터의 이름") [데이터 타입], [변수명]
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
