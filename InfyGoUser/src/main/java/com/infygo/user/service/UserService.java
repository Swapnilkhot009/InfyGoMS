package com.infygo.user.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infygo.user.DTO.UserDTO;
import com.infygo.user.entity.User;
import com.infygo.user.exception.AlreadyExistsException;
import com.infygo.user.exception.InvalidCredentialsException;
import com.infygo.user.exception.NotExstingException;
import com.infygo.user.repository.UserRepository;



@Service
@PropertySource("classpath:validation.properties")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Environment environment;
	
	//Register new user 
	public UserDTO registerUser(UserDTO userDTO) throws AlreadyExistsException{
		User user = modelMapper.map(userDTO, User.class);
		if(userRepository. getuserIdByName(user.getName())!=null)
			throw new AlreadyExistsException(environment.getProperty("Already.Exists"));
		userRepository.saveAndFlush(user);
		return modelMapper.map(user, UserDTO.class);	
	}
	
	//Login for user based on userId and password
	public String userLogin(String userId, String passward) throws InvalidCredentialsException, NotExstingException{
		
	User dbUser=userRepository.findByUserId(userId);
	if(dbUser==null)
		throw new NotExstingException(environment.getProperty("Not.A.User"));
	if(dbUser.getPassword().equals(passward))
		return environment.getProperty("Logged.In");
	else 
		throw new InvalidCredentialsException(environment.getProperty("Invalid.Credentials"));
	}
	
	//Provides user details based on userId
	public UserDTO getUserDeailsById(String userId) throws NotExstingException {
		User user =userRepository.findById(userId).orElse(null);
		if(user == null)
			throw new NotExstingException(environment.getProperty("Not.A.User"));
		return modelMapper.map(user, UserDTO.class);
		
	}
}
