package com.example.findingFlatmates.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private int id;
	@NotBlank(message = "Bhai yaar name toh likh apna")
	private String name;
	@Email(message = "Email id is not valid please recheck email.")
	private String email;
	@NotBlank(message = "Please enter your valid phone number.")
	@Pattern(regexp = "^[789]\\d{9}$",
		    message = "Invalid phone number. Should start with either 7, 8 or 9 and should contain 10 numbers.")
	private String phoneNo;
	private String profilePic;
}
