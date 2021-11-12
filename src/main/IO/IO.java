package main.IO;

import java.io.*;

public final class IO {

	/*
	 * String to denote directory where all data should be saved
	 */
	private static String filename;
	private static String path;
	
	private static BufferedWriter bw;
	private static BufferedReader br;
	
	private static String line;
	private static boolean EOL;
	private static boolean fileExist;
	
	public static void start() {
		String dir = System.getProperty("user.dir");
		dir = dir + "/data";
		File directory = new File(dir);
		if(!directory.exists())
		{
			directory.mkdir();
		}
		path = dir;
	}

	public static void setReader()
	{
		EOL = false;
		try {
			br = new BufferedReader(new FileReader(path + "/" + filename));
		} catch (IOException e) {
			System.out.println("IO Exception thrown");
		}
	}

	public static void closeReader() {
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Reader had trouble closing");
		}
	}

	public static void setWriter() {
		try {
			bw = new BufferedWriter(new FileWriter(path + "/" + filename));
			fileExist = true;
		} catch (IOException e) {
			fileExist = false;
			System.out.println("Warning: No Data file exist.");
		}
	}

	public static void closeWriter() {
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Writer had trouble closing");
		}
	}
	
	
	public static void setFileName(String fn)
	{
		//we might have to check if the name is valid for use as file name
		filename = fn + ".txt";
	}

	public static void setDirectory(String p) {
		// check if the path is a valid working directory
		try {
			File file = new File(p);

			// we can create our own custom exceptions if required
			if (!file.isDirectory())
				throw new Exception("Path did not resolve to be Directory");
		}

		catch (Exception e) {
			System.out.println("Please use a valid directory path");
			return;
		}

		path = p;
	}

	
	public static void write(String data)
	{
		try {
			bw.write(data);
		} catch (IOException e) {
			System.out.println("WRITE Error");
		}
	}
	
	public static void readLine()
	{
		if(!EOL)
		{
			try {
				line = br.readLine();
			} catch (IOException e) {
				System.out.println("READ Error");
			}
		}

		if(line == null) EOL = true;
	}
	
	public static String getLine()
	{
		if(!EOL) return line;
		else return null;
	}
	
	public static boolean isEOL()
	{
		return EOL;
	}
	
	public static boolean checkFileExist()
	{
		return fileExist;
	}

}
