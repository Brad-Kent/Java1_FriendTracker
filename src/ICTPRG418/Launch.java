package ICTPRG418;

import dataBase.DataBaseLoader;
import javafx.application.Application;
import view.FriendTracker;

public class Launch 
{
	public static void main(String[] args)
	{
		// Application.launch(FriendTracker.class);
		//new FriendTracker().launchApp(args);
		//Application.launch(FriendTracker.class, args);
		new DataBaseLoader().connectToFilesOnDisk();
	}

}
