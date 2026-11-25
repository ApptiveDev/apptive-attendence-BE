package apptive.attendence.exception.dto;

public record ErrorResult (
        ErrorType error,
        int status,
        String message
) {}
