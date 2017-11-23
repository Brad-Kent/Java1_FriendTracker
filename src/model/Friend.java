package model;

import dataBase.DataBaseLoader;

public class Friend
{
	private String[] md_friendData;
	private String m_Name;
	private String m_Likes;
	private String m_Dislikes;
	private int m_BirthdayDay;
	private int m_BirthdayMonth;
	private int m_BirthdayYear;
	
	//private static FriendDataFormatter temp = new FriendDataFormatter();//new Friend().new FriendDataFormatter();
	public String[] getFriendData() {return md_friendData; }
	public String getM_Name    () { return m_Name    ; }
	public String getM_Likes   () { return m_Likes   ; }
	public String getM_Dislikes() { return m_Dislikes; }
	public int getM_BirthdayDay  () { return m_BirthdayDay  ; }
	public int getM_BirthdayMonth() { return m_BirthdayMonth; }
	public int getM_BirthdayYear () { return m_BirthdayYear ; }
	
	public void setFriendData(String[] friendData) { this.md_friendData = friendData; }
	public void setM_Name    (String m_Name    ) { this.m_Name     = m_Name    ; }
	public void setM_Likes   (String m_Likes   ) { this.m_Likes    = m_Likes   ; }
	public void setM_Dislikes(String m_Dislikes) { this.m_Dislikes = m_Dislikes; }
	public void setM_BirthdayDay  (int m_BirthdayDay  ) { this.m_BirthdayDay   = m_BirthdayDay  ; }
	public void setM_BirthdayMonth(int m_BirthdayMonth) { this.m_BirthdayMonth = m_BirthdayMonth; }
	public void setM_BirthdayYear (int m_Birthday_Year) { this.m_BirthdayYear  = m_Birthday_Year;	}

	// Singetion Design Pattern 
    private Friend() {}
	
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
			Friend[] friends = new Friend[1024];
			String[][] friendData = formateData(db.getDataFromDataBase(dataIdCode));
			
			//if(friendData == null) return null; // #This should be a custom exception.
			System.out.println("creting Friends :D " + friendData.length);
			
			// Create Friend Instances with Data from dataBase
//			int currentIndex = 0, maxIndex = friendData.length;
//			while(currentIndex < maxIndex) {
//				// Assume field data structor is in order [str, str, str, int, int, int]
//				Friend temp = new Friend(); 
//				int index = 0;
//				temp.md_friendData = friendData[index];
//				temp.m_Name = friendData[currentIndex][index++];
//				temp.m_Likes = friendData[currentIndex][index++];
//				temp.m_Dislikes = friendData[currentIndex][index++];
//				temp.m_BirthdayDay   = Integer.valueOf(friendData[currentIndex][index++]);
//				temp.m_BirthdayMonth = Integer.valueOf(friendData[currentIndex][index++]);
//				temp.m_BirthdayYear  = Integer.valueOf(friendData[currentIndex][index]);
//				
//				friends[currentIndex] = temp;
//			}
		
//			 String[] temp = {"Name", "Likes", "Dislikes", "Day", "Month"};
//
//			Friend[] gg = new Friend[1];
//			gg[0] = new Friend();
//			gg[0].setFriendData(new String[] {"Name", "Likes", "Dislikes", "Day", "Month"});
//			return gg; //friends;
			return friends;
		}
		private static String[][] formateData(String[] data)
		{
			if(data == null) return null;
			
			String[][] formattedData = new String[data.length][6]; 
			int index = 0;
			for(String subData: data) {
				formattedData[index] = subData.split(",");
			}

			return formattedData;
		}
		
	}
	
}
