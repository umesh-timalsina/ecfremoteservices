package org.siu.casa.remoteserive.host.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.siu.casa.remoteservice.api.IRaspberryPiService;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Component(
		property = {
				"service.exported.interfaces="
							+ HostConstants.HOST_EXPORTED_INTERFACES,
							
				"ecf.exported.async.interfaces="
							+ HostConstants.HOST_EXPORTED_INTERFACES, // Remote the available async interfaces as well
				
				"ecf.generic.server.hostname="
							+ HostConstants.HOST_IP,
				
				"ecf.generic.server.port="
							+ HostConstants.HOST_PORT,
				
				"server.exported.configs=ecf.generic.server.port"
			}
)
public class RaspberryPiService implements IRaspberryPiService{
	
	private GpioPinDigitalOutput ledPin;
	private GpioPinDigitalOutput doorPin;
	
	@Activate
	void activate() {
		
		GpioController gpioController = GpioFactory.getInstance();
		ledPin = gpioController.provisionDigitalOutputPin(
								   RaspiPin.GPIO_00, 
								   PinState.LOW
						);
		
		doorPin = gpioController.provisionDigitalOutputPin(
								RaspiPin.GPIO_00,
								PinState.LOW
						);
		
		System.out.println("RaspberryPi service available via DS");
		
	}// end method activate
	
	@Override
	public Boolean turnOnLed() {
		ledPin.setState(PinState.HIGH);
		System.out.println("TurnOnLed service called from remote consumer");
		return ledPin.getState().isHigh();
	} // end method turnOnLed

	@Override
	public Boolean openDoor() {
		doorPin.setState(PinState.HIGH);
		System.out.println("OpenDooe service called from remote consumer");
		return doorPin.getState().isHigh();
	}// end method openDoor
	
	@Override
	public Boolean turnOffLed() {
		ledPin.setState(PinState.LOW);
		System.out.println("TurnOffLed service called from remote consumer");
		return ledPin.getState().isLow();
	}// end method turnOffLed

	@Override
	public Boolean closeDoor() {
		doorPin.setState(PinState.LOW);
		System.out.println("closeDoor service called from remote consumer");
		return doorPin.getState().isLow();
	}// end method closeDoor
}
