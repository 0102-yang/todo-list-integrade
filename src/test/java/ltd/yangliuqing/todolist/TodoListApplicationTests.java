package ltd.yangliuqing.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.yangliuqing.todolist.service.EmailSenderService;

@SpringBootTest
class TodoListApplicationTests {
    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void contextLoads() {
        emailSenderService.sendTextEmail("test");
    }

}
