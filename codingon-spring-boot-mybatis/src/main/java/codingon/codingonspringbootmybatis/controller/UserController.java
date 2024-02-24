package codingon.codingonspringbootmybatis.controller;


import codingon.codingonspringbootmybatis.domain.User;
import codingon.codingonspringbootmybatis.dto.UserDTO;
import codingon.codingonspringbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 작업 순서 : sql 이용해 table 생성 -> 도메인 생성 -> dto -> mapper -> service -> controller
@Controller
public class UserController {
    // Create, Read

    // Autowired 쓰려면 Bean이어야한다.
    // Bean이려면 service 이거나 configure 여야함
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getUsers(Model model) {
        List<UserDTO> users = userService.getUserList();
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", users);
        return "user";
    }

    // postman 사용해서 값 insert 할 것
    @PostMapping("/user")
    public String userInsert(@RequestParam String name, @RequestParam String nickname) {
        User user = new User();
        user.setName(name);
        user.setNickname(nickname);

        userService.insertUser(user);
        // redirect 한 결과 return
        return "redirect:/community";
    }
}
