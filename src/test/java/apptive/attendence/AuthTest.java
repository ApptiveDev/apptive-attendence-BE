package apptive.attendence;

import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.ProfileResponseDTO;
import apptive.attendence.entity.Users;
import apptive.attendence.repository.UsersRepository;
import apptive.attendence.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long userId;

    @BeforeEach
    public void setUp() {
        Users user = Users.builder()
                .name("가입자")
                .email("abcderdf@academy.ac.kr")
                .password(passwordEncoder.encode("1111"))
                .part("프론트엔드")
                .team("웹서비스 1팀")
                .department("컴퓨터공학과")
                .studentId("123456789")
                .generation(28)
                .build();
        usersRepository.save(user);
        userId = user.getId();
    }

    @Test
    public void signupTest() {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "123456788",
                "가입자2",
                "생명공학과",
                "asdfjkl@academy.ac.kr",
                "2222",
                28,
                "기획",
                null,
                null
        );

        userService.signup(requestDTO);

        Assertions.assertEquals(2, usersRepository.count());
    }

    @Test
    public void getProfileTest() {
        ProfileResponseDTO profile = userService.getProfile(userId);
        Assertions.assertNotNull(profile);
    }

    @Test
    public void editProfileTest() {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "123456789",
                "가입자",
                "AI컴퓨터공학부",
                "abcderdf@academy.ac.kr",
                "1234",
                28,
                "프론트엔드",
                null,
                "웹서비스 1팀"
        );
        ProfileResponseDTO newProfile = userService.updateProfile(userId, requestDTO);

        Assertions.assertEquals("AI컴퓨터공학부",newProfile.department());
    }
}
