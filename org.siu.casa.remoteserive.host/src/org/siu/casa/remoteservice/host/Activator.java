package org.siu.casa.remoteservice.host;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.siu.casa.remoteservice.api.IRaspberryPiService;

public class Activator implements BundleActivator{
	private ServiceRegistration<IRaspberryPiService> raspberryPiServiceRegistration;
	private static final String SERVICE_EXPORTED_CONFIGS = "service.exported.configs";
	private static final String DEFAULT_CONFIG = "ecf.generic.server";
	@Override
	public void start(BundleContext context) throws Exception {
		Dictionary<String, Object> props = createRemoteServiceProperties();
		
		raspberryPiServiceRegistration = context.registerService(
											IRaspberryPiService.class, 
											new RaspberryPiService(), 
												props);
		System.out.println("RaspberryPi Service registered with registration= " 
												+ raspberryPiServiceRegistration );
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private Dictionary<String, Object> createRemoteServiceProperties() {
		Hashtable<String, Object> result = new Hashtable<>();
		result.put("service.exported.interfaces", "*");
		result.put(SERVICE_EXPORTED_CONFIGS, DEFAULT_CONFIG);
		result.put(DEFAULT_CONFIG+".port", HostConstants.HOST_PORT);
		result.put(DEFAULT_CONFIG+".hostname", HostConstants.HOST_IP);
		
		return result;
	}

}
