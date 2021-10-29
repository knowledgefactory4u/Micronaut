package com.knf.dev.demo.data;

import java.util.HashMap;
import java.util.Map;

import com.knf.dev.demo.model.User;

import jakarta.inject.Singleton;

@Singleton
public class UserData {

	public User getUserById(Long id) {
		User user = genearteDummyData().get(id);
		return user;
	}

	// Generate Dummy Users
	private Map<Long, User> genearteDummyData() {
		Map<Long, User> dummyUsers = new HashMap<>();
		User user1 = new User("user-1", "user-1@gmail.com");
		User user2 = new User("user2", "user2@gmail.com");
		User user3 = new User("user3", "user3@gmail.com");
		dummyUsers.put(22l, user1);
		dummyUsers.put(13l, user2);
		dummyUsers.put(19l, user3);
		return dummyUsers;
	}
}
