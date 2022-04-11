package com.example.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.OrderService;

@Component
public class GetOrderProcessor implements Processor {
	
	@Autowired
	OrderService orderService;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getMessage().setBody(orderService.getOrders());
		
	}

}
