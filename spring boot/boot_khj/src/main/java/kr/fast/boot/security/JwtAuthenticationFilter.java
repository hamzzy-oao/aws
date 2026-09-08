package kr.fast.boot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//컨트롤러에 들어가기 전 jwt토큰을 검증하는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
    	
    	//request에서 토큰을 가져옴
        String token = resolveToken(request);

        //토큰이 있고 유효하면
        if (token != null && jwtProvider.validateToken(token)) {
        	//토큰에서 사용자와 권한을 추출
            String username = jwtProvider.getUsername(token);
            String role = jwtProvider.getRole(token);

            //Spring Security 전용 인증 객체 생성
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,//사용자 식별자
                    null, //가졎증명. 보통은 null
                    List.of(new SimpleGrantedAuthority(role)) // 권한 목록
            );

            //SecurityContext에 위에서 생성한 인증 객체를 저장(로그인 처리완료)
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        //다음 필터를 실행
        filterChain.doFilter(request, response);
    }

    //request에서 토큰을 가져옴
    private String resolveToken(HttpServletRequest request){
    	/*	발급 받은 토큰으로 서버에 데이터를 요청할 때,
    	 * 	다음과 같이 토큰을 가져와서 서버로 데이터를 전송하여 요청
    	 * 
    	 * fetch("url",{
    	 * 		method : "method",
    	 * 		headers : {
    	 * 			"Content-Type" : "application/json",
    	 * 			"Authorization" : "Bearer 토큰" 
    	 * 		},
    	 * 		body : JSON.steingify(객체)
    	 * 	});
    	 * 
    	 * */
    	//request 헤더에 "Authorization"에 있는 값을 가져옴 => "Bearer 토큰"
        String bearerToken = request.getHeader("Authorization");
        //가져온 값이 "Bearer"로 시작하는 경우 토큰을 추출
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
        	//"Bearer"를 제외한 부분을 추출
            return bearerToken.substring(7);
        }
        return null;
    }
}