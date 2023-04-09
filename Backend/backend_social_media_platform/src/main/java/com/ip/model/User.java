package com.ip.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "User name can't be null")
	@NotBlank(message = "User name can't be blank")
	@NotEmpty (message = "User name can't be empty")
	@Size(min = 1, max = 50, message = "User name should have characters in the range 1 - 50")
	private String name;
	
	@Email
	private String email;
		
	@Size(min = 0, max = 200, message = "User bio should have characters in the range 0 - 200")
	private String bio;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	private LocalDateTime updatedAt;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Post> posts = new ArrayList<>();
	
	

}
