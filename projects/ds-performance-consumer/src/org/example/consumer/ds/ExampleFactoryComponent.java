package org.example.consumer.ds;

import org.example.provider.MyFancyService;
import org.example.provider.ServiceFromFactory;
import org.example.provider.Timer;
import org.osgi.service.component.ComponentContext;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(servicefactory = true)
public class ExampleFactoryComponent implements ServiceFromFactory {

	int serviceCount = 0;

	@Activate
	void activate(ComponentContext ctx) {
		System.out.println("Activate, using bundle: " + ctx.getUsingBundle());
	}

	@Reference(service = MyFancyService.class, multiple = true, target = "(&(id=99999)(type=service))")
	public void addFancyService(MyFancyService service) {
		if (serviceCount == 0) {
			System.out.println("Injection started...");
			Timer.stop();
		}
		serviceCount++;
	}
}
