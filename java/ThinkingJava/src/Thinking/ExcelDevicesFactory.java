package Thinking;

public class ExcelDevicesFactory<T> implements DevicesFactory<T>{

	@Override
	public Devices<T> getDevices() {
		// TODO Auto-generated method stub
		return new ExcelDevices<T>();
	}

}
