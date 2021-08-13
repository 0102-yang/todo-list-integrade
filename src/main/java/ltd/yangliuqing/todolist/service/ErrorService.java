package ltd.yangliuqing.todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/** @author 16573 */
@Service
public class ErrorService {
    public List<String> getErrorList(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<>();
        var errors = bindingResult.getAllErrors();
        for (var error : errors) {
            errorList.add(error.getDefaultMessage());
        }
        return errorList;
    }
}
