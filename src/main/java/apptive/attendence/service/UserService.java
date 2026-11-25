package apptive.attendence.service;

import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.ProfileResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.entity.Users;
import apptive.attendence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    public void signup(UserRequestDTO userRequestDTO) {
        Users newUser = Users.builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .part(userRequestDTO.part())
                .generation(userRequestDTO.generation())
                .studentId(userRequestDTO.studentId())
                .department(userRequestDTO.department())
                .profilePhotoURL(null)
                .build();
        usersRepository.save(newUser);
    }

    public ProfileResponseDTO getProfile(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        return ProfileResponseDTO.from(user);
    }

    public ProfileResponseDTO updateProfile(Long userId, UserRequestDTO userRequestDTO) {
        Users user = usersRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        user.updateUser(userRequestDTO);
        usersRepository.save(user);
        return ProfileResponseDTO.from(user);
    }
}
