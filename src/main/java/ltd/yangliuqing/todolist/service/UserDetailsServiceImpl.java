package ltd.yangliuqing.todolist.service;

import ltd.yangliuqing.todolist.model.pojo.UserDetailsImpl;
import ltd.yangliuqing.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @author 16573 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = this.userRepository.findByUsername(username);
        if (u.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(u.get().getUsername());
        userDetails.setPassword(u.get().getPassword());
        return userDetails;
    }
}
