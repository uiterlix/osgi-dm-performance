package org.example.consumer.ds;

import org.example.provider.MyFancyService;
import org.example.provider.Timer;

import aQute.bnd.annotation.component.*;

@Component
public class ExampleComponent {
	
	int serviceCount = 0;

	void init() {
		System.out.println("ExampleComponent init");
	}
	void start() {
		System.out.println("ExampleComponent start");
	}
	void stop() {
		System.out.println("ExampleComponent stop");
	}
	void destroy() {
		System.out.println("ExampleComponent destroy");
	}

	// TODO: Find out... the filter does not seem to make that much of a difference.
	@Reference(service=MyFancyService.class, multiple=true, target="(&(id=99999)(type=service))")
	public void addFancyService(MyFancyService service) {
		if (serviceCount == 0) {
			System.out.println("Injection started...");
			Timer.stop();
		}
		serviceCount ++;
	}
	
	public void removeFancyService(MyFancyService service) {
		
	}
}