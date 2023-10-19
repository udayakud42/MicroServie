package com.mavericbank.microservices.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {
	
	public RouteLocator gatewayRouter (RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p-> p.path("/post")		
			.filters(f-> f
					.addRequestHeader("MyHeader", "MyURI")
					.addRequestParameter("Param", "MyValue"))
				.uri("http://httpbin.org:80"))
//				.route(p->p.path("/transaction-service/**")
//						.uri("lb://transaction-service"))
//				.route(p->p.path("/balance-service/**")
//						.uri("lb://balance-service"))
						.build();	
	}
}
