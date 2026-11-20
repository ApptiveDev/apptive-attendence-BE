package apptive.attendence.service;

import apptive.attendence.dto.response.PenaltyResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserService {

    private final UsersRepository usersRepository;

    public List<UserResponseDTO> getAllUsers() {

    }

    public List<PenaltyResponseDTO> getAllPenalties() {

    }

    public UserResponseDTO addUser() {

    }

    public void deletePenalty(long penaltyId) {

    }

    public void deleteUser(long userId) {

    }
}
