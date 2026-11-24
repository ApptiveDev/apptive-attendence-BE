package apptive.attendence.security;

import apptive.attendence.entity.Users;
import apptive.attendence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return UserInfoDetail(user.getEmail(), user.getPassword(), user.getRole());
    }
}
