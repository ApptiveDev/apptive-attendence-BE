package apptive.attendence.controller;

import apptive.attendence.service.AdminAttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
 @RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAttendController {

    private final AdminAttendService adminAttendService;

    @GetMapping("/create-code")
    public ResponseEntity<String> createCode() {
        String code = adminAttendService.createCode();
        return ResponseEntity.ok(code);
    }
}
