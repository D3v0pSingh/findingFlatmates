package com.example.findingFlatmates.Dtos;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class flatPostsDto {
	private int id;
	@NotEmpty(message = "Please add a title for your post.")
	@Size(min = 10, message = "Enter title of atleast 10 characters.")
	private String ptitle;
	@NotBlank(message = "Add some description for your post.")
	private String desc;
	@NotNull(message = "Enter the price properly it cannot be blank.")
	@Min(value = 2000, message = "Price should be greater than 2000")
	private Long price;
	private String ppics;
	@NotBlank(message = "Enter the city properly.")
	private String city;
	@NotBlank
	private String fulladdress;
	@NotBlank(message = "Please specify male or female")
	private String tenenttype;
	@NotNull(message = "Enter the number of Person you want to stay with you.")
	private int noofperson;
	private String roomType;
	private UserDto user;
}
