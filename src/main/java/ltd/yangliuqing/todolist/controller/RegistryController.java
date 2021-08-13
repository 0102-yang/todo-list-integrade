package ltd.yangliuqing.todolist.controller;

import ltd.yangliuqing.todolist.model.vo.RegistryUser;
import ltd.yangliuqing.todolist.service.RegistryService;
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
@RequestMapping("/registry")
public class RegistryController {
    @Autowired private RegistryService registryService;

    @GetMapping
    public String getRegistry() {
        return "registry";
    }

    @PostMapping
    public ModelAndView postRegistry(@Valid RegistryUser registryUser, BindingResult bindingResult)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registry");
            return modelAndView;
        }

        this.registryService.registryUser(registryUser);

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
