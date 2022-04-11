package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.ServiceImpl;

@Component
public class AddUserProcessor implements Processor{
	
	@Autowired
	ServiceImpl serviceImpl;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("inside Add processor");
		
		
		
		User user = exchange.getIn().getBody(User.class);
		
		//User dynamicUser = (User) exchange.getIn().getBody();
	
	//	User user = new User();
	  
		
		//user.setId("1000000");
		//user.setName("test");
		//user.setEmail("test@gmail.com");
		//user.setGender("female");
		//user.setStatus("inactive");
		
		exchange.getMessage().setBody(serviceImpl.addUser(user));
		
	}

}