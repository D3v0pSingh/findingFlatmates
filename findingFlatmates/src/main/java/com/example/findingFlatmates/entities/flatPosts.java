package com.example.findingFlatmates.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class flatPosts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@ManyToOne
	private User user;
}
