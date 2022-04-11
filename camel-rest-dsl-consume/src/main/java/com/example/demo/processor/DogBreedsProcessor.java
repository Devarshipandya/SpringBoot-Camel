package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.ServiceImpl;



@Component
public class DogBreedsProcessor implements Processor{
	
	@Autowired
	ServiceImpl serviceImpl;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getMessage().setBody(serviceImpl.getAllUsers());
		
	}

}