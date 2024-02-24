package codingon.codingonspringbootmybatis.service;

import codingon.codingonspringbootmybatis.domain.Community;
import codingon.codingonspringbootmybatis.domain.User;
import codingon.codingonspringbootmybatis.dto.CommunityDTO;
import codingon.codingonspringbootmybatis.dto.UserDTO;
import codingon.codingonspringbootmybatis.mapper.CommunityMapper;
import codingon.codingonspringbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
// - 스프링 부트에게 서비스 계층임을 알림
// - 해당 어노테이션이 없으면 컨트롤러에서 서비스 클래스를 찾을 수 없음
@Service
public class CommunityService {
    // @Autowired
    // - 의존성 주입 (쉽게 말하면, 원하는 객체를 직접 생성하지 않고도 사용할 수 있도록 함)
    @Autowired
    CommunityMapper communityMapper;

    // getUserList()
    // - controller 에서 전체 조회
    // - mapper의 retriveAll() 메소드 실행
    public List<CommunityDTO> getPostList() {
        List<Community> communities = communityMapper.getAllPosts();
        List<CommunityDTO> result = new ArrayList<>();
        for (Community community: communities) {
            // case1. builder 패턴 사용하지 않는 경우
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(user.getId());
//            userDTO.setName(user.getName());
//            userDTO.setNickname(user.getNickname());
//            userDTO.setNo(user.getId() + 100);

            //////////////////////////////////////////////
            // case2. builder 패턴 사용하는 경우
            CommunityDTO communityDTO = CommunityDTO.builder()
                    .id(community.getId())
                    .title(community.getTitle())
                    .content(community.getContent())
                    .writer(community.getWriter())
                    .registerd(community.getRegisterd())
                    .build();
            result.add(communityDTO);
        }
        return result;
    }

    public void createPost(Community community) {
        communityMapper.createPost(community);
    }

    public void updatePost(Community community) {
        communityMapper.updatePost(community);
    }

    public void deletePost(int id) {
        communityMapper.deletePost(id);
    }

    public List<CommunityDTO> getQueryList(String query) {
        List<Community> communityList = communityMapper.getQuery(query);
        List<CommunityDTO> result = new ArrayList<>();
        for (Community community: communityList) {
            CommunityDTO communityDTO = CommunityDTO.builder()
                    .id(community.getId())
                    .title(community.getTitle())
                    .content(community.getContent())
                    .writer(community.getWriter())
                    .registerd(community.getRegisterd())
                    .build();
            result.add(communityDTO);
        }

        return result;
    }
}
