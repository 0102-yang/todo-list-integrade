package ltd.yangliuqing.todolist.controller;

import ltd.yangliuqing.todolist.model.vo.LoginUser;
import ltd.yangliuqing.todolist.service.ErrorService;
import ltd.yangliuqing.todolist.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/** @author 16573 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired private HomePageService homePageService;

    @Autowired private ErrorService errorService;

    @GetMapping
    public String getLogin() {
        return "login";
    }

    @PostMapping
    public ModelAndView postLogin(@Valid LoginUser loginUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            var errorList = this.errorService.getErrorList(bindingResult);
            modelAndView.addObject("errorList", errorList);
            modelAndView.setViewName("login");
            return modelAndView;
        }

        return this.homePageService.loadHomePageByUsername(loginUser.getUsername());
    }
}
