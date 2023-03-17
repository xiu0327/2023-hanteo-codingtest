package idol.back.global.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException{

    private BasicExceptionType exceptionType;

    public BasicExceptionType getExceptionType() {
        return exceptionType;
    }

    public BusinessException(BasicExceptionType exceptionType) {
        super(exceptionType.getMessage());
        log.info("error message = {}", exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
