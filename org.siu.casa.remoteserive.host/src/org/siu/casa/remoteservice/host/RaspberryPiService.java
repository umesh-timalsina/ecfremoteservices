package org.siu.casa.remoteservice.host;

import org.siu.casa.remoteservice.api.IRaspberryPiService;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class RaspberryPiService implements IRaspberryPiService{
	//private GpioPinDigitalOutput ledPin;
	private GpioPinDigitalOutput doorPin;
	
	Boolean bool = Boolean.FALSE;
	
	@Override
	public Boolean turnOnLed() {
		//ledPin.setState(PinState.HIGH);
		System.out.println("TurnOnLed service called from remote consumer");
		bool = true;
		return bool;
	} // end method turnOnLed

	@Override
	public Boolean openDoor() {
		doorPin.setState(PinState.HIGH);
		System.out.println("OpenDooe service called from remote consumer");
		return doorPin.getState().isHigh();
	}// end method openDoor
	
	@Override
	public Boolean turnOffLed() {
		//ledPin.setState(PinState.LOW);
		System.out.println("TurnOffLed service called from remote consumer");
		bool = false;
		return bool;
	}// end method turnOffLed

	@Override
	public Boolean closeDoor() {
		doorPin.setState(PinState.LOW);
		System.out.println("closeDoor service called from remote consumer");
		return doorPin.getState().isLow();
	}// end method closeDoor
}
