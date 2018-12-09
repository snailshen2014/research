package Thinking;

public class TxtDevicesFactory<T> implements DevicesFactory<T>{

	@Override
	public Devices<T> getDevices() {
		// TODO Auto-generated method stub
		return new TxtDevices<T>();
	}

}
