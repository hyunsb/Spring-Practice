package shop.mtcoding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    public BoardController() {
        System.out.println("Call BoardController Constructor");
    }

    @GetMapping("/main")
    @ResponseBody
    public String main() {
        return "<h1>hello</h1>";
    }
}
