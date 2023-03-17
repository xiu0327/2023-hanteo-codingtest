package idol.back.category.exceptions;

import idol.back.global.exceptions.BasicExceptionType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CategoryExceptionType implements BasicExceptionType {
    NOT_FOUND_CATEGORY("NOT_FOUND_CATEGORY", "해당 카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NON_EXISTENT_PARENT("NON_EXISTENT_PARENT", "존재하지 않는 부모 카테고리 입니다.", HttpStatus.BAD_REQUEST)
    ;

    private String errorCode;
    private String message;
    private HttpStatus httpStatus;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
