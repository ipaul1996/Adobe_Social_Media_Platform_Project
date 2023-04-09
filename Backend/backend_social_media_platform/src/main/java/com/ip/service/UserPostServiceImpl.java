package com.ip.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.dto.UserDTO;
import com.ip.dto.UserDTOV1;
import com.ip.exception.UserException;
import com.ip.model.User;
import com.ip.repository.PostRepo;
import com.ip.repository.UserRepo;

@Service
public class UserPostServiceImpl implements UserPostService {
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private PostRepo pRepo;

	@Override
	public User createUser(UserDTO dto) throws UserException {
		
		Optional<User> op =  uRepo.findByEmail(dto.getEmail());
		
		if(op.isPresent()) {
			throw new UserException("User with email: " + dto.getEmail() + " already exists");
		}
		
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setBio(dto.getBio());
		
		return uRepo.save(user);
		
	}

	
	@Override
	public User getUser(Integer userId) throws UserException {
		
		Optional<User> op = uRepo.findById(userId);
		
		if(op.isEmpty()) {
			throw new UserException("Invalid user id");
		}
		
		return op.get();
	}

	
	
	@Override
	public User updateUser(Integer userId, UserDTOV1 dto) throws UserException {
		
		Optional<User> op = uRepo.findById(userId);
		
		if(op.isEmpty()) {
			throw new UserException("Invalid user id");
		}
		
		User user = op.get();
		
		if(dto.getName().equals(null) && dto.getBio().equals(null)) {
			throw new UserException("Both name and bio can not be null");
		}
		
		if(!dto.getName().equals(null)) {
			user.setName(dto.getName());
		}
		
		if(!dto.getBio().equals(null)) {
			user.setBio(dto.getBio());
		}
		
		return uRepo.save(user);
	}

	
	
	@Override
	public User deleteUser(Integer userId) throws UserException {
		
		Optional<User> op = uRepo.findById(userId);
		
		if(op.isEmpty()) {
			throw new UserException("Invalid user id");
		}
		
		uRepo.delete(op.get());
		
		return op.get();
	}

	
	
	@Override
	public Integer getTotalNumberOfUsers() throws UserException {
		
		List<User> users =  uRepo.findAll();
		
		if(users.isEmpty()) {
			throw new UserException("No user found");
		}
		
		return users.size();
	}

	
	
	@Override
	public List<User> getTop5ActiveUsers() throws UserException {
		
		List<User> users =  uRepo.findAll();
		
		if(users.isEmpty()) {
			throw new UserException("No user found");
		}
		
		List<User> top5Users =  users.stream()
									 .sorted((u1, u2) -> Integer.compare(u2.getPosts().size(), u1.getPosts().size()))
									 .limit(5)
									 .collect(Collectors.toList());
		
		return top5Users;
	}

	
	
	
	
	
	
	

}
