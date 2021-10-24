package org.knf.dev.demo.controller;

import java.util.List;
import java.util.Optional;

import org.knf.dev.demo.entity.User;
import org.knf.dev.demo.repository.UserRepository;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@ExecuteOn(TaskExecutors.IO)
@Controller("/users")
public class UserController {

	protected final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Get
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Get("/{id}")
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	@Put("/{id}")
	public void updateUser(Long id, @Body User user) {
		userRepository.update(id, user);
	}

	@Post
	public User addUser(@Body User user) {
		return userRepository.save(user);
	}

	@Delete("/{id}")
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
