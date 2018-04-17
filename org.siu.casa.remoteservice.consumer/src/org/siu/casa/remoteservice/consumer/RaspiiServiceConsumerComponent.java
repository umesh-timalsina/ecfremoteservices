package org.siu.casa.remoteservice.consumer;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.siu.casa.remoteservice.api.IRaspberryPiService;

@Component
public class RaspiiServiceConsumerComponent {

	private IRaspberryPiService raspiiService;
	
	@Reference
	void bindIRaspberryPiService(IRaspberryPiService 
						iRaspberryPiService) {
		
		this.raspiiService = iRaspberryPiService;
		System.out.println("Properly found iRaspberryPi Remote service");
	}
	
	
	// At the time of component activation, turn on the led for 5 seconds 
	// and then turn it back off.
	@Activate
	void activate() {
		if(this.raspiiService != null) {
			this.raspiiService.turnOnLed();
		
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.raspiiService.turnOffLed();
		}else {
			System.out.println("No raspberryPi Service found");
		}
	} // end method activate
}
