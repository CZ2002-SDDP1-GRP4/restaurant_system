package main.IO;

public interface RW {
	
	/* 
	 * Write data object to text file in specified directory and create a flat file with specified name
	 */
	public void write();
	
	/* 
	 * Read data from text file in specified directory
	 */
	public void read();
}
