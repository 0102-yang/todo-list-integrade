package ltd.yangliuqing.todolist.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ltd.yangliuqing.todolist.model.vo.Remind;
import ltd.yangliuqing.todolist.repository.RemindRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RemindRepository remindRepository;

    @GetMapping
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        List<Remind> res = new ArrayList<>();
        var list = remindRepository.findAll();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (var e : list) {
            if (e.getCompleteFlag()) {
                continue;
            }
            String datetime = dateTimeFormatter.format(e.getRemindTime());
            Remind remind = new Remind().setDescription(e.getDescription()).setRemindTime(datetime);
            res.add(remind);
        }

        modelAndView.addObject("completedRemindList", res);
        modelAndView.setViewName("/home");

        return modelAndView;
    }
}
