package codingon.codingonspringbootmybatis.mapper;

import codingon.codingonspringbootmybatis.domain.Community;
import codingon.codingonspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 스트링 부트에게 매퍼 역할을 하는 인터페이스임을 알림
public interface CommunityMapper {
    // sql 과 객체를 매핑해주는 곳으로 실제 DB 접속할 때 실행할 sql 문 정의

    // case 1. xml 파일 (mapper 파일) 참고 해서 sql문 실행
    List<Community> getAllPosts();
    // UserMapper.xml 의 id 값과 함수 이름이 같아야 함.


    // case 2. xml 파일 (mapper 파일) 참고하지 않고, sql 을 실행
    @Insert("insert into community(title, content, writer) values(#{title}, #{content}, #{writer})")
    // 실제 사용되는 sql문
    void createPost(Community community); // 외부에서 호출할 때 사용되는 메소드 이름
    // void insertUser (String name, String nickname);

    @Update("update kdt.community SET title = #{title}, content = #{content} WHERE id = #{id}")
    void updatePost(Community community);

    @Delete("DELETE FROM community WHERE id = #{id}")
    void deletePost(int id);

    @Select("SELECT * FROM community WHERE title = #{query}")
    List<Community> getQuery(String query);

    List<Community> searchQuery(String word);
}
