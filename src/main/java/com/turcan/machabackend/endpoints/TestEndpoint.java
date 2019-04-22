package com.turcan.machabackend.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.turcan.machabackend.entity.Test;
import com.turcan.machabackend.entity.TestRepository;
import com.turcan.machabackend.entity.User;
import com.turcan.machabackend.entity.UserRepository;
import com.turcan.machabackend.response.TestResponse;
import com.turcan.machabackend.security.SecurityConstants;
/**
 * endpoints, belongs to the com.auth0.samples.authapi.springbootauthupdated.task package.
 */

@RestController
@RequestMapping("/test")
public class TestEndpoint {

	private TestRepository testRepository;
	private UserRepository userRepository;

	public TestEndpoint(TestRepository testRepository, UserRepository userRepository) {
		this.testRepository = testRepository;
		this.userRepository = userRepository;
	}
	
	@PostMapping("/new")
	public void addNewTest(@RequestBody Test testObject, HttpServletRequest req) {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
				.build()
				.verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
				.getSubject();
		User user = userRepository.findByUsername(username);
		testObject.setOwner(user);
		testRepository.save(testObject);
	}
	
	@GetMapping("/all")
	public List<TestResponse> getAll(HttpServletRequest req) {
		
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
				.build()
				.verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
				.getSubject();
		User user = userRepository.findByUsername(username);
		
		List<TestResponse> testResponses = new ArrayList<>();
		
		testRepository.findByOwner(user).forEach((entry) -> {
			Long ownerId = null;
			if (entry.getOwner() != null) {
				ownerId = entry.getOwner().getId();
			} 
			testResponses.add(new TestResponse(entry.getId(), entry.getDescription(), ownerId));
		});
		
		return testResponses;
	}
	
	@GetMapping("/{id}")
	public TestResponse getByID(@PathVariable long id) {
		Optional<Test> testObject = testRepository.findById(id);
		
		if (testObject.get() == null) {
			return null;
		}
		
		Long ownerID = null;
		if (testObject.get().getOwner() != null) {
			ownerID = testObject.get().getOwner().getId();
		}
		return new TestResponse(testObject.get().getId(), testObject.get().getDescription(), ownerID);
	}

}
