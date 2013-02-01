package org.example.provider;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private Set<ServiceRegistration> registrations = new HashSet<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		Timer.start();
		System.out.println("Start provider activator...");
		if (System.getProperty("org.apache.felix.dependencymanager.filterindex") != null) {
			System.out.println("Initialize dependency manager (to enable filter indices early)");
			new DependencyManager(context);
			System.out.println("Initialized dependency manager");
		}
		Properties properties = new Properties();
		properties.setProperty("type", "service");
		registerServices(context, Globals.SERVICE_COUNT, properties);

	}
	
	private void registerServices(BundleContext context, int count, Properties properties) {
		long start = System.currentTimeMillis();
		long splitStart = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			properties.setProperty("id", i + "");
			Producer service = new Producer() {
			};
			registrations.add(context.registerService(Producer.class.getName(), service, properties));
			if (i % Globals.TIMER_SPLIT == 0) {
				long splitDuration = System.currentTimeMillis() - splitStart;
				splitStart = System.currentTimeMillis();
				System.out.println("Split (" + Globals.TIMER_SPLIT + "): " + splitDuration + "ms.");
			}
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("Registered " + count + " services... (" + duration + "ms.)");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		for (ServiceRegistration registration : registrations) {
			registration.unregister();
		}
	}

}
