package com.example.findingFlatmates.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private int id;
	private String name;
	private String email;
	private String phoneNo;
	private String profilePic;
}
