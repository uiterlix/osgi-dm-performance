package org.example.consumer.ds;

import org.example.provider.Producer;
import org.example.provider.Timer;

import aQute.bnd.annotation.component.*;

/**
 * Component used by the different DI implementations. 
 */
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

	@Reference(service=Producer.class, multiple=true, target="(&(id=99999)(type=service))")
	public void addFancyService(Producer service) {
		if (serviceCount == 0) {
			System.out.println("Injection started...");
			Timer.stop();
		}
		serviceCount ++;
	}
	
	public void removeFancyService(Producer service) {
		
	}
}