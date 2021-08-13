package ltd.yangliuqing.todolist.repository;

import ltd.yangliuqing.todolist.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** @author 16573 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * 判断是否存在该用户名
     *
     * @param username 用户名
     * @return true则存在
     */
    boolean existsByUsername(String username);
}
