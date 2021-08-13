package ltd.yangliuqing.todolist.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/** @author 16573 */
@Data
public class LoginUser {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
