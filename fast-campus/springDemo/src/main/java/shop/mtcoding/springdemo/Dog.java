package shop.mtcoding.springdemo;

import org.springframework.stereotype.Component;

@Component
public class Dog {

    private String name = "강아지";

    public Dog() {
        System.out.println("Dog 생성자 호출됨");
    }
}
