package apptive.attendence.repository;

import apptive.attendence.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Long, Users> {
    public List<Users> allUsers();
}
