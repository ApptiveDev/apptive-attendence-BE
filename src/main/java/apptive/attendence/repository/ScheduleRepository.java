package apptive.attendence.repository;

import apptive.attendence.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public Schedule findById(long id);
}
