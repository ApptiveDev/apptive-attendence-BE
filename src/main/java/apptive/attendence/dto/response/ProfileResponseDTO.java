package apptive.attendence.dto.response;

import apptive.attendence.entity.Users;

public record ProfileResponseDTO (
        Long userId,
        String name,
        String department,
        String studentId,
        String email,
        Integer generation,
        String part,
        String team
) {
    public static ProfileResponseDTO from(Users user) {
        return new ProfileResponseDTO(
                user.getId(),
                user.getName(),
                user.getDepartment(),
                user.getStudentId(),
                user.getEmail(),
                user.getGeneration(),
                user.getPart(),
                user.getTeam()
        );
    }
}