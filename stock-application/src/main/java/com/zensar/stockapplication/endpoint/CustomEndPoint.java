package com.zensar.stockapplication.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "custom")
@Component
public class CustomEndPoint {
		
		@ReadOperation
		public String myCustomEndPoint() {
			return "my custom end point";
		}
}
