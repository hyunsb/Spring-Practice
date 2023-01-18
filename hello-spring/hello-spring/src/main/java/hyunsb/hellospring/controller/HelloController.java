package hyunsb.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    //Ctrl + P : 파라매터 정보
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }


    // 해당 웹페이지의 소스를 확인해보면 hmtl코드 없이 문자열만 존재하는 것을 확인할 수 있음
    @GetMapping("hello-string")
    @ResponseBody // http의 header, <body>부에 해당 내용을 직접 넣어 주겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }
}
