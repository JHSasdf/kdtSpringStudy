package codingon.codingonspringbootjpa.dto;

import codingon.codingonspringbootjpa.entity.JpaCommunityEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JpaCommunityDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private JpaCommunityEntity.UserType type;
}

//@Builder
// - 빌더 패턴
// - 디자인 패턴의 "생성" 패턴 중 하나
// - 객체를 생성하는 방법 중 하나
// 1) set 함수를 이용하는 방법
// 2) 생성자에 주입하는 방법
// 빌더를 사용하게 되면 setter 없이 생성 가능
// 빌더 패턴은 복잡한 객체의 생성 과정 및 표현 방법을 분리해 동일한 생성 절차에서
// 서로 다른 표현 결과를 만들 수 있게 하는 패턴 
