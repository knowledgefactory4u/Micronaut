package com.knf.dev.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.knf.dev.demo.entity.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import jakarta.inject.Singleton;

@Singleton
public class UserRepository {
	private final MongoClient mongoClient;

	public UserRepository(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	private MongoCollection<Document> getCollection() {
		return mongoClient.getDatabase("user-test").getCollection("user-test");
	}

	public User saveUser(User user) {
		Document document = new Document().append("firstName", user.getFirstName())
				.append("lastName", user.getLastName()).append("emailId", user.getEmailId());
		getCollection().insertOne(document);
		user.setId(document.getObjectId("_id").toString());
		return user;
	}

	public User updateUser(String id, User user) {
		Optional<Document> optionalDocument = findDocumentById(id);
		if (optionalDocument.isPresent()) {
			Document document = optionalDocument.get();
			document.put("firstName", user.getFirstName());
			document.put("lastName", user.getLastName());
			document.put("emailId", user.getEmailId());
			Bson bsonFilter = Filters.eq("_id", new ObjectId(id));
			getCollection().replaceOne(bsonFilter, document);
		}
		return user;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		MongoCursor<Document> cursor = getCollection().find().iterator();

		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				User user = new User();
				user.setLastName(document.getString("lastName"));
				user.setFirstName(document.getString("firstName"));
				user.setEmailId(document.getString("emailId"));
				user.setId(String.valueOf(document.getObjectId("_id")));
				users.add(user);
			}
		} finally {
			cursor.close();
		}
		return users;
	}

	public Optional<User> findUserById(String id) {
		return findDocumentById(id).map(this::mapUser);
	}

	public void deleteUserById(String id) {
		Bson bsonFilter = Filters.eq("_id", new ObjectId(id));
		getCollection().deleteOne(bsonFilter);
	}

	private Optional<Document> findDocumentById(String id) {
		Bson bsonFilter = Filters.eq("_id", new ObjectId(id));
		try (MongoCursor<Document> iterator = getCollection().find(bsonFilter).iterator()) {
			return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
		}
	}

	private User mapUser(Document document) {
		User user = new User();
		user.setId(document.getObjectId("_id").toString());
		user.setEmailId(document.getString("emailId"));
		user.setLastName(document.getString("lastName"));
		user.setFirstName(document.getString("firstName"));
		return user;
	}
}
