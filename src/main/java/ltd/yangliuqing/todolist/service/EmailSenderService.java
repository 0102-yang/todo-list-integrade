package ltd.yangliuqing.todolist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** @author yang */
@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${yangliuqing.mail}")
    private String to;

    @Value("${yangliuqing.subject}")
    private String subject;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 发送邮件
     *
     * @param to      收件人地址
     * @param subject 主题
     * @param content 内容
     */
    public void sendTextEmail(String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.from);
        message.setTo(this.to);
        message.setSubject(this.subject);
        message.setText(content);
        this.javaMailSender.send(message);
    }
}
