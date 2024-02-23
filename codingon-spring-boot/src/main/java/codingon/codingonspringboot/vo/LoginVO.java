package codingon.codingonspringboot.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LoginVO {
    private String id;
    private String password;

    public LoginVO(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // 동등성 구현을 위한 코드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginVO userVO = (LoginVO) o;
        // 여기서 모든 필드 비교
        return Objects.equals(id, userVO.id) && Objects.equals(password, userVO.password);
    }

    @Override
    public int hashCode() {
        // 필드들을 이용한 해시코드 생성
        return Objects.hash(id, password);
    }
}
