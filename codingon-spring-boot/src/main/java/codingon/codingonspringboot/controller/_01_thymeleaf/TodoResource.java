package codingon.codingonspringboot.controller._01_thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODOS_LIST =
            List.of(new Todo("something", "somethingBook"),
                    new Todo("in28minutes","Learn AWS"),
                    new Todo("in28minutes", "Get AWS Certified"));
    @GetMapping("/todos")
    @ResponseBody
    public List<Todo> retrieveAllTodos() {
        return TODOS_LIST;
    }

    @GetMapping("/users/{username}/todos")
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