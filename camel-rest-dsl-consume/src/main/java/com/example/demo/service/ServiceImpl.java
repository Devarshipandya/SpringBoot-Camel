package com.example.demo.service;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class ServiceImpl {

	CamelContext camelContext = new DefaultCamelContext();
	ProducerTemplate template = camelContext.createProducerTemplate();


	
	public Object getAllUsers() {
		Exchange exchange = null;

		try {

			camelContext.start();
			
			exchange = template.send("https://gorest.co.in/public/v2/users", new Processor() {
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setHeader("Authorization", "Bearer 775fc969a7cf3d6eafcedbc42b420ed1199d51b6e25fcd04478c21dcdfc4a273");
				}
			});

			Message out = exchange.getMessage();
			System.out.println("Response from http template is " + exchange.getMessage().getBody());
			System.out.println("status header is " + out.getHeader(Exchange.HTTP_RESPONSE_CODE));
			camelContext.close();
		} catch (Exception e) {

		}

		return exchange.getMessage().getBody();

	}
	
	public Object getUserById(String userId) {
		Exchange exchange = new DefaultExchange(camelContext);

		try {
			
			String RestUrl  =  "https://gorest.co.in/public/v2/users/" + userId;

			camelContext.start();

			exchange = template.send(RestUrl, new Processor() {
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setHeader("Authorization", "Bearer 775fc969a7cf3d6eafcedbc42b420ed1199d51b6e25fcd04478c21dcdfc4a273");
				}
			});

			Message out = exchange.getMessage();
			System.out.println("Response from http template is " + exchange.getMessage().getBody());
			System.out.println("status header is " + out.getHeader(Exchange.HTTP_RESPONSE_CODE));
			camelContext.close();
		} catch (Exception e) {

		}

		return exchange.getMessage().getBody();

	}
	
	public Object addUser(User user) {
		Exchange exchange = new DefaultExchange(camelContext);

		try {
			
			String RestUrl  =  "https://gorest.co.in/public/v2/users";

			camelContext.start();
			
			exchange = template.send(RestUrl, new Processor() {
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setBody(user, User.class);
					 exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
					 
					 exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
					 
					exchange.getIn().setHeader("Authorization", "Bearer 775fc969a7cf3d6eafcedbc42b420ed1199d51b6e25fcd04478c21dcdfc4a273");
				}
			});

			Message out = exchange.getMessage();
			System.out.println("Response from http template is " + exchange.getMessage().getBody());
			System.out.println("status header is " + out.getHeader(Exchange.HTTP_RESPONSE_CODE));
			camelContext.close();
		} catch (Exception e) {

		}

		return exchange.getMessage().getBody();

	}
	
	public Object UpdateUser(User user, String userId)
			 {
		Exchange exchange = new DefaultExchange(camelContext);

		try {
			
			String RestUrl  =  "https://gorest.co.in/public/v2/users/"+userId;

			camelContext.start();
			
			exchange = template.send(RestUrl, new Processor() {
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setBody(user, User.class);
					 exchange.getIn().setHeader(Exchange.HTTP_METHOD, "PUT");
					 exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
					 
					exchange.getIn().setHeader("Authorization", "Bearer 775fc969a7cf3d6eafcedbc42b420ed1199d51b6e25fcd04478c21dcdfc4a273");
				}
			});

			Message out = exchange.getMessage();
			System.out.println("Response from http template is " + exchange.getMessage().getBody());
			System.out.println("status header is " + out.getHeader(Exchange.HTTP_RESPONSE_CODE));
			camelContext.close();
		} catch (Exception e) {

		}

		return exchange.getMessage().getBody();

	}
	
	
	
	/*
	 * public Object getAllDogBreeds() { Exchange exchange = null;
	 * 
	 * try {
	 * 
	 * camelContext.start();
	 * 
	 * exchange = template.send("https://dog.ceo/api/breeds/list/all", new
	 * Processor() { public void process(Exchange exchange) throws Exception {
	 * //exchange.getIn().setHeader(Exchange.HTTP_QUERY, "hl=en&q=activemq"); } });
	 * 
	 * Message out = exchange.getMessage();
	 * System.out.println("Response from http template is " +
	 * exchange.getMessage().getBody()); System.out.println("status header is " +
	 * out.getHeader(Exchange.HTTP_RESPONSE_CODE)); camelContext.close(); } catch
	 * (Exception e) {
	 * 
	 * }
	 * 
	 * return exchange.getMessage().getBody();
	 * 
	 * }
	 */

	
	
	/*
	 * public Object getGenderByName(String name) { Exchange exchange = new
	 * DefaultExchange(camelContext);
	 * 
	 * try {
	 * 
	 * camelContext.start(); exchange.setProperty("name", name);
	 * 
	 * template.send("http://api.zippopotam.us/us/98121", exchange);
	 * 
	 * Message out = exchange.getMessage();
	 * System.out.println("Response from http template is " +
	 * exchange.getMessage().getBody()); System.out.println("status header is " +
	 * out.getHeader(Exchange.HTTP_RESPONSE_CODE)); camelContext.close(); } catch
	 * (Exception e) {
	 * 
	 * }
	 * 
	 * return exchange.getMessage().getBody();
	 * 
	 * }
	 */
	
	
	
	/*
	 * public Object getCountryDetailsByCode(String countryCode, String zipCode) {
	 * 
	 * System.out.println("inside getCountryDetailsByCode method " );
	 * 
	 * CountryDetail detail = new CountryDetail(); Exchange exchange = new
	 * DefaultExchange(camelContext);
	 * 
	 * String countryDetail = "";
	 * 
	 * try {
	 * 
	 * camelContext.start();
	 * 
	 * countryDetail = template.requestBodyAndHeaders( "http://api.zippopotam.us/" +
	 * countryCode + "/" + zipCode, null, null, String.class);
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //detail
	 * = mapper.readValue(countryDetail, CountryDetail.class);
	 * 
	 * //ObjectMapper checkoutResponsePrintMapper = new ObjectMapper();
	 * //System.out.
	 * println("-------------------START----------------PO data response");
	 * ////System.out.println(checkoutResponsePrintMapper.
	 * writerWithDefaultPrettyPrinter().writeValueAsString(detail)); System.out.
	 * println("-------------------END-------------------PO data response");
	 * 
	 * 
	 * //exchange.setProperty("country", countryCode); //exchange.setProperty("zip",
	 * zipCode);
	 * 
	 * //exchange =
	 * template.send("http://api.zippopotam.us/"+countryCode+"/"+zipCode, exchange);
	 * 
	 * //exchange =
	 * template.requestBodyAndHeaders("http://api.zippopotam.us/"+countryCode+"/"+
	 * zipCode, null, null);
	 * 
	 * //Message out = exchange.getMessage();
	 * //System.out.println("Response from http template is " +
	 * exchange.getMessage().getBody()); //System.out.println("status header is " +
	 * out.getHeader(Exchange.HTTP_RESPONSE_CODE)); //camelContext.close();
	 * 
	 * //exchange.getIn().setBody(detail);
	 * 
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * }
	 * 
	 * return countryDetail;
	 * 
	 * }
	 */

}
	


