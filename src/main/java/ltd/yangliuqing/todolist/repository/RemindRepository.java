package ltd.yangliuqing.todolist.repository;

import ltd.yangliuqing.todolist.model.entity.RemindEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/** @author 16573 */
@Repository
public interface RemindRepository
                extends CrudRepository<RemindEntity, Integer>, JpaSpecificationExecutor<RemindEntity> {

        /**
         * 返回比指定提醒时间更前的提醒任务
         *
         * @param remindTime   时间
         * @param completeFlag 事件是否被提醒过
         * @return 所有任务
         */
        List<RemindEntity> findAllByRemindTimeBeforeAndCompleteFlag(LocalDateTime remindTime, Boolean completeFlag);

        /**
         * 通过事件的id更新已经提醒的标志
         *
         * @param completeFlag 事件更新的标志
         * @param remindId     事件的id
         */
        @Modifying
        @Transactional
        @Query(value = "update remind set complete_flag = ?1 where remind_id = ?2", nativeQuery = true)
        void updateCompleteFlagByRemindId(Boolean completeFlag, Integer remindId);
}
