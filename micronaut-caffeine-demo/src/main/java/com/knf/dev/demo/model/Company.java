package com.knf.dev.demo.model;

import java.util.Map;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Company {

	private String name;
	private Map<String, String> details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public Company(String name, Map<String, String> details) {
		super();
		this.name = name;
		this.details = details;
	}

	public Company() {

	}

}
