package idol.back.global.exceptions;

import org.springframework.http.HttpStatus;

public interface BasicExceptionType {
    String getErrorCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
