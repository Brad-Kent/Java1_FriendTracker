package model;

import dataBase.DataBaseLoader;

public class Friend
{
	private String[] md_friendData;
	private String m_Name;
	private String m_Likes;
	private String m_Dislikes;
	private String m_BirthdayDay;
	private String m_BirthdayMonth;
	//private int m_BirthdayYear;
	
	// Singetion Design Pattern ~ Uses like a Struct 
   // private Friend() {}
 	
    //private static FriendDataFormatter temp = new FriendDataFormatter();//new Friend().new FriendDataFormatter();
  	public String[] getFriendData() {return md_friendData; }
  	public String getM_Name    () { return m_Name    ; }
  	public String getM_Likes   () { return m_Likes   ; }
  	public String getM_Dislikes() { return m_Dislikes; }
  	public String getM_BirthdayDay  () { return m_BirthdayDay  ; }
  	public String getM_BirthdayMonth() { return m_BirthdayMonth; }
  	//public int getM_BirthdayYear () { return m_BirthdayYear ; }
  	
  	public void setFriendData(String[] friendData) { this.md_friendData = friendData; }
  	public void setM_Name    (String m_Name    ) { this.m_Name     = m_Name    ; }
  	public void setM_Likes   (String m_Likes   ) { this.m_Likes    = m_Likes   ; }
  	public void setM_Dislikes(String m_Dislikes) { this.m_Dislikes = m_Dislikes; }
  	public void setM_BirthdayDay  (String m_BirthdayDay  ) { this.m_BirthdayDay   = m_BirthdayDay  ; }
  	public void setM_BirthdayMonth(String m_BirthdayMonth) { this.m_BirthdayMonth = m_BirthdayMonth; }
  	//public void setM_BirthdayYear (int m_Birthday_Year) { this.m_BirthdayYear  = m_Birthday_Year;	}

	// An addapter to Database, Gets data 
	public static final class FriendDataFormatter
	{
		// TODO: This could be part of the dataBase, this sends database loader its class id -> gets relevent data associated with friend's!
		private static String dataIdCode = "friendData.txt";
		private static DataBaseLoader db = DataBaseLoader.getDataBaseLoader();
		
		// 1) Sends Database a request for data, gets data and creates an Friend Array from the data 
		// 2) Or could load all data from disk and store in String array, when request comes in, finds current data and returns the data in its format
			// I.e. No Friend Obj, just the data, Friend data would all have to be private, or static
		public static Friend[] getFriends()
		{
			// Basic Format, security, exception check yet TODO: Advanced 
			Friend[] friends = new Friend[50];
			String[][] friendData = formateData(db.getDataFromDataBase(dataIdCode));
			
			if(friendData == null || friendData.length > 50) return null; // #This should be a custom exception.
			System.out.println("Creating Friends :D " + friendData.length);
			
			// Create Friend Instances with Data from dataBase
			int currentIndex = 0, maxIndex = friendData.length;
			while(currentIndex < maxIndex) {
				// Assume field data structor is in order [str, str, str, int, int, int]
				// Data Input is more like [fName, lName, Likes, Dislikes, bday, bmonth, byear0
				Friend temp = new Friend(); 
				int index = 0;
				temp.md_friendData = friendData[currentIndex]; // # Contains extra Friend Data 
				temp.m_Name = (friendData[currentIndex][index++] + " "  + friendData[currentIndex][index++]);
				//System.out.println(temp.m_Name + "  " + index);
				temp.m_Likes = friendData[currentIndex][index++];
				temp.m_Dislikes = friendData[currentIndex][index++];
				temp.m_BirthdayDay   = friendData[currentIndex][index++];
				temp.m_BirthdayMonth = friendData[currentIndex][index++];
				//temp.m_BirthdayYear  = Integer.valueOf(friendData[currentIndex][index]);
				
				friends[currentIndex++] = temp;
			}
			System.out.println("Done");
			return friends;
		}
		private static String[][] formateData(String[] data)
		{
			//if(data == null) return null; #This should be a custom Exception w/ Handling 
	
			String[][] formattedData = new String[data.length][6]; // Could be Safer		
			for(int i = 0; i < data.length; i++) {
				String[] splitData = data[i].split(",");
				formattedData[i] = splitData;
//				for(String pp : formattedData[i]) // Testing, shows what data is in the array
//					System.out.print(pp + "|");
//				System.out.println();
			}
			return formattedData;
		}
		
	}// End of Inner Class
}//End of Main Class
