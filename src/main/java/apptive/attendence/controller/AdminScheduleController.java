package apptive.attendence.controller;

import apptive.attendence.dto.response.AttendenceUserDTO;
import apptive.attendence.dto.request.ScheduleRequestDTO;
import apptive.attendence.dto.response.ScheduleResponseDTO;
import apptive.attendence.service.AdminScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminScheduleController {

    private final AdminScheduleService adminScheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO requestDTO) {

    }

    @PutMapping("/schedule")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(@RequestBody ScheduleRequestDTO requestDTO) {

    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable long scheduleId) {

    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<AttendenceUserDTO> attendenceSchedule(@PathVariable long scheduleId) {

    }
}
