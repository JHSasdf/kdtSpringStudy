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

    @GetMapping("/check")
    @ResponseBody
    public boolean checkName(@RequestParam String name, Model model) {
        boolean result = userService.checkName(name);
        return result;
    }

    @PostMapping("/")
    @ResponseBody
    public String insertUser(@RequestBody UserEntity user) {
        String newName = userService.insertUser(user);
        return newName + " Success";
    }

    @GetMapping("/search/name")
    public String searchName(String name, Model model) {
        List<UserDTO> users = userService.searchName(name);
        model.addAttribute("list", users);
        return "user";
    }

    @GetMapping("/search/nameornickname")
    public String searchNameOrNickname(String name, Model model) {
        List<UserDTO> users = userService.searchNameOrNickname(name);
        model.addAttribute("list", users);
        return "user";
    }
}
