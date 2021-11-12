package main;

import main.IO.RW;

public class IOApp {
	public RW read(RW readable) {
		return readable.read();
	}
	
	public void write(RW writable) {
		writable.write();
	}
}
