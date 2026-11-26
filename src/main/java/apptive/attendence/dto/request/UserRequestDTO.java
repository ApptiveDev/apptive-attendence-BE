package apptive.attendence.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO (
        String studentId,
        String name,
        String department,
        String email,
        String password,
        Integer generation,
        String part,
        String profilePhotoURL,
        String team
) { }
