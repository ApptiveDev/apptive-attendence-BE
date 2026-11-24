package apptive.attendence.repository;

import apptive.attendence.entity.Attendence;
import apptive.attendence.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendenceRepository extends JpaRepository<Attendence, Integer> {
    public Attendence findByUsersIdAndScheduleId(Long usersId, Long scheduleId);
    public List<Attendence> findByScheduleId(Long scheduleId);
    public long countByUsersId(Long usersId);
}
