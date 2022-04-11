package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.ServiceImpl;

@Component
public class UpdateUserProcessor implements Processor{
	
	@Autowired
	ServiceImpl serviceImpl;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		
		  User user = exchange.getIn().getBody(User.class);
		  
		 
		  String userId = exchange.getIn().getHeader("userId").toString();
		  
		  exchange.getMessage().setBody(serviceImpl.UpdateUser(user, userId));
		 
		
	}

}