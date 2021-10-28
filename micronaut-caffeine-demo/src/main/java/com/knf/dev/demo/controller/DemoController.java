package com.knf.dev.demo.controller;

import com.knf.dev.demo.cacheprovider.CompanyDataProvider;
import com.knf.dev.demo.model.Company;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/api")
public class DemoController {
	private final CompanyDataProvider companyDataProvider;

	public DemoController(CompanyDataProvider companyDataProvider) {
		this.companyDataProvider = companyDataProvider;
	}

	@Get("/{companyName}")
	public Company index(String companyName) {
		return new Company(companyName, companyDataProvider.
		companydetails(companyName));
	}
}
