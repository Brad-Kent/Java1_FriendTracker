package ICTPRG418;

public class Friend
{
	private String m_Name;
	private String m_Likes;
	private String m_Dislikes;
	private int m_BirthdayDay;
	private int m_BirthdayMonth;
	private int m_Birthday_Year;
	
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

}
