package apptive.attendence;

import apptive.attendence.dto.request.PenaltyRequestDTO;
import apptive.attendence.dto.response.PenaltyResponseDTO;
import apptive.attendence.entity.*;
import apptive.attendence.repository.AttendenceRepository;
import apptive.attendence.repository.PenaltyRepository;
import apptive.attendence.repository.PenaltyRuleRepository;
import apptive.attendence.repository.ScheduleRepository;
import apptive.attendence.repository.UsersRepository;
import apptive.attendence.service.AdminUserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Transactional
public class AdminPenaltyTest {
    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PenaltyRuleRepository penaltyRuleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AttendenceRepository attendenceRepository;

    @Autowired
    private AdminUserService adminUserService;

    private Long testUser;
    private Long testPenalty;
    private Long testSchedule;
    private Integer testPenaltyRule;

    @BeforeEach
    public void before() {
        PenaltyRule rule = PenaltyRule.builder()
                .penalty(10)
                .notes("세미나 무단 결석")
                .build();

        penaltyRuleRepository.save(rule);
        testPenaltyRule = rule.getId();

        Users user = Users.builder()
                .name("벌점자")
                .email("penalty@academy.ac.kr")
                .role(Roles.USER)
                .part("프론트엔드")
                .department("컴퓨터공학과")
                .build();

        usersRepository.save(user);
        testUser = user.getId();

        Schedule schedule = Schedule.builder()
                .title("1차 세미나")
                .scheduleDate(LocalDate.of(2020, 1, 1))
                .startTime(LocalTime.parse("16:00"))
                .endTime(LocalTime.parse("19:00"))
                .permissionPenalty(5)
                .nonPermissionPenalty(10)
                .build();

        scheduleRepository.save(schedule);
        testSchedule = schedule.getId();

        Attendence attendence = Attendence.builder()
                .users(user)
                .schedule(schedule)
                .attendence(false)
                .absentReason("무단 결석")
                .build();

        attendenceRepository.save(attendence);

        Penalty penalty = Penalty.builder()
                .penaltyRule(rule)
                .penaltyDate(LocalDate.now())
                .schedule(schedule)
                .user(user)
                .build();

        penaltyRepository.save(penalty);
        testPenalty = penalty.getId();
    }

    @Test
    public void getAllPenaltyTest() {
        List<PenaltyResponseDTO> allPenalties = adminUserService.getAllPenalties();

        System.out.println(allPenalties);
    }

    @Test
    public void addPenaltyTest() {
        PenaltyRequestDTO penaltyRequestDTO = new PenaltyRequestDTO(
                testUser,
                testSchedule,
                testPenaltyRule
        );

        PenaltyRequestDTO resultDTO = adminUserService.addPenalty(penaltyRequestDTO);
        System.out.println(resultDTO);
        Assertions.assertNotNull(resultDTO);
    }

    @Test
    public void deletePenaltyTest() {
        penaltyRepository.deleteById(testPenalty);
    }
}
