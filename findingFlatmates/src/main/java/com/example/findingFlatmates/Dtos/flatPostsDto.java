package com.example.findingFlatmates.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class flatPostsDto {
	private int id;
	private String ptitle;
	private String desc;
	private long price;
	private String ppics;
	private String city;
	private String fulladdress;
	private String tenenttype;
	private int noofperson;
	private String roomType;
	private UserDto user;
}
