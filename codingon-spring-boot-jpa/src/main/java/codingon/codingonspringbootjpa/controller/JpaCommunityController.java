package codingon.codingonspringbootjpa.controller;

import codingon.codingonspringbootjpa.dto.JpaCommunityDTO;
import codingon.codingonspringbootjpa.dto.UserDTO;
import codingon.codingonspringbootjpa.entity.JpaCommunityEntity;
import codingon.codingonspringbootjpa.entity.UserEntity;
import codingon.codingonspringbootjpa.service.JpaCommunityService;
import codingon.codingonspringbootjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/community")
public class JpaCommunityController {
    @Autowired
    JpaCommunityService jpaCommunityService;

    @GetMapping("")
    public String getPosts(Model model){
        List<JpaCommunityDTO> posts = jpaCommunityService.getCommunityList();
        model.addAttribute("list", posts);

        return "japcommunity";
    }

    @PostMapping("")
    @ResponseBody
    public String insertPost(@RequestBody JpaCommunityEntity post) {
//         없으면 id는 default 값인 0이다.
        System.out.println(post.getId());
        String newName = jpaCommunityService.insertCommunity(post);
        return newName + " Success";
    }

    @GetMapping("/search")
    public String search(@RequestParam String word, Model model) {
        List<JpaCommunityDTO> posts = jpaCommunityService.search(word);
        model.addAttribute("list", posts);
        return "japcommunity";
    }

    @DeleteMapping("")
    @ResponseBody
    public boolean deletePost(@RequestParam Integer id) {
        boolean result = jpaCommunityService.deletePost(id);
        return result;
    }

}
