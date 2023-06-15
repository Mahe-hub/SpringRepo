package com.fdmgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Week9SpringBootEmployeeClientApplication {
	//baseurl 
	//public final String CONTACT_REST_URL_BASE= "http://localhost:8080/api/v1/employees";
	public final String CONTACT_REST_URL_BASE= "http://Employee-service/api/v1/employees";
	public static void main(String[] args) {
		SpringApplication.run(Week9SpringBootEmployeeClientApplication.class, args);
	}
	
	//get bean of webclient 
		@Bean 
		@LoadBalanced
		public WebClient.Builder webclientBulider()
		{
			return WebClient.builder();
		}
		
		//inject the instance 
		@Bean
		public WebClient employeeRestWebClient(WebClient.Builder bulider) 
		{
			//consume the base url for request
			return bulider.baseUrl(CONTACT_REST_URL_BASE)
					//header of request
					 //response from server it will be jason 
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					// bulid web client instance
					.build();
		}
}
