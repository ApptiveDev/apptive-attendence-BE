package apptive.attendence;

import apptive.attendence.repository.AttendenceRepository;
import apptive.attendence.service.AdminAttendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeTest {
    @Autowired
    private AdminAttendService adminAttendService;

    @Test
    public void test() {
        String code = adminAttendService.createCode();
        System.out.println(code);
        Assertions.assertNotNull(code);
    }
}
