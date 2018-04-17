package org.siu.casa.remoteservice.host;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

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
		//result.put("ecf.exported.async.interfaces", "*");
		
		Properties props = System.getProperties();
		String config = props.getProperty(SERVICE_EXPORTED_CONFIGS);
		if(config == null) {
			config = DEFAULT_CONFIG;
			result.put(DEFAULT_CONFIG+".port", HostConstants.HOST_PORT);
			result.put(DEFAULT_CONFIG+".hostname", HostConstants.HOST_IP);
		}
		result.put(SERVICE_EXPORTED_CONFIGS, config);
		
		for(Object k : props.keySet()) {
			if(k instanceof String) {
				String key = (String) k;
				if (key.startsWith(config) || key.startsWith("osgi.basic") || key.startsWith("osgi.private")
						|| key.startsWith("osgi.confidential") || key.startsWith("osgi.async")
						|| key.startsWith("service."))
					result.put(key, props.get(key));
			}
		}
		return result;
	}

}
