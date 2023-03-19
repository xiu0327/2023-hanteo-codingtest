package idol.back.board.exceptions;

import idol.back.global.exceptions.BasicExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardExceptionType implements BasicExceptionType {
    WRONG_BOARD_TYPE("WRONG_BOARD_TYPE", "잘못된 게시판 유형입니다.", HttpStatus.BAD_REQUEST)
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
