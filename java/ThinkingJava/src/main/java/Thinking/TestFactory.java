package Thinking;

import java.util.List;

public class TestFactory {
	public static  <T> List<T>  generatorDevices(DevicesFactory<T> fac) {
		Devices<T> dev = fac.getDevices();
		return dev.fromFile();
	}
	public static void main(String[] args) {
		generatorDevices(new TxtDevicesFactory<String>());
		generatorDevices(new ExcelDevicesFactory<String>());
		
	}
}
