package shop.mtcoding.springdemo;

import lombok.Getter;

@Getter
public class ErrorResponse {

    boolean result;
    String message;

    public ErrorResponse(String message) {
        this.result = false;
        this.message = message;
    }
}
