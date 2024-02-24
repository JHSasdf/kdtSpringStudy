package codingon.codingonspringbootmybatis.domain;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String nickname;
}
// 실제 데이터의 역할이므로 "테이블의 구조"와 동일해야 함
// maper = sql & 결과를 객체로 매핑되는 객체