package apptive.attendence.exception;

import apptive.attendence.entity.Schedule;
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
                "존재하지 않는 사용자입니다"
        );
    }

    @ExceptionHandler
    public ErrorResult handleException(ScheduleNotFoundException ex) {
        return new ErrorResult(
                ErrorType.SCHEDULE_NOT_FOUND,
                404,
                "일정을 찾을 수 없습니다"
        );
    }

    @ExceptionHandler
    public ErrorResult handleException(PenaltyNotFoundException ex){
        return new ErrorResult(
                ErrorType.PENALTY_NOT_FOUND,
                404,
                "벌점 부여 목록을 찾을 수 없습니다"
        );
    }

    @ExceptionHandler
    public ErrorResult handleException(PenaltyRuleNotFoundException ex) {
        return new ErrorResult(
                ErrorType.PENALTY_RULE_NOT_FOUND,
                404,
                "벌점 부여 규정을 찾을 수 없습니다"
        );
    }
}
