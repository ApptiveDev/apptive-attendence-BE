package apptive.attendence.controller;

import apptive.attendence.dto.request.ScheduleRequestDTO;
import apptive.attendence.dto.response.ScheduleResponseDTO;
import apptive.attendence.dto.response.TotalUserDTO;
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
        ScheduleResponseDTO responseDTO = adminScheduleService.save(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(@RequestBody ScheduleRequestDTO requestDTO, @PathVariable Long scheduleId) {
        ScheduleResponseDTO responseDTO = adminScheduleService.update(requestDTO, scheduleId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable long scheduleId) {
        adminScheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok("Delete schedule successfully.");
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<TotalUserDTO> attendenceSchedule(@PathVariable long scheduleId) {
        TotalUserDTO totalUser = adminScheduleService.getTotalAttendence(scheduleId);
        return ResponseEntity.ok(totalUser);
    }
}
