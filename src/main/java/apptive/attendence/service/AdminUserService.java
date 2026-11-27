package apptive.attendence.service;

import apptive.attendence.dto.request.PenaltyRequestDTO;
import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.PenaltyDTO;
import apptive.attendence.dto.response.PenaltyResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.entity.*;
import apptive.attendence.exception.PenaltyNotFoundException;
import apptive.attendence.exception.PenaltyRuleNotFoundException;
import apptive.attendence.exception.ScheduleNotFoundException;
import apptive.attendence.exception.UserNotFoundException;
import apptive.attendence.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.util.Tuple;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserService {

    private final UsersRepository usersRepository;
    private final PenaltyRepository penaltyRepository;
    private final ScheduleRepository scheduleRepository;
    private final PenaltyRuleRepository ruleRepository;
    private final AttendenceRepository attendenceRepository;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for (Users user : users) {
            userResponseDTOs.add(UserResponseDTO.from(user));
        }
        return userResponseDTOs;
    }

    @Transactional(readOnly = true)
    public List<PenaltyResponseDTO> getAllPenalties() {
        List<Penalty> allPenalties = penaltyRepository.findAll();
        Map<Tuple<LocalDate, String>, List<PenaltyDTO>> penalties = new HashMap<>();
        List<PenaltyResponseDTO> penaltiesResponseDTOs = new ArrayList<>();

        for (int i = 0; i < allPenalties.size(); i++) {
            Penalty penalty = allPenalties.get(i);

            LocalDate scheduleDate = penalty.getSchedule().getScheduleDate();
            String name = penalty.getUser().getName();

            Attendence attendence = attendenceRepository.findByUsersIdAndScheduleId(
                    penalty.getUser().getId(), penalty.getSchedule().getId()
            );
            Tuple<LocalDate, String> key = new Tuple<>(scheduleDate, name);

            PenaltyDTO penaltyDTO = new PenaltyDTO(penalty.getPenaltyRule().getPenalty(), attendence.getAbsentReason(), penalty.getPenaltyRule().getNotes());
            if (!penalties.containsKey(key)) {
                penalties.put(key, new ArrayList<>());
            }
            penalties.get(key).add(penaltyDTO);
        }

        for (Tuple<LocalDate, String> tuple : penalties.keySet()) {
            List<PenaltyDTO> penaltyDTOs = penalties.get(tuple);
            Integer total = penaltyDTOs.stream()
                    .mapToInt(PenaltyDTO::penalty)
                    .sum();

            PenaltyResponseDTO responseDTO = new PenaltyResponseDTO(
                    tuple._1(),
                    tuple._2(),
                    penaltyDTOs,
                    total
            );
            penaltiesResponseDTOs.add(responseDTO);
        }
        return penaltiesResponseDTOs;
    }

    @Transactional
    public PenaltyRequestDTO addPenalty(PenaltyRequestDTO penaltyRequestDTO) {

        Users user = usersRepository.findById(penaltyRequestDTO.userId()).orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }

        Schedule schedule = scheduleRepository.findById(penaltyRequestDTO.scheduleId()).orElse(null);

        if (schedule == null) {
            throw new ScheduleNotFoundException();
        }

        PenaltyRule rule = ruleRepository.findById(penaltyRequestDTO.penaltyRuleId()).orElse(null);

        if (rule == null) {
            throw new PenaltyRuleNotFoundException();
        }

        Penalty penalty = Penalty.builder()
                .user(user)
                .penaltyDate(LocalDate.now())
                .schedule(schedule)
                .penaltyRule(rule)
                .build();

        penaltyRepository.save(penalty);
        return penaltyRequestDTO;
    }

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        Users user = Users.builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .part(userRequestDTO.part())
                .generation(userRequestDTO.generation())
                .studentId(userRequestDTO.studentId())
                .department(userRequestDTO.department())
                .build();

        usersRepository.save(user);
        return UserResponseDTO.from(user);
    }

    @Transactional
    public void deletePenalty(long penaltyId) {
        penaltyRepository.deleteById(penaltyId);
    }

    @Transactional
    public void deleteUser(long userId) {
        usersRepository.deleteById(userId);
    }
}
