package com.example.demo.resource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.processor.AddUserProcessor;
import com.example.demo.processor.GetAllUsersProcessor;
import com.example.demo.processor.GetUserByIdProcessor;
import com.example.demo.processor.UpdateUserProcessor;



@Component
public class ApplicationResource extends RouteBuilder {

@Autowired
GetAllUsersProcessor getAllUsersProcessor;

@Autowired
GetUserByIdProcessor getUserByIdProcessor;

@Autowired
AddUserProcessor addUserProcessor;

@Autowired
UpdateUserProcessor updateUserProcessor;

	@Override
	public void configure() throws Exception {
		
		//restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
	
		rest().get("/getAllUsers").produces(MediaType.APPLICATION_JSON_VALUE).route().process(getAllUsersProcessor).endRest();
		
		
		rest().get("/getUserById/{userId}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(getUserByIdProcessor).endRest();
		
		
		//rest().post("/addUser").consumes(MediaType.APPLICATION_JSON_VALUE).type(User.class).produces(MediaType.APPLICATION_JSON_VALUE)
		
		//.route().process(addUserProcessor).marshal().json(JsonLibrary.Jackson, User.class).endRest();
		
       rest().bindingMode(RestBindingMode.json).post("/addUser").consumes(MediaType.APPLICATION_JSON_VALUE).type(User.class).outType(User.class).route().process(addUserProcessor);
       
	  
       rest().bindingMode(RestBindingMode.json).put("/updateUser/{userId}").consumes(MediaType.APPLICATION_JSON_VALUE).type(User.class).outType(User.class)
		
		.route().process(updateUserProcessor);
		
		
		
		
		//rest().get("/getAllDogBreeds").produces(MediaType.APPLICATION_JSON_VALUE).route().process(dogBreedsProcessor);
		
		//rest().get("/getEmployee/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(getEmployeeProcessor);
		
		//rest().get("/getCountryDetails/{country}/{zip}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(zipCodeLookupProcessor);

		
		

		
	
	}

}
