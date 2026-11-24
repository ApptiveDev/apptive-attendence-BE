package apptive.attendence;

import apptive.attendence.dto.response.TotalUserDTO;
import apptive.attendence.entity.Attendence;
import apptive.attendence.entity.Roles;
import apptive.attendence.entity.Schedule;
import apptive.attendence.entity.Users;
import apptive.attendence.repository.AttendenceRepository;
import apptive.attendence.repository.ScheduleRepository;
import apptive.attendence.repository.UsersRepository;
import apptive.attendence.service.AdminScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@Transactional
public class AdminAttendTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AttendenceRepository attendenceRepository;

    @Autowired
    private AdminScheduleService adminScheduleService;

    private Long scheduleId;
    private Long attendUserId;
    private Long absentUserId;

    @BeforeEach
    void setUp() {
        // 일정
        Schedule schedule = scheduleRepository.save(Schedule.builder()
                .title("정기 모임")
                .scheduleDate(LocalDate.parse("2025-06-24"))
                .startTime(LocalTime.parse("18:00"))
                .endTime(LocalTime.parse("19:00"))
                .permissionPenalty(5)
                .nonPermissionPenalty(10)
                .build());
        scheduleId = schedule.getId();

        // 참석 인원
        Users attendUser = usersRepository.save(Users.builder()
                .name("참석자")
                .email("attend1@academy.ac.kr")
                .part("백엔드")
                .department("컴퓨터공학과")
                .generation(58)
                .role(Roles.USER)
                .build());
        attendUserId = attendUser.getId();

        // 불참 인원
        Users absentUser = usersRepository.save(Users.builder()
                .name("불참자")
                .email("absent1@academy.ac.kr")
                .part("기획")
                .department("경영학과")
                .generation(58)
                .role(Roles.USER)
                .build());
        absentUserId = absentUser.getId();

        // 출결 기록
        attendenceRepository.save(Attendence.builder()
                .users(attendUser)
                .schedule(schedule)
                .attendence(true)
                .build());

        attendenceRepository.save(Attendence.builder()
                .users(absentUser)
                .schedule(schedule)
                .attendence(false)
                .absentReason("개인 사정")
                .build());
    }

    @Test
    public void AttendUserTest() {
        TotalUserDTO totalUsers = adminScheduleService.getTotalAttendence(scheduleId);

        Assertions.assertNotNull(totalUsers);
        Assertions.assertEquals(1,  totalUsers.attendees().size());
        Assertions.assertEquals(1, totalUsers.attendees().size());

        Assertions.assertEquals(attendUserId, totalUsers.attendees().get(0).userId());
        Assertions.assertEquals(absentUserId, totalUsers.nonAttendees().get(0).userId());
    }
}
