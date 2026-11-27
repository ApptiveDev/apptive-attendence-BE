package apptive.attendence.service;

import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.ProfileResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.entity.Users;
import apptive.attendence.exception.UserNotFoundException;
import apptive.attendence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserRequestDTO userRequestDTO) {
        Users newUser = Users.builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .password(passwordEncoder.encode(userRequestDTO.password()))
                .part(userRequestDTO.part())
                .generation(userRequestDTO.generation())
                .studentId(userRequestDTO.studentId())
                .department(userRequestDTO.department())
                .profilePhotoURL(null)
                .build();
        usersRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public ProfileResponseDTO getProfile(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("존재하지 않는 사용자입니다");
        }

        return ProfileResponseDTO.from(user);
    }

    @Transactional
    public ProfileResponseDTO updateProfile(Long userId, UserRequestDTO userRequestDTO) {
        Users user = usersRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("존재하지 않는 사용자입니다");
        }

        user.updateUser(userRequestDTO);
        usersRepository.save(user);
        return ProfileResponseDTO.from(user);
    }
}
