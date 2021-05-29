package com.stwijn.rest;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestAPITest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	@DisplayName("test if JSON type is returned by controller")
	void test_JSON() {	
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/in", String.class);
		assertTrue(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("test if ResponseEntity object is returned by controller")
	void test_ResponseEntity() {
		
		ResponseEntity<String> responseEntityObj = this.restTemplate.getForEntity("/in", String.class);
		assertTrue(responseEntityObj instanceof ResponseEntity);
	}
	
}
