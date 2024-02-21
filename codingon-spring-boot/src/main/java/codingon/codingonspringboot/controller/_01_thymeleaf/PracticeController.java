package codingon.codingonspringboot.controller._01_thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PracticeController {
    @GetMapping("/practice/thymeleaf1")
    public String getThymeleaf(Model model) {
        model.addAttribute("age", 19);
        model.addAttribute("age2", 37);
        List<Person> personList = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            personList.add(new Person(i, "person"+i));
        }
        model.addAttribute("personList", personList);
        return "_00_practice/practice";
    }
}

// lombok에 있는 annotation으로 이거 쓰면 getter, setter 없어도 됨
@Getter
@Setter
@AllArgsConstructor // 모든 필드 값을 매개변수로 받는 생성자
class Person {
    private int age;
    private String name;

}