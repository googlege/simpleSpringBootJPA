package de.homedev.springboot.jpav3.service;

import java.util.List;

import de.homedev.springboot.jpav3.entity.UserEntity;

public interface IUserService {
	public static final String SERVICE_NAME = "userServiceImpl";

	public List<UserEntity> save(List<UserEntity> list);

	public UserEntity findByUsernameAndPassword(String username, String userPassword);

	public void deleteAll();
}
