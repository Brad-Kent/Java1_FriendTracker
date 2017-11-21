package model;

import dataBase.DataBaseLoader;

public class Friend
{
	private String m_Name;
	private String m_Likes;
	private String m_Dislikes;
	private int m_BirthdayDay;
	private int m_BirthdayMonth;
	private int m_Birthday_Year;
	
	//private static FriendDataFormatter temp = new FriendDataFormatter();//new Friend().new FriendDataFormatter();
	
	
	public String getM_Name    () { return m_Name    ; }
	public String getM_Likes   () { return m_Likes   ; }
	public String getM_Dislikes() { return m_Dislikes; }
	public int getM_BirthdayDay  () { return m_BirthdayDay  ; }
	public int getM_BirthdayMonth() { return m_BirthdayMonth; }
	public int getM_Birthday_Year() { return m_Birthday_Year; }
	
	public void setM_Name    (String m_Name    ) { this.m_Name     = m_Name    ; }
	public void setM_Likes   (String m_Likes   ) { this.m_Likes    = m_Likes   ; }
	public void setM_Dislikes(String m_Dislikes) { this.m_Dislikes = m_Dislikes; }
	public void setM_BirthdayDay  (int m_BirthdayDay  ) { this.m_BirthdayDay   = m_BirthdayDay  ; }
	public void setM_BirthdayMonth(int m_BirthdayMonth) { this.m_BirthdayMonth = m_BirthdayMonth; }
	public void setM_Birthday_Year(int m_Birthday_Year) { this.m_Birthday_Year = m_Birthday_Year;	}


	public static Friend[] getFriends()
	{
		
	}
	// An addapter to Database, Gets data 
	private static final class FriendDataFormatter
	{
		private DataBaseLoader db;
		
		// 1) Sends Database a request for data, gets data and creates an Friend Array from the data 
		// 2) Or could load all data from disk and store in String array, when request comes in, finds current data and returns the data in its format
			// I.e. No Friend Obj, just the data, Friend data would all have to be private, or static
		
		public static void sayHello()
		{
			System.out.println("Hello From Inner: ");
		}
		
	}
	
}
