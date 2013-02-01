package org.example.consumer.ds;

import org.example.provider.Producer;
import org.example.provider.Timer;
import org.osgi.service.component.ComponentContext;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

/**
 * Not yet been used in testing
 *
 */
@Component(servicefactory = true)
public class ExampleFactoryComponent implements Producer {

	int serviceCount = 0;

	@Activate
	void activate(ComponentContext ctx) {
		System.out.println("Activate, using bundle: " + ctx.getUsingBundle());
	}

	@Reference(service = Producer.class, multiple = true, target = "(&(id=99999)(type=servicefromfactory))")
	public void addFancyService(Producer service) {
		if (serviceCount == 0) {
			System.out.println("Injection started...");
			Timer.stop();
		}
		serviceCount++;
	}
}
