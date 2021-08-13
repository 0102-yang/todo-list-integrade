package ltd.yangliuqing.todolist.model.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/** @author 16573 */
@Data
public class RegistryUser {
    @NotBlank private String username;

    @NotBlank private String password;

    @NotBlank @Email private String email;
}
