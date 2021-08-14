package ltd.yangliuqing.todolist.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/** @author 16573 */
@Data
@Accessors(chain = true)
public class Remind {
    private String description;

    private String remindTime;
}
