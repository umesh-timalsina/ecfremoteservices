package org.siu.casa.remoteservice.api;

public interface IRaspberryPiService {
	
	/** Turn on the led connected to RaspberryPi
	 * @return true if the operation was successful
	 */
	public Boolean turnOnLed();
	
	/** Open the door controlled by RaspberryPi
	 * @return true if the operation was successful
	 */
	public Boolean openDoor();
	
	/** Turn off the led connected to RaspberryPi 
	 * @return true if the operation was successful
	 */
	public Boolean turnOffLed();
	
	/** Close the door controlled by RaspberryPi
	 * @return true if the operation was successful
	 */
	public Boolean closeDoor();
}
