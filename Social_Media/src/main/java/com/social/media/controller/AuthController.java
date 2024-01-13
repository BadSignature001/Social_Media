package com.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.social.media.config.JwtProvider;
import com.social.media.models.User;
import com.social.media.repository.UserRepository;
import com.social.media.request.LoginRequest;
import com.social.media.response.AuthResponse;
import com.social.media.service.CustomUserDetailService;
import com.social.media.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService ;
	
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Autowired
	private CustomUserDetailService customerUserDetails ;
	
	@PostMapping("/regUser")
	public AuthResponse createUser(@RequestBody User user)throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail()) ;
		
		if(isExist != null)
		{
			throw new Exception("Already has a user by this email address") ;
		}
		
		User newuser = new User() ;
		newuser.setFirstname(user.getFirstname());
		newuser.setLastname(user.getLastname());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newuser) ;
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		String token = JwtProvider.generateToken(authentication) ;
		
		AuthResponse res = new AuthResponse(token,"Register Successful") ;
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword()) ;
		
		String token = JwtProvider.generateToken(authentication) ;
		
		AuthResponse res = new AuthResponse(token,"Signin Successful") ;
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email) ;
		if(userDetails == null) {
			throw new BadCredentialsException("invalid username") ;
			
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password incorrect") ;
			
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

}
