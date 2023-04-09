package com.ip.service;

import java.util.List;

import com.ip.dto.UserDTO;
import com.ip.dto.UserDTOV1;
import com.ip.exception.UserException;
import com.ip.model.User;

public interface UserPostService {
	
	public User createUser(UserDTO dto) throws UserException;
	
	public User getUser(Integer userId) throws UserException;
	
	public User updateUser(Integer userId, UserDTOV1 dto) throws UserException;
	
	public User deleteUser(Integer userId) throws UserException;
	
	public Integer getTotalNumberOfUsers() throws UserException;
	
	public List<User> getTop5ActiveUsers() throws UserException;

}
