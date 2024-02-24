package codingon.codingonspringbootmybatis.controller;


import codingon.codingonspringbootmybatis.domain.Community;
import codingon.codingonspringbootmybatis.domain.User;
import codingon.codingonspringbootmybatis.dto.CommunityDTO;
import codingon.codingonspringbootmybatis.dto.UserDTO;
import codingon.codingonspringbootmybatis.service.CommunityService;
import codingon.codingonspringbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 작업 순서 : sql 이용해 table 생성 -> 도메인 생성 -> dto -> mapper -> service -> controller
@Controller
public class CommunityController {
    // Create, Read

    // Autowired 쓰려면 Bean이어야한다.
    // Bean이려면 service 이거나 configure 여야함
    @Autowired
    CommunityService communityService;

    @GetMapping("/community")
    public String getCommunity(Model model) {
        List<CommunityDTO> posts = communityService.getPostList();
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", posts);
        return "community";
    }

    @GetMapping("/community/query/{id}")
    public String getQueriedCommunity(Model model,  @PathVariable String id) {
        List<CommunityDTO> posts = communityService.getQueryList(id);
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", posts);
        return "community";
    }

    // postman 사용해서 값 insert 할 것
    @PostMapping("/community/create")
    public String createPost(@RequestParam String title, @RequestParam String content, @RequestParam String writer) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);
        community.setWriter(writer);

        communityService.createPost(community);
        // redirect 한 결과 return
        return "redirect:/community";
    }

    @PostMapping("/community/update")
    public String updatePost(@RequestBody Community community) {
        communityService.updatePost(community);
        return "redirect:/community";
    }

    @PostMapping("/community/delete")
    public String deletePost(int id) {
        communityService.deletePost(id);
        return "redirect:/community";
    }
 }
