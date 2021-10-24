package org.knf.dev.demo.repository;

import org.knf.dev.demo.entity.User;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;


@Repository
public interface UserRepository extends CrudRepository<User,Long>{

}