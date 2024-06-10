package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

public class AutoApConfigTest {
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean( MemberService.class);
        // memberServiceImpl 이런 이름으로 Bean 등록 <- class 이름이 MemberServiceImpl이라
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
