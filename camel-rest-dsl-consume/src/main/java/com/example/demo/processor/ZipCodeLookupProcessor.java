package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.ServiceImpl;

@Component
public class ZipCodeLookupProcessor implements Processor{
	
	@Autowired
	ServiceImpl serviceImpl;

	@Override
	public void process(Exchange exchange) throws Exception {
		//String empId  = exchange.getIn().getBody(String.class);
		
		String countryCode  = exchange.getIn().getHeader("country", String.class);
		
		String zipCode  = exchange.getIn().getHeader("zip", String.class);
		
		//exchange.getMessage().setBody(serviceImpl.getCountryDetailsByCode(countryCode,zipCode));
		
	}

}


