package apptive.attendence.entity;

import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[0-9]+$", message="숫자 형태인 학번만 입력할 수 있습니다.")
    private String studentId;

    private String name;

    private String department;

    @Email
    private String email;

    private String password;

    private Integer generation;

    private String part;

    private String profilePhotoURL;

    private Roles role;

    private String team;

    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updateDate = LocalDateTime.now();

    public void updateUser(UserRequestDTO userRequest) {
        this.studentId = userRequest.studentId();
        this.name = userRequest.name();
        this.department = userRequest.department();
        this.email = userRequest.email();
        this.generation = userRequest.generation();
        this.part = userRequest.part();
        this.profilePhotoURL = userRequest.profilePhotoURL();
        this.team = userRequest.team();
    }
}
