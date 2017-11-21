package dataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Friend;

public class DataBaseLoader 
{
	// This will be the obj that loads friend data
	// TODO: find, get, load & send data from disk -> Friend obj for creation 
	/*This could be good 
	 * File classpathRoot = new File(classLoader.getResource("").getPath());
		File[] csvFiles = classpathRoot.listFiles(new FilenameFilter() {
		    @Override public boolean accept(File dir, String name) {
		        return name.endsWith(".csv");
		    }
		});*/
	// These are use for 8-bit bytes
	private FileInputStream  in   = null;
	private FileOutputStream out  = null;
	
	// These are for 16bit unicode 
	private FileReader fileReader = null;
	private BufferedReader reader = null;
	private FileWriter fileWriter = null;
	private BufferedWriter writer = null;
	
	private File file             = null;
	private String pathToDatabase = "/dataBase/";
	private String databaseFile   = "data.txt";
	
	public DataBaseLoader()
	{
		
	}
	public void connectToFilesOnDisk()
	{
		//System.out.print(System.getProperty("user.dir"));
		//System.out.print(file.getAbsolutePath());
		//System.out.println(this.getClass().getClassLoader().getResource("").getPath());
		file = new File("");
		String currentDir = file.getAbsolutePath();
		System.out.println(currentDir + pathToDatabase + databaseFile);
		
		
		String line = null;
		try {
			fileReader = new FileReader(currentDir + pathToDatabase + "input.txt");
			fileWriter = new FileWriter(currentDir + pathToDatabase + "output.txt");
	       
			   int c;
	         	while ((c = fileReader.read()) != -1) {
	         		System.out.print( (char) c);
	         		//fileWriter.write(c);
	         	}
	         	
		    	 	fileReader.close();
		    	 	fileWriter.close();
//			
//			// Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }   
//            // Always close files.
//            bufferedReader.close();   
	      } catch (IOException e) { e.printStackTrace(); }
	}
}
