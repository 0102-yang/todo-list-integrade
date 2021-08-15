package ltd.yangliuqing.todolist.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import ltd.yangliuqing.todolist.model.entity.RemindEntity;
import ltd.yangliuqing.todolist.model.vo.Remind;
import ltd.yangliuqing.todolist.repository.RemindRepository;

@Controller
@RequestMapping("/remind")
@Slf4j
public class RemindController {
    @Autowired
    private RemindRepository remindRepository;

    @PostMapping
    public ModelAndView postRemind(Remind remind) {
        ModelAndView modelAndView = new ModelAndView();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(remind.getRemindTime(), dateTimeFormatter);
        RemindEntity remindEntity = new RemindEntity().setDescription(remind.getDescription()).setRemindTime(date);
        remindRepository.save(remindEntity);
        log.info("Successfully add remind");

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
