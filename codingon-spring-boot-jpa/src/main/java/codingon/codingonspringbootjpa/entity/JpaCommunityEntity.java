package codingon.codingonspringbootjpa.entity;

// Entity
// - DB 에서 쓰이는 필드와 매핑이 되는 클래스 (DB 테이블과 대응되는 클래스) - MyBatis의 domain

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// 빌더, 엔티티 어노테이션 동시에 사용 -> 두개가 충돌해서 생성자가 만들어지지 않는 문제가 생김
// 원래는 Entity, Builder 각각에 맞는 생성자가 자동으로 생김.
// 빌더 -> 모든 필드를 사용하는 생성자 필요
// 엔티티 -> 기본 생성자 필요
// 그래서 ALLArgsConstructor와 NoArgsConstructor가 필요함 (Lombok의 도움을 받음)

@Getter
@Builder // 객체 생성 처리
@AllArgsConstructor // 모든 필드를 사용하는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자 (기본 생성자)
@Entity // 해당 클래스가 Entity 클래스임을 명시 (반드시 추가하기)
@Table(name = "jpacommunity") // 테이블 명과 클래스 명이 동일한 경우 생략 가능 (대문자 사용하려면 따로 설정 필요)
public class JpaCommunityEntity {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 사용 옵션
    private int id;

    @Column(nullable = false, length = 20) // varchar(20), length 안적으면 255
    private String title; // 만약 컬럼명이 다르자면 name="text"로 매핑시킬 컬럼명 적어주면 된다.
    // ex. @Column(nullable = false, length = 20, name = "another_column_name")

    // 타입: text(varchar X)
    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private String writer;

    // 참고. Enum 타입 지정 가능
    @Column()
    @Enumerated(EnumType.STRING)
    private UserType type;

    //이렇게 하면 자동생성
    @CreationTimestamp
    private Timestamp registered;

    public enum UserType {
        USER1, USER2
    }
}
