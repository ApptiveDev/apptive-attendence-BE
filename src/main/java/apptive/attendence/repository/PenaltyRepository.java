package apptive.attendence.repository;

import apptive.attendence.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    public void deleteById(long id);
}
