package apptive.attendence.repository;

import apptive.attendence.entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttendenceRepository extends JpaRepository<Attendence, Integer> {
    public Attendence findByUsersIdAndScheduleId(Long usersId, Long scheduleId);
}
