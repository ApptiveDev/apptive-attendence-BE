package apptive.attendence;

import apptive.attendence.dto.request.ScheduleRequestDTO;
import apptive.attendence.dto.response.ScheduleResponseDTO;
import apptive.attendence.entity.Schedule;
import apptive.attendence.repository.ScheduleRepository;
import apptive.attendence.service.AdminScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class AdminScheduleTest {

    @Autowired
    private AdminScheduleService adminScheduleService;

    @Test
    public void addScheduleTest() throws ParseException {
        ScheduleRequestDTO request = new ScheduleRequestDTO(
            "일정1",
                LocalDate.parse("2020-01-01"),
                LocalTime.parse("11:00"),
                LocalTime.parse("12:00"),
                5,
                10
        );
        ScheduleResponseDTO responseDTO = adminScheduleService.save(request);
        Assertions.assertNotNull(responseDTO);
    }

    @Nested
    class ScheduleTest {

        @Autowired
        private ScheduleRepository scheduleRepository;

        private Long scheduleId;

        @BeforeEach
        void setUp() {
            Schedule schedule = Schedule.builder()
                    .title("기존 일정")
                    .scheduleDate(LocalDate.parse("2025-06-24"))
                    .startTime(LocalTime.parse("18:00"))
                    .endTime(LocalTime.parse("19:00"))
                    .permissionPenalty(5)
                    .nonPermissionPenalty(10)
                    .build();
            Schedule savedSchedule = scheduleRepository.save(schedule);
            scheduleId = savedSchedule.getId();
        }

        @Test
        public void updateScheduleTest() {
            ScheduleRequestDTO newSchedule = new ScheduleRequestDTO(
                    "새 일정",
                    LocalDate.parse("2025-06-24"),
                    LocalTime.parse("18:00"),
                    LocalTime.parse("19:00"),
                    5,
                    10
            );

            ScheduleResponseDTO responseDTO = adminScheduleService.update(newSchedule, scheduleId);
            Assertions.assertNotNull(responseDTO);
        }

        @Test
        public void deleteScheduleTest() {
            scheduleRepository.deleteById(scheduleId);
        }
    }
}
