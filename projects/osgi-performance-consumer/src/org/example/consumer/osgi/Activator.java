package org.example.consumer.osgi;

import org.example.provider.Producer;
import org.example.provider.Timer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Activator start");
		
		ServiceReference[] serviceReferences = context.getServiceReferences(Producer.class.getName(), "(&(id=99999)(type=service))");
		context.getService(serviceReferences[0]);
		Timer.stop();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
