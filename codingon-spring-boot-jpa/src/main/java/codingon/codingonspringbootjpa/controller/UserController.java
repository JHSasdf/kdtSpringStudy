package codingon.codingonspringbootjpa.controller;

import codingon.codingonspringbootjpa.dto.UserDTO;
import codingon.codingonspringbootjpa.entity.UserEntity;
import codingon.codingonspringbootjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getUsers(Model model){
        List<UserDTO> users = userService.getUserList();
        model.addAttribute("list", users);

        return "user";
    }

    @GetMapping("/search")
    @ResponseBody
    public boolean getSearchUsers(@RequestParam String name, Model model) {
        boolean result = userService.SearchUser(name);
        return result;
    }

    @PostMapping("/")
    @ResponseBody
    public String insertUser(@RequestBody UserEntity user) {
        String newName = userService.insertUser(user);
        return newName + " Success";
    }
}
