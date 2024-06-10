package codingon.codingonspringboot.controller._01_thymeleaf;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODOS_LIST =
            List.of(new Todo("admin", "somethingBook"),
                    new Todo("in28minutes", "Learn AWS"),
                    new Todo("in28minutes", "Get AWS Certified"));

    @GetMapping("/todos")
    @ResponseBody
    public List<Todo> retrieveAllTodos() {
        return TODOS_LIST;
    }

    // @preAuthorize pathVariable의 username이 authentication.name과 같아야함
    // @PostAuthorize return하는 object의 username이 in28minutes여야함
    // RolesAllowed 이 roll만 해당 // configure 단에서 jsr250Enabled = true는 controller단에서 allowed를 설정할 수 있다
    // @Secured는 authority와 비교해서 확인한다
    // 많이쓰는 건 pre와 post
    // 표준은 jsr-250, rolesAllowed
    @GetMapping("/users/{username}/todos")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    @PostAuthorize("returnObject.username == 'in28minutes'")
    @RolesAllowed({"ADMIN", "USER"})
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ResponseBody
    public Todo retrieveTodosForASpecificUser(@PathVariable String username) {
        List<Todo> result = TODOS_LIST.stream().filter(e->e.username().equals(username)).toList();
        return result.get(0);
    }

    @PostMapping("/users/{username}/todos")
    @ResponseBody
    public void createTodoForASpecificUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("Create {} for {}", todo, username);

}

record Todo(String username, String description) {}

}