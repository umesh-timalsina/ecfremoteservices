package org.example.impls;

import org.example.api.Greeting;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(property= {"service.exported.interfaces=*", 
					  "ecf.generic.server.port=3250",
					  "ecf.generic.server.hostname=131.230.217.97",
					  "ecf.generic.server.id=ecftcp://131.230.217.97:3250/rpiservice",
					  "service.exported.configs=ecf.generic.server"
					}
)
public class Example implements Greeting {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}
	
	@Activate
	void activate() {
		System.out.println("Registered sayHello");
	}

}
