package shop.mtcoding.bankapp.handler.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException {

    private HttpStatus status;

    public AuthException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public AuthException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
