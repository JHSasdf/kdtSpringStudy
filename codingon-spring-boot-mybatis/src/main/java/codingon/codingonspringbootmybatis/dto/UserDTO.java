package codingon.codingonspringbootmybatis.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String nickname;
    private int no;
    // domain.User 와 다르게 "no" 항목을 추가함
    // 실제 테이블에 존재하는 컬럼은 아니지만 서비스 로직에서 no 정보를 활용할 예정
}
