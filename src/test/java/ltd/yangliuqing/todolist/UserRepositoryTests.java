package ltd.yangliuqing.todolist;

import ltd.yangliuqing.todolist.model.entity.UserEntity;
import ltd.yangliuqing.todolist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired private UserRepository userRepository;

    @Test
    void idIdentityTests() {
        UserEntity userEntity =
                new UserEntity().setUsername("Yang").setEmail("1234565").setPassword("1234");
        this.userRepository.save(userEntity);

        var u = this.userRepository.findById(1);
        Assert.isTrue(u.isPresent(), "Identity strategy is not valid.");
    }
}
