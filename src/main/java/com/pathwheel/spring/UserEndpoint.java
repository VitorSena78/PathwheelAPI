package com.pathwheel.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pathwheel.dao.UserDAO;
import com.pathwheel.exception.AuthenticationFailureException;
import com.pathwheel.exception.UserNotFoundException;
import com.pathwheel.io.Logger;
import com.pathwheel.jdbc.JdbcDataAccessObjectListener;
import com.pathwheel.jdbc.JdbcUserDAO;
import com.pathwheel.request.AuthenticateRequest;
import com.pathwheel.request.RegisterUserRequest;
import com.pathwheel.response.AuthenticateResponse;
import com.pathwheel.response.Codes;
import com.pathwheel.response.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserEndpoint implements JdbcDataAccessObjectListener {

	@Autowired
	private ObjectMapper objectMapper;
	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AuthenticateResponse  authenticate(@RequestBody String jsonRequest) {
		Logger.info("==[/v1/user/authenticate]=");
		Logger.info("rodando");
		AuthenticateResponse response = new AuthenticateResponse();
		try {

			AuthenticateRequest request = objectMapper.readValue(jsonRequest.getBytes("UTF-8"),
					AuthenticateRequest.class);
			Logger.info(request.toString());


			UserDAO dao = new JdbcUserDAO(this);
			response.setUser(dao.authenticate(request.getLogin(), request.getSecret()));


			response.setCode(200);
			response.setDescription("Success");

		} catch (UserNotFoundException e) {
			response.setCode(e.getCode());
			response.setDescription(e.getMessage());
			/*System.out.println(e.getCode());
			Logger.info(e.getMessage()+": e.getMessage()");*/
		} catch (AuthenticationFailureException e) {
			response.setCode(e.getCode());
			response.setDescription(e.getMessage());
			/*System.out.println(e.getCode());
			Logger.info(e.getMessage()+": e.getMessage()");*/
		} catch (Exception e) {
			response.setCode(Codes.INTERNAL_SERVER_ERROR);
			response.setDescription(e.getMessage());
			/*System.out.println(Codes.INTERNAL_SERVER_ERROR);
			Logger.info(e.getMessage()+": e.getMessage()");*/
		}
		Logger.info("<< " + response.toString());
		return response;
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RegisterUserResponse register(@RequestBody String jsonRequest) {
		Logger.info("==[/v1/user/register]=");
		Logger.info("rodando");
		Logger.info("jsonRequest: "+jsonRequest);
		RegisterUserResponse response = new RegisterUserResponse();
		try {
			RegisterUserRequest request = new ObjectMapper().readValue(jsonRequest.getBytes("UTF-8"), RegisterUserRequest.class);
			Logger.info(request.toString());
			UserDAO dao = new JdbcUserDAO(this);
			//response.setSpot(dao.register(request.getSpot()));
			response.setUser(dao.register(request.getUser(),request.getSecret()));
			response.setCode(200);
			response.setDescription("Success");

		}catch (Exception e){
			response.setCode(Codes.INTERNAL_SERVER_ERROR);
			response.setDescription(e.getMessage());
		}
		Logger.info("<< " + response.toString());
		return response;
	}

	@Override
	public void onErroJdbcDataAccessObject(Object sender, Exception e) {
		Logger.info(e.getMessage());
	}

	@Override
	public void onLogJdbcDataAccessObject(Object sender, String msg) {
		Logger.info(msg);
	}

}
