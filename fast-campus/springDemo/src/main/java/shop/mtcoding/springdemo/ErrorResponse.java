package shop.mtcoding.springdemo;

public class ErrorObject {

    boolean result;
    String message;

    public ErrorObject(String message) {
        this.result = false;
        this.message = message;
    }
}
