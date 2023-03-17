package idol.back.global.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicExceptionFormat {
    private String code;
    private String message;

    public static BasicExceptionFormat create(BasicExceptionType baseExceptionType){
        return new BasicExceptionFormat(baseExceptionType.getErrorCode(), baseExceptionType.getMessage());
    }
}
