package shop.mtcoding.springdemo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 모든 Exception 을 catch 해서 MessageConverter 가 발동하는 오브젝트
@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException exception) {
        System.out.println("MyControllerAdvice 실행됨");
        return new ErrorResponse(exception.getMessage());
    }
}
