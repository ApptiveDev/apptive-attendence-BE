package apptive.attendence.controller;

import apptive.attendence.dto.request.PenaltyRequestDTO;
import apptive.attendence.dto.response.PenaltyResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> allUsers = adminUserService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/penalties")
    public ResponseEntity<List<PenaltyResponseDTO>> getAllPenalties() {
        List<PenaltyResponseDTO> allPenalties = adminUserService.getAllUsers();

    }

    @PostMapping("/penalty")
    public ResponseEntity<PenaltyResponseDTO> addPenalty(@RequestBody PenaltyRequestDTO penaltyRequestDTO) {
        PenaltyResponseDTO penalties = adminUserService.addPenalty(penaltyRequestDTO);
        return ResponseEntity.ok(penalties);
    }

    @DeleteMapping("/{penalty-id}")
    public ResponseEntity<String> deletePenalty(@PathVariable("penalty-id") long penalty_id) {
        adminUserService.deletePenalty(penalty_id);
        return ResponseEntity.ok("Delete penalty with id " + penalty_id);
    }
}
