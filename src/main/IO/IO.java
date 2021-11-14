package main.IO;

import java.io.*;

/**
 * Handles writing to and reading from external files
 */
public final class IO {

	/**
	 * String to denote directory where all data should be saved
	 */
	private static String filename;

	/**
	 * String to denote path to data directory
	 */
	private static String path;

	/**
	 * BufferedWriter object helps to write to external files
	 */
	private static BufferedWriter bw;

	/**
	 * BufferedReader object helps to read from external files
	 */
	private static BufferedReader br;

	/**
	 * Stores instance of very next line of external text file
	 */
	private static String line;

	/**
	 * Boolean that denotes whether or not stored line is null, or the end of a line
	 */
	private static boolean EOL;

	/**
	 * Boolean that denotes whether the desired external file exists
	 */
	private static boolean fileExist = false;

	/**
	 * Sets up filename and file directory
	 */
	public static void start() {
		String dir = System.getProperty("user.dir");
		dir = dir + "/data";
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdir();
		}
		path = dir;
	}

	/**
	 * Sets up BufferedReader object to start reading from external file
	 */
	public static void setReader() {
		EOL = false;
		try {
			br = new BufferedReader(new FileReader(path + "/" + filename));
			fileExist = true;
		} catch (IOException e) {
			fileExist = false;
			System.out.println("Warning: " + filename + " Data file does not exist");
		}
	}

	/**
	 * Closes BufferedReader object to stop reading from external file and save
	 * changes
	 */
	public static void closeReader() {
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Reader had trouble closing");
		}
	}

	/**
	 * Sets up BufferedWriter to start writing to external file
	 */
	public static void setWriter() {
		try {
			bw = new BufferedWriter(new FileWriter(path + "/" + filename));
		} catch (IOException e) {
			System.out.println("ERROR: Could not write to specified file");
		}
	}

	/**
	 * Closes BufferedWriter object to stop writing to external file and save
	 * changes
	 */
	public static void closeWriter() {
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("IO Exception thrown - Writer had trouble closing");
		}
	}

	/**
	 * Set external file name
	 * 
	 * @param fn The file name to be read/write
	 */
	public static void setFileName(String fn) {
		// we might have to check if the name is valid for use as file name
		filename = fn + ".txt";
	}

	/**
	 * Set directory path for external files to be saved or read from
	 * 
	 * @param p The path to be set
	 * 
	 * This function is not used in our program. It's added for future use.
	 * 
	 */
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

	/**
	 * Safely writes a new line to external file
	 * 
	 * @param data String to be written
	 */
	public static void write(String data) {
		try {
			bw.write(data);
		} catch (IOException e) {
			System.out.println("WRITE Error");
		}
	}

	/**
	 * Safely reads a new line into internal line object from external file
	 */
	public static void readLine() {
		if (!EOL) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				System.out.println("READ Error");
			}
		}

		if (line == null)
			EOL = true;
	}

	/**
	 * Returns stored line object which represents next line of external file
	 * 
	 * @return line if it exists
	 */
	public static String getLine() {
		if (!EOL)
			return line;
		else
			return null;
	}

	/**
	 * Returns a boolean that indicates whether external file has ended
	 * 
	 * @return EOL boolean object
	 */
	public static boolean isEOL() {
		return EOL;
	}

	/**
	 * Returns a boolean that indicates whether external file eixsts
	 * 
	 * @return fileExist boolean object
	 */
	public static boolean checkFileExist() {
		return fileExist;
	}

}
