package ltd.yangliuqing.todolist.service;

import lombok.extern.slf4j.Slf4j;
import ltd.yangliuqing.todolist.model.entity.RemindEntity;
import ltd.yangliuqing.todolist.model.entity.UserEntity;
import ltd.yangliuqing.todolist.repository.RemindRepository;
import ltd.yangliuqing.todolist.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** @author yang */
@Service
@Slf4j
public class ScheduledTaskService {
    private final UserRepository userRepository;

    private final RemindRepository remindRepository;

    private final EmailSenderService emailSenderService;

    final String SUBJECT = "邮件提醒服务";

    final String CONTENT = "尊敬的%s:\n    您的任务'%s'已经到达提醒时间! 时间为%s";

    public ScheduledTaskService(EmailSenderService emailSenderService, RemindRepository remindRepository,
            UserRepository userRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.remindRepository = remindRepository;
    }

    // todo: 需要将add和delete放出方法
    /**
     * 添加新的任务
     *
     * @param remind 任务
     */
    public void addTask(RemindEntity remind) {
        this.remindRepository.save(remind);
        log.info("Add a remind task, userId: " + remind.getUserId() + " remindId: " + remind.getRemindId());
    }

    /**
     * 删除一个任务
     *
     * @param remindId 任务的id
     */
    public void deleteTask(Integer remindId) {
        this.remindRepository.deleteById(remindId);
        log.info("Safely delete a remind, id: " + remindId);
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
        var user = this.userRepository.findById(remind.getUserId()).orElse(new UserEntity());
        String toEmail = user.getEmail();
        String content = this.formatEmailContent(user.getUsername(), remind.getDescription(), remind.getRemindTime());
        this.emailSenderService.sendTextEmail(toEmail, this.SUBJECT, content);
        log.info("Send email complete, target user: " + toEmail + " remind info: " + remind.getDescription());
    }

    private String formatEmailContent(String username, String description, LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分");
        return String.format(this.CONTENT, username, description, formatter.format(time));
    }
}
