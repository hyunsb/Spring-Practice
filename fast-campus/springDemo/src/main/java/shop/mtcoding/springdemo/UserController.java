package shop.mtcoding.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class UserController {

    public UserController() {
        System.out.println("Call UserController Constructor");
    }

    @ResponseBody
    @GetMapping("/login")
    public String login() {
        return "<h1>login</h1>"; // text/html MIME TYPE
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm"; // ViewResolver 발동 (prefix + joinForm + suffix)
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public User userInfo() {
        User user = new User(1L, "ssar", "ssar@nate.com");
        return user; // MessageConverter 발동 (getter 호출 -> json 반환) application/json
    }

    @ResponseBody
    @GetMapping("/error1/{num}")
    public String error1(@PathVariable int num) {
        if (num == 1) return "error1";
        throw new RuntimeException("error1에서 오류");
    }

    @ResponseBody
    @GetMapping("/error2/{num}")
    public String error2(@PathVariable int num) {
        if (num == 1) return "error2";
        throw new RuntimeException("error2에서 오류");
    }
}
