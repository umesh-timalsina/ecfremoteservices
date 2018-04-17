package org.example.application.command;

import org.apache.felix.service.command.CommandProcessor;
import org.example.application.api.Greeting;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = {
			CommandProcessor.COMMAND_SCOPE+":String=example",
			CommandProcessor.COMMAND_FUNCTION+":String=greet"
		}
)
public class GreetingCommand {
	private Greeting greetingSvc;
	
	@Reference
	public void setGreeting(Greeting greetingSvc) {
		this.greetingSvc = greetingSvc;
		System.out.println("Found a greeter");
	}
	
	public void greet(String name) {
		System.out.println(greetingSvc.sayHello(name));
	}

}
