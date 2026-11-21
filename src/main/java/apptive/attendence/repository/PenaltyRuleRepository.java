package apptive.attendence.repository;

import apptive.attendence.entity.PenaltyRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRuleRepository extends JpaRepository<PenaltyRule, Integer> {
    public PenaltyRule findById(int id);
}
