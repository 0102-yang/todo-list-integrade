package ltd.yangliuqing.todolist.service;

import lombok.extern.slf4j.Slf4j;
import ltd.yangliuqing.todolist.model.entity.UserEntity;
import ltd.yangliuqing.todolist.model.vo.RegistryUser;
import ltd.yangliuqing.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/** @author 16573 */
@Slf4j
@Service
public class RegistryService {
    @Autowired private UserRepository userRepository;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registryUser(RegistryUser registryUser) throws Exception {
        if (this.userRepository.existsByUsername(registryUser.getUsername())) {
            throw new Exception("用户名已经存在");
        }

        String encryptedPassword = this.bCryptPasswordEncoder.encode(registryUser.getPassword());

        UserEntity user = new UserEntity();
        user.setUsername(registryUser.getUsername());
        user.setPassword(encryptedPassword);
        user.setEmail(registryUser.getEmail());

        log.info("用户{}被注册了", registryUser.getUsername());

        this.userRepository.save(user);
    }
}
