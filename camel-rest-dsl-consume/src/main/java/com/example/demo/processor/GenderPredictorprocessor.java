package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.ServiceImpl;

@Component
public class GenderPredictorprocessor implements Processor{
	
	@Autowired
	ServiceImpl serviceImpl;

	@Override
	public void process(Exchange exchange) throws Exception {
		//String empId  = exchange.getIn().getBody(String.class);
		
		String empId  = exchange.getIn().getHeader("id", String.class);
		
		//exchange.getMessage().setBody(serviceImpl.getEmployeeById(empId));
		
	}

}