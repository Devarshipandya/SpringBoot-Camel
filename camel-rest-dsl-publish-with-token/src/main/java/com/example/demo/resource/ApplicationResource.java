package com.example.demo.resource;

import java.util.Arrays;

import org.apache.camel.api.management.mbean.ComponentVerifierExtension.VerificationError.Code;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestOperationResponseMsgDefinition;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Order;
import com.example.demo.processor.GetOrderProcessor;
import com.example.demo.processor.InsertOrderProcessor;
import com.example.demo.service.OrderService;


@Component
public class ApplicationResource extends RouteBuilder {

///Actual Camel Rout defines here for each operation	
	
	
@Autowired
GetOrderProcessor getOrderProcessor;

@Autowired
InsertOrderProcessor insertOrderProcessor;

@Autowired
private Environment env; // Used to Autowired Environment using which we can read application.proprty values

//@Value("${camel.servlet.mapping.context-path}")
//private String contextPath;
//We can read application.proprty using this way also


	@Override
	public void configure() throws Exception {

		// Enable basic rest configuration
		restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.json)
			.contextPath("/services") // value for this should be same with application.proprty -"camel.component.servlet.mapping.context-path" otherwise application will not start. This will be added on swagger file as base path 
			//.contextPath(env.getProperty("camel.component.servlet.mapping.context-path"))
			.enableCORS(true)
			.port(env.getProperty("server.port", "8080"))
			.apiContextPath("/api-doc")
			.apiProperty("api.title", "Sample Camel Rest User API")
			.apiProperty("api.version", "1.0.0")
			;
		
		//define hello-world end point ( example of direct response set in route )
		rest()
			.get("/hello-world").description("Hello World rest example")
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.responseMessage( new RestOperationResponseMsgDefinition().code(200).message("Operation performed successfully")  )
			.route()
			.setBody(constant("hello-world"))
		.endRest();
		
        //get order list ( example of perform operation using Processor )
		rest()
			.get("/getOrders").description("Get list of all orders")
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.responseMessage( new RestOperationResponseMsgDefinition().code(200).message("Order List fetched successfully") )
			.outType(Order.class)
			.route()
			.process(getOrderProcessor) // here need to specify Processor class
		.endRest();
		
		// get specific order id ( example of perform operation using bean / service class )
		rest()
	        .get("/getOrders/{id}").description("Get specific order based on id")
	        .produces(MediaType.APPLICATION_JSON_VALUE)
	        .consumes(MediaType.APPLICATION_JSON_VALUE)
	        .param().name("id").type(RestParamType.path).description("The id of the order to get").dataType("integer").endParam()
			.responseMessage( new RestOperationResponseMsgDefinition().code(200).message("Order based on id fetched successfully") )
			.responseMessage( new RestOperationResponseMsgDefinition().code(204).message("Order based on ghiven id is not found") )
			.outType(Order.class)
	        .route().routeId("order-based-on-id") // define route name
	        .bean(OrderService.class, "findOrderById(${header.id})") // need to pass bean / service class along with method name which needs to execute
		.endRest();

		
		// insert order ( example of perform operation using Processor )
		rest()
			.post("/insertOrder").description("Insert Order")
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.type(Order.class)
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.responseMessage( new RestOperationResponseMsgDefinition().code(200).message("Order created successfully")  )
			.route()
			.process(insertOrderProcessor)
			.endRest();
		
	}

}
