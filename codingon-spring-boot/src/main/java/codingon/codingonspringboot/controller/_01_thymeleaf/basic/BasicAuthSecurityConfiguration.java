package codingon.codingonspringboot.controller._01_thymeleaf.basic;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
// jsr250Enabled = true는 controller단에서 allowed를 설정할 수 있다
//@Configuration
//@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        // HTTP 요청에 대한 권한 부여 적용
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/users").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());
        
        // HTTP 세션에 사용할 정책을 STATELESS로 설정하기 (REST API에서 설정해야 함.)
        // 스프링 부트 기본 옵션에서는 세션을 이용해서 로그인 로그아웃을 설정함.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 폼 로그인 사용할 건지
//        http.formLogin(withDefaults());
        
        // 기본 인증 사용
        http.httpBasic(withDefaults());

        // csrf 사용 해제
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    // UserDetailsService : 사용자별 데이터를 로드하는 코어 인터페이스
    // in memory user details manager
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 메모리에 저장
//        var user = User.withUsername("in28minutes").password("{noop}dummy").roles("USER").build();
//        var admin = User.withUsername("admin").password("{noop}dummy").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // 메모리에 저장
        var user = User.withUsername("in28minutes")
//                .password("{noop}dummy")
                .password("dummy").passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}dummy")
                .password("dummy").passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("ADMIN", "USER").build();
        var jdbcUserDetailManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailManager.createUser(user);
        jdbcUserDetailManager.createUser(admin);

        return jdbcUserDetailManager;
    }


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
