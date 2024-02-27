package codingon.codingonspringbootjpa.service;

import codingon.codingonspringbootjpa.dto.JpaCommunityDTO;
import codingon.codingonspringbootjpa.entity.JpaCommunityEntity;
import codingon.codingonspringbootjpa.repository.JpaCommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCommunityService {

    private final JpaCommunityRepository jpaCommunityRepository;

    @Autowired
    public JpaCommunityService(JpaCommunityRepository jpaCommunityRepository) {
        this.jpaCommunityRepository = jpaCommunityRepository;
    }

    // DateTime -> String
    // SimpleDateFormat(형식).format(dateTime 데이터)
    // .registered(new SimpleDateFormat("yyyy-mm--dd").format(for문의iteration.getRegistered()))
    public List<JpaCommunityDTO> getCommunityList() {
        // repository 에서 전체 조회가 가능하도록
        // JPA에서 정의되어있는 메소드 사용
        List<JpaCommunityEntity> jpaEntityList = jpaCommunityRepository.findAll();
        List<JpaCommunityDTO> result = new ArrayList<>();
        for(JpaCommunityEntity jpaCommunityEntity: jpaEntityList) {
            JpaCommunityDTO jpaCommunityDTO = JpaCommunityDTO.builder()
                    .id(jpaCommunityEntity.getId())
                    .title(jpaCommunityEntity.getTitle())
                    .content(jpaCommunityEntity.getContent())
                    .writer(jpaCommunityEntity.getWriter())
                    .type(jpaCommunityEntity.getType())
                    .build();
            result.add(jpaCommunityDTO);
        }

        return result;
    }


    public String insertCommunity(JpaCommunityEntity community) {
        // jpa save(T) 메소드 : T 는 Entity
        // - insert 할 때 사용
        // - 기존 entity를 업데이트 할 때도 사용
        // => 기본값(pk) 상태에 따라 다르게 동작
        //      pk 가 존재한다면 pk 와 연결된 entity update
        //      pk 가 없는 경우, 새로운 entity insert

        JpaCommunityEntity newPost = jpaCommunityRepository.save(community);
        // save를 했을 때 반환되는 객체는 Entity 객체
        return newPost.getTitle();
    }

    // Update
    // patch 를 할 때, id 랑 정보랑 따로 전달을 받는다.
    public void patchBoard(JpaCommunityDTO communityDTO) {
        JpaCommunityEntity board = jpaCommunityRepository.findById(communityDTO.getId()).orElseThrow(
                () -> new RuntimeException("board patch : id is Wrong!"));
    JpaCommunityEntity modified = JpaCommunityEntity.builder()
            .id(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .writer(board.getWriter())
            .type(board.getType())
            .build();
            jpaCommunityRepository.save(modified);
    }

    public void deleteBoard(int id) {
        JpaCommunityEntity board = jpaCommunityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("board patch : id is Wrong!"));
        jpaCommunityRepository.deleteById(board.getId());
    }
    public boolean deletePost(int id) {

        try {
            jpaCommunityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<JpaCommunityDTO> search(String word) {
        List<JpaCommunityEntity> posts = jpaCommunityRepository.findByTitleOrContentOrWriter(word, word, word);
        List<JpaCommunityDTO> result = new ArrayList<>();
        for(JpaCommunityEntity post: posts) {
            JpaCommunityDTO jpaCommunityDTO = JpaCommunityDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .type(post.getType())
                    .build();
            result.add(jpaCommunityDTO);
        }
        return result;
    }
}
