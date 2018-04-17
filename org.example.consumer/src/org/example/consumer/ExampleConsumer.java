package org.example.consumer;

import org.example.api.Greeting;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class ExampleConsumer {

	@Reference
	void bindGreeting(Greeting greet) {
		System.out.println("Scott says: " + greet.sayHello("Scott"));
	}
}
