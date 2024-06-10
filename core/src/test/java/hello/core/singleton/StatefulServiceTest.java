package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleTon() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        // A사용자 만원주문
        int userAPrice = statefulService1.order("userA", 10000);
        // B사용자 20000원 주문
        int userBPrice = statefulService1.order("userA", 20000);
//        System.out.println(statefulService1.getPrice());
//        org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isNotEqualTo(10000);
        org.assertj.core.api.Assertions.assertThat(userAPrice).isEqualTo(10000);
        //  지역변수로 바꾸기
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}