package codingon.codingonspringbootmybatis.mapper;

import codingon.codingonspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 스트링 부트에게 매퍼 역할을 하는 인터페이스임을 알림
public interface UserMapper {
    // sql 과 객체를 매핑해주는 곳으로 실제 DB 접속할 때 실행할 sql 문 정의

    // case 1. xml 파일 (mapper 파일) 참고 해서 sql문 실행
    List<User> retrieveAll();
    // UserMapper.xml 의 id 값과 함수 이름이 같아야 함.


    // case 2. xml 파일 (mapper 파일) 참고하지 않고, sql 을 실행
    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})") // 실제 사용되는 sql문
    void insertUser(User user); // 외부에서 호출할 때 사용되는 메소드 이름
    // void insertUser (String name, String nickname);
}
