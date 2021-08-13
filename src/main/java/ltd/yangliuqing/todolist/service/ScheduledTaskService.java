package ltd.yangliuqing.todolist.service;

import lombok.extern.slf4j.Slf4j;
import ltd.yangliuqing.todolist.model.entity.RemindEntity;
import ltd.yangliuqing.todolist.repository.RemindRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** @author yang */
@Service
@Slf4j
public class ScheduledTaskService {
    private final RemindRepository remindRepository;

    private final EmailSenderService emailSenderService;

    public ScheduledTaskService(EmailSenderService emailSenderService, RemindRepository remindRepository) {
        this.emailSenderService = emailSenderService;
        this.remindRepository = remindRepository;
    }

    /** 每一分钟检查一次任务情况 */
    @Scheduled(cron = "1 0/1 * * * *")
    public void scheduleTask() {
        LocalDateTime now = LocalDateTime.now();
        var list = this.remindRepository.findAllByRemindTimeBeforeAndCompleteFlag(now, Boolean.FALSE);
        list.forEach(this::wrapEmailAndSendEmail);
    }

    /**
     * 包装Email消息以及发送Email消息
     *
     * @param remind 发送的事件
     */
    private void wrapEmailAndSendEmail(RemindEntity remind) {
        // 将事件更新为已完成状态
        this.remindRepository.updateCompleteFlagByRemindId(Boolean.TRUE, remind.getRemindId());

        // 发送邮件
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分");
        String datetime = formatter.format(remind.getRemindTime());
        String content = String.format("尊敬的yangliuqing:\n    您的任务'%s'已经到达提醒时间! 提醒时间为%s", remind.getDescription(),
                datetime);
        this.emailSenderService.sendTextEmail(content);
        log.info("Successfully send email.");
    }
}
