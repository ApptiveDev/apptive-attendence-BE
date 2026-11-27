package apptive.attendence.exception;

import apptive.attendence.exception.dto.ErrorResult;
import apptive.attendence.exception.dto.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler
    public ErrorResult handleException(UserNotFoundException ex){
        return new ErrorResult(
                ErrorType.USER_NOT_FOUND,
                404,
                ex.getMessage()
        );
    }


}
