package com.knf.dev.demo.contoller;

import java.util.List;
import java.util.Optional;

import com.knf.dev.demo.entity.User;
import com.knf.dev.demo.repository.impl.UserRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/api/v1/users")
public class UserController {

	@Inject
	UserRepository userRepository;

	@Get
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Get("/{id}")
	public Optional<User> getUser(String id) {
		return userRepository.findUserById(id);
	}

	@Post
	public HttpResponse<User> addUser(@Body User user) {
		userRepository.saveUser(user);
		return HttpResponse.created(user);
	}

	@Put("/{id}")
	public HttpResponse<User> updateUser(String id, @Body User user) {
		userRepository.updateUser(id, user);
		return HttpResponse.created(user);
	}

	@Delete("/{id}")
	public HttpResponse<Void> delete(String id) {
		userRepository.deleteUserById(id);
		return HttpResponse.noContent();
	}

}
