package idol.back.global.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BasicExceptionFormat> businessException(BusinessException e) {
        return new ResponseEntity<>(BasicExceptionFormat.create(e.getExceptionType()), e.getExceptionType().getHttpStatus());
    }

}
