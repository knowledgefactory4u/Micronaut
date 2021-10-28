package com.knf.dev.demo.cacheprovider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;

@Singleton
@CacheConfig("companydetails")
public class CompanyDataProvider {
	Map<String, Map<String, String>> companydetails = 
	new HashMap<String, Map<String, String>>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("A-Company", 
			Map.of("Number of Employees", "300", "Rating", "4"));
			put("B-Company", 
			Map.of("Number of Employees", "200", "Rating", "4.2"));
		}
	};

	@Cacheable
	public Map<String, String> companydetails(String name) {
		try {
			TimeUnit.SECONDS.sleep(2);
			return companydetails.get(name);
		} catch (InterruptedException e) {
			return null;
		}
	}

}
