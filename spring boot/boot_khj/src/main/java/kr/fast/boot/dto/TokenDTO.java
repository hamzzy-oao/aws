package kr.fast.boot.dto;

import org.springframework.http.ResponseCookie;

public record TokenDTO(String accessToken, ResponseCookie refreshCookie) {}