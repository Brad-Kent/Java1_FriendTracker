package dataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DataBaseLoader
{
	// Singleton Design Pattern
	private DataBaseLoader() {}
	private static DataBaseLoader db = new DataBaseLoader();
	
	// These are use for 8-bit bytes
	private FileInputStream  in   = null;
	private FileOutputStream out  = null;
	
	// File<Reader/Writer> are for 16bit unicode, Buffered are for Srings and ease of use
	private FileReader fileReader = null;
	private BufferedReader reader = null;
	private FileWriter fileWriter = null;
	private BufferedWriter writer = null;
	
	// File and Path's 
	private File file             = new File("");
	private String pathToDatabase = "/dataBase/";
	private String databaseFile   = "friend50.txt";
	
	// Getters & Setters 
	public static DataBaseLoader getDataBaseLoader() { return db; }
	// #TODO: If project is to be upgraded to a larger scale 
	//public void setDataBaseFile(String fileName) { this.databaseFile = fileName; }
	
	public String[] getDataFromDataBase(String dataId)
	{
		String currentDir = file.getAbsolutePath() + pathToDatabase;
		
		String[] lines = new String[50];
		try {
			fileReader = new FileReader(currentDir + databaseFile);
			reader = new BufferedReader(fileReader);
			String line = null; int index = 0;
			while((line = reader.readLine()) != null) {
					//  System.out.println(line);
					  lines[index++] = line;
			}   
			reader.close();
			fileReader.close();
		}catch (IOException e) { e.printStackTrace(); }
		
		return lines;
	}
}

