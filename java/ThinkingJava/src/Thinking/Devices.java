package Thinking;

import java.util.List;

public interface Devices<T> {
	List<T> fromFile();
}

interface DevicesFactory<T> {
	Devices<T> getDevices();
}
