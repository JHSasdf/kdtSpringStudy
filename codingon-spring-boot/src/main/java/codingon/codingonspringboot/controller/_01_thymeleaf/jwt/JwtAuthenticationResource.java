package codingon.codingonspringboot.controller._01_thymeleaf.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

//@RestController
public class JwtAuthenticationResource {

    // 1. 기본 인증 사용해서 JWT 토큰 받기
    // 2-n. REST API에 대한 요청 인증하기 위해서 JWT 토큰을 Bearer 토큰으로 활용


    private JwtEncoder jwtEncoder;

    @Autowired
    public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication) {
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        // claims 생성
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .subject(authentication.getName())
                .claim("scope", createScope(authentication)) // authority
                .build();

        // jwtEncoderParameters 생성하고 이걸 인코딩하고 TokenValue 추출해서 return
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(Authentication authentication) {
        // 권한 받기
        return authentication.getAuthorities().stream() // list of Authority objects
                .map(a -> a.getAuthority()) // list of String
                .collect(Collectors.joining(" "));
    }
}

record JwtResponse(String token) {}