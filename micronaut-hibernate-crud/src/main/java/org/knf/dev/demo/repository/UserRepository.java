package org.knf.dev.demo.repository;

import java.util.List;
import java.util.Optional;
import org.knf.dev.demo.entity.User;

public interface UserRepository {
	Optional<User> findById(Long id);

	User save(User user);

	void deleteById(Long id);

	List<User> findAll();

	void update(Long id, User user);
}