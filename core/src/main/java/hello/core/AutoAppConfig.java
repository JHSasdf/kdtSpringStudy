package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // basePackages 설정하면 컴포넌트 스캔하는 시작 위치를 지정한다. 이 패키지 포함 하위만 뒤짐
        //basePackages = {"hello.core", "hello.service"}
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class, // 이 클래스에 해당하는 패키지부터 찾음, 지정 안하면 해당 클래스의 패키지부터
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
