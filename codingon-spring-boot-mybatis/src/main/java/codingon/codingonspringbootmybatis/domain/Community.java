package codingon.codingonspringbootmybatis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Community {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String registerd;
}
// 실제 데이터의 역할이므로 "테이블의 구조"와 동일해야 함
// maper = sql & 결과를 객체로 매핑되는 객체