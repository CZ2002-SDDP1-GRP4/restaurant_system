package main;

import main.IO.RW;

public class IOApp {

	public void read(RW readable) {
		readable.read();
	}
	
	public void write(RW writable) {
		writable.write();
	}
}
