package apptive.attendence.dto.request;

public record SignupRequestDTO (
    String email,
    String password,
    String name,
    String department,
    String studentId,
    Integer generation,
    String part,
    String team
) {}
