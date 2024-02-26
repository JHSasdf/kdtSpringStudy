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
@RequestMapping("/community") // 밑의 모든 주소값에 board를 추가함.
public class CommunityController {
    // Create, Read

    // Autowired 쓰려면 Bean이어야한다.
    // Bean이려면 service 이거나 configure 여야함
    // 객체 생성 시에 사용할 방식 (직접 필드를 쓸건지, 필드에 setter를 쓸건지, 필드를 final로 하고 생성자를 쓸건지)

    // dependency 정의
    @Autowired
    CommunityService communityService;

    // 생성자 주입
//        private final CommunityService communityService;
// 생성자 주입은 Autowired안써도 자동와이어링이 된다.
//    public CommunityController(CommunityService communityService) {
//        this.communityService = communityService;
//    }
    @GetMapping("")
    public String getCommunity(Model model) {
        List<CommunityDTO> posts = communityService.getPostList();
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", posts);
        return "community";
    }

    @GetMapping("/{id}")
    public String getQueriedCommunity(Model model,  @PathVariable String id) {
        List<CommunityDTO> posts = communityService.getQueryList(id);
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", posts);
        return "community";
    }

    @GetMapping("/search/{id}")
    public String getSearchedCommunity(Model model,  @PathVariable String id) {
        List<CommunityDTO> posts = communityService.searchQuery(id);
        // mybatis를 통해 전달된 정보를 받아와 users 에 넣고
        model.addAttribute("list", posts);
        return "community";
    }

    // postman 사용해서 값 insert 할 것
    @PostMapping("")
    @ResponseBody
    public String createPost(@RequestBody Community community) {
//        Community community = new Community();
//        community.setTitle(title);
//        community.setContent(content);
//        community.setWriter(writer);

        communityService.createPost(community);
        // redirect 한 결과 return
        return "redirect:/community";
    }

    @PatchMapping("")
    @ResponseBody
    public String updatePost(@RequestBody Community community) {
        communityService.updatePost(community);
        return "redirect:/community";
    }

    @DeleteMapping("")
    @ResponseBody
    public String deletePost(int id) {
        communityService.deletePost(id);
        return "redirect:/community";
    }
 }
