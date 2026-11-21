package apptive.attendence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AdminAttendService {
    public String createCode() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        String uuidCode = uuid.substring(0,4);
        return uuidCode;
    }
}
