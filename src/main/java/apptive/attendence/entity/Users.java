package apptive.attendence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[0-9]$", message="숫자 형태인 학번만 입력할 수 있습니다.")
    private String studentId;

    private String name;

    private String department;

    @Email
    private String email;

    private Integer generation;

    private String part;
}
