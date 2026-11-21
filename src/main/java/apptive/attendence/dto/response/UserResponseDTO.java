package apptive.attendence.dto.response;

import apptive.attendence.entity.Users;

public record UserResponseDTO(
   Integer generation,
   String name,
   String part,
   String department,
   String studentId
) {
    public static UserResponseDTO from(Users user) {
        return new UserResponseDTO(
                user.getGeneration(),
                user.getName(),
                user.getPart(),
                user.getDepartment(),
                user.getStudentId()
        );
    }
}
