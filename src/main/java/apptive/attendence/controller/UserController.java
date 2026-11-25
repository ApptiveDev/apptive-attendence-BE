package apptive.attendence.controller;

import apptive.attendence.dto.request.UserRequestDTO;
import apptive.attendence.dto.response.ProfileResponseDTO;
import apptive.attendence.dto.response.UserResponseDTO;
import apptive.attendence.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDTO userRequestDTO) {
        userService.signup(userRequestDTO);
        return ResponseEntity.ok().body("Signup Successful");
    }

    @GetMapping("/{user_id}/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long user_id) {
        ProfileResponseDTO profile = userService.getProfile(user_id);
        return ResponseEntity.ok().body(profile);
    }

    @PutMapping("/{user_id}/profile")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long userId, @RequestBody UserRequestDTO userRequestDTO) {
        ProfileResponseDTO newProfile = userService.updateProfile(userId, userRequestDTO);
        return ResponseEntity.ok().body(newProfile);
    }
}
