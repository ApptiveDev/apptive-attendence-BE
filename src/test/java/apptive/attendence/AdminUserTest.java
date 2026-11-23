package apptive.attendence;

import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.entity.Roles;
import apptive.attendence.entity.Users;
import apptive.attendence.repository.UsersRepository;
import apptive.attendence.service.AdminUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class AdminUserTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminUserService adminUserService;

    @BeforeEach
    public void setUp() {
        Users admin = Users.builder()
                .name("관리자")
                .email("admin1@academy.ac.kr")
                .part("백엔드")
                .department("컴퓨터공학과")
                .generation(58)
                .role(Roles.ADMIN)
                .build();
        usersRepository.save(admin);

        Users user = Users.builder()
                .name("유저")
                .email("user1@academy.ac.kr")
                .part("기획")
                .department("경영학과")
                .generation(58)
                .role(Roles.USER)
                .build();

        usersRepository.save(user);
    }

    @Test
    public void allUsersGetTest() {
        List<UserResponseDTO> allUsers = adminUserService.getAllUsers();
        Assertions.assertEquals(2, allUsers.size());
    }
}
