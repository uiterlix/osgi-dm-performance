package org.example.consumer.dm;

import org.apache.felix.dm.Component;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.example.consumer.ds.ExampleComponent;
import org.example.provider.MyFancyService;
import org.osgi.framework.BundleContext;

public class Activator extends DependencyActivatorBase {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Start bundle activator...");
		super.start(context);
	}
	
	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
 
		System.out.println("index: " + System.getProperty("org.apache.felix.dependencymanager.filterindex"));
		System.out.println("adding component...");
		Component exampleComponent = manager.createComponent().setImplementation(ExampleComponent.class)
			.add(manager.createServiceDependency().setService(MyFancyService.class, "(&(id=99999)(type=service))").setRequired(true)
					.setCallbacks("addFancyService", "removeFancyService"))
			.setCallbacks("init", "start", "stop", "destroy");
		manager.add(exampleComponent);
	}

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		
	}

}
