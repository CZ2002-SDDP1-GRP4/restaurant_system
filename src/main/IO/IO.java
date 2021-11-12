package main.IO;

import java.io.*;

public class IO {
	
	/* 
	 * String to denote directory where all data should be saved
	 */
	private String filename;
	private String path;
	public BufferedWriter bw;
	public BufferedReader br;
	
	public IO() {
		String dir = System.getProperty("user.dir");
		this.path = dir;
	}
	
	public IO(String path, String filename) {
		this.path = path;
		this.filename = filename;
	}
	
	public void setReader()
	{
		try {
			this.br = new BufferedReader(new FileReader(this.path + "\\" + this.filename));
		} catch (IOException e) {
			System.out.println("IO Exception thrown");
		}
	}
	
	public void closeReader()
	{
		try {
			this.br.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Reader had trouble closing");
		}
	}
	
	public void setWriter()
	{
		try {
			this.bw = new BufferedWriter(new FileWriter(this.path + "\\" + this.filename));
		} catch (IOException e) {
			System.out.println("IO Exception thrown");
		}
	}
	
	public void closeWriter()
	{
		try {
			this.bw.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Writer had trouble closing");
		}
	}
	
	
	
	public void setFileName(String fn)
	{
		//we might have to check if the name is valid for use as file name
		this.filename = fn;
	}
	
	public void setDirectory(String path)
	{
		// check if the path is a valid working directory
		try {
			File file = new File(path);

			// we can create our own custom exceptions if required
			if (!file.isDirectory()) throw new Exception("Path did not resolve to be Directory");
		}
			
		catch(Exception e){
			System.out.println("Please use a valid directory path");
			return;
		}
			
		this.path = path;
	}
	
}
