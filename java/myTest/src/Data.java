
public class Data {
	private final char[] buffer;

	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < size; i++) {
			buffer[i] = '*';
		}
	}

	public synchronized String read() {
		StringBuilder result = new StringBuilder();
		for (char c : buffer) {
			result.append(c);
		}
		sleep(100);
		return result.toString();
	}

	public synchronized void write(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			sleep(100);
		}
	}

	private void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
