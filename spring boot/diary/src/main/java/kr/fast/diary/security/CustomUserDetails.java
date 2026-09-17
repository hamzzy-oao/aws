package kr.fast.diary.security;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CustomUserDetails implements UserDetails {
	
	private final Long userId;
	private final String email;
	private final String nickName;
	private final Collection<? extends GrantedAuthority> authrities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authrities;
	}

	@Override
	public @Nullable String getPassword() {
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
