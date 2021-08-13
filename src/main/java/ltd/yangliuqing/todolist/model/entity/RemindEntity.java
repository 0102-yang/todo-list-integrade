package ltd.yangliuqing.todolist.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/** @author 16573 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "remind")
@Accessors(chain = true)
public class RemindEntity implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remind_id")
    private Integer remindId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "remind_time")
    private LocalDateTime remindTime;

    @Column(name = "complete_flag")
    private Boolean completeFlag;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RemindEntity remind = (RemindEntity) o;

        return Objects.equals(this.remindId, remind.remindId);
    }

    @Override
    public int hashCode() {
        return 178679516;
    }
}
