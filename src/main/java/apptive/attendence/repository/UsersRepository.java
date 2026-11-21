package apptive.attendence.repository;

import apptive.attendence.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public List<Users> allUsers();
    public Users findUserById(Long id);
    public void deleteById(Long id);
}
