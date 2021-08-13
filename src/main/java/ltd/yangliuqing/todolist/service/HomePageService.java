package ltd.yangliuqing.todolist.service;

import ltd.yangliuqing.todolist.model.entity.RemindEntity;
import ltd.yangliuqing.todolist.model.entity.UserEntity;
import ltd.yangliuqing.todolist.model.vo.Remind;
import ltd.yangliuqing.todolist.repository.RemindRepository;
import ltd.yangliuqing.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** @author 16573 */
@Service
public class HomePageService {
    @Autowired private UserRepository userRepository;

    @Autowired private RemindRepository remindRepository;

    public ModelAndView loadHomePageByUsername(String username) throws UsernameNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Optional<UserEntity> user = this.userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<RemindEntity> remindEntities =
                this.remindRepository.findAllByUserId(user.get().getUserId());

        // 筛选出未完成的事件清单
        List<Remind> completedReminds =
                remindEntities.stream()
                        .filter(RemindEntity::getCompleteFlag)
                        .map(this::getRemind)
                        .collect(Collectors.toList());

        modelAndView.setViewName("home");
        modelAndView.addObject("username", user.get().getUsername());
        modelAndView.addObject("email", user.get().getEmail());
        modelAndView.addObject("completedRemindList", completedReminds);

        return modelAndView;
    }

    private Remind getRemind(RemindEntity remindEntity) {
        Remind remind = new Remind();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分");

        remind.setDescription(remindEntity.getDescription());
        remind.setRemindTime(formatter.format(remindEntity.getRemindTime()));
        return remind;
    }
}
