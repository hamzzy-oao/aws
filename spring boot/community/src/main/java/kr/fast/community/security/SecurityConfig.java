package kr.fast.community.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final JwtProvider jwtProvider;

    public SecurityConfig(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 인증이 필요한 URL 또는 인증이 필요없는 URL을 설정
            .authorizeHttpRequests(auth -> auth
            		// /api/auth/로 시작하는 모든 URL은 모두 접근이 가능
                .requestMatchers("/api/auth/**").permitAll()
                // /api/admin/로 시작하는 모든 URL은 로그인이 필요
                .requestMatchers("/api/admin/**").authenticated()
                //로그인한 사용자만 접근할 수 있는 URL 설정
                .requestMatchers(HttpMethod.POST,
                		"/api/post/" // 게시글 등록
                		).authenticated()
                .requestMatchers(HttpMethod.PUT,
                		"/api/post/*" // 게시글 수정
                		).authenticated()
                .requestMatchers(HttpMethod.DELETE,
                		"/api/post/*" // 게시글 삭제
                		).authenticated()
                //그 외 다른 URL들은 인증이 필요함
                .anyRequest().permitAll()
            )
            //필터 추가
            .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}