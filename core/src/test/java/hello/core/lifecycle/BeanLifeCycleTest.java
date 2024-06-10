package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean("networkClient", NetworkClient.class);
        ac.close();
        System.out.println("ff");
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
//                (initMethod = "init", destroyMethod = "close") 이건 함수명을 등록해넣는 방법.
//                이거안쓰고 애노테이션 이용하는 걸 스프링에서 권장한다. 외부 라이브러리에서는 해당 기능을 이용한다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("httl://hello-spring.dev");
            return networkClient;
        }
    }
}
