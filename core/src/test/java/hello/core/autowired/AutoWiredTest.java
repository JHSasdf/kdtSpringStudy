package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("NoBean1 = " + noBean1);
            // member는 스프링 빈이 아니다.
            // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨.
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("NoBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("NoBean3 = " + noBean3);
        }
    }
}
