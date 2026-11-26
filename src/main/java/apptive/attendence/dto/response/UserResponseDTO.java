package apptive.attendence.dto.response;

import apptive.attendence.entity.Users;

public record UserResponseDTO(
   Long userId,
   Integer generation,
   String name,
   String part,
   String department,
   String studentId,
   String profilePhotoURL
) {
    public static UserResponseDTO from(Users user) {
        return new UserResponseDTO(
                user.getId(),
                user.getGeneration(),
                user.getName(),
                user.getPart(),
                user.getDepartment(),
                user.getStudentId(),
                user.getProfilePhotoURL()
        );
    }
}
