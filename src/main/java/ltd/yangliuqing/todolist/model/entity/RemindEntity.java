package ltd.yangliuqing.todolist.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/** @author 16573 */
@Data
@Entity
@Table(name = "remind")
@Accessors(chain = true)
public class RemindEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remind_id")
    private Integer remindId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "remind_time")
    private LocalDateTime remindTime;

    @Column(name = "complete_flag")
    private Boolean completeFlag = Boolean.FALSE;
}
