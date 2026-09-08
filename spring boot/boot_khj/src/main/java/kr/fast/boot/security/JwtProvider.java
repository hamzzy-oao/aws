package kr.fast.boot.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider{

	
	private final SecretKey key;
    //어세스토큰 유지 시간
    private final long accessTokenInMs;
    //리프래쉬토큰 유지시간
    private final long refreshTokenInMs;

    //생성자
    public JwtProvider(
    		//@Value : application.proprties에 있는 값을 가져옴
    		//jwt.secret : JWT을 만들 때 사용될 문자열 => 노출되면 안됨.
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-in-ms}") long accessTokenInMs,
            @Value("${jwt.refresh-token-in-ms}") long refreshTokenInMs){
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenInMs = accessTokenInMs;
        this.refreshTokenInMs = refreshTokenInMs;
        
    }

    //토큰 생성
    public String createToken(String username, String role){
    	//토큰 생성 시간
        Date now = new Date();
        //토큰 만료 시간
        Date validity = new Date(now.getTime() + accessTokenInMs);
        

        return Jwts.builder()
                .subject(username)
                //토큰에 넣고 싶은 정보를 claim을 통해 넣어줌
                .claim("role", role)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact(); //JWTBuilder객체를 문자열로 만듬(토큰)
    }

    //토큰에서 소유주(username)을 가져옴
    public String getUsername(String token){
        return parseClaims(token).getSubject();
    }

    //토큰에서 권한을 가져옴(claim)
    public String getRole(String token){
        return parseClaims(token).get("role", String.class);
    }

    public boolean isRefreshToken(String refreshToken) {
    	return "refresh".equals(parseClaims(refreshToken).get("type"));
    }
    //토큰이 유효한 토큰인지 확인.
    public boolean validateToken(String token){
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //토큰을 이용하여 Claims 객체를 가져오는 기능
    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public String createRefreshToken(String username) {
    	//토큰 생성 시간
        Date now = new Date();
        //토큰 만료 시간
        Date validity = new Date(now.getTime() + refreshTokenInMs);
        

        return Jwts.builder()
                .subject(username)
                //토큰에 넣고 싶은 정보를 claim을 통해 넣어줌
                .claim("type","refresh")
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact(); //JWTBuilder객체를 문자열로 만듬(토큰)
    }
}