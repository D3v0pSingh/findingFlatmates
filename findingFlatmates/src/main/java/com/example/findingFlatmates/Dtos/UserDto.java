package com.example.findingFlatmates.Dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.findingFlatmates.entities.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements UserDetails{
	private int id;
	
	@NotBlank(message = "Bhai yaar name toh likh apna")
	private String name;
	
	@Email(message = "Email id is not valid please recheck email.")
	private String email;
	
	@NotBlank(message = "Password cannot be empty please fill some kind of password.")
	private String password;
	
	@NotBlank(message = "Please enter your valid phone number.")
	@Pattern(regexp = "^[789]\\d{9}$",
		    message = "Invalid phone number. Should start with either 7, 8 or 9 and should contain 10 numbers.")
	private String phoneNo;
	
	private String profilePic;
	
	@Enumerated(value = EnumType.STRING)
	private Role role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public String getUsername() {
		return email;
	}
}
