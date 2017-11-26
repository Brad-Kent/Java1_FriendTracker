package controller;

import java.util.Arrays;
import java.util.Comparator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Friend;
import view.FriendTracker;

/*
 * Class Description: 
 *	Heirarchy - Super: Application ~used for JavaFX applivaitons 
 *	Design    - Uses MVC & Commmunication Msg's 
 *				> MVC: Model, View, Controller
 *	State	  -  Controller uses a View field to implemment and handle events from the View \	
 *				 these events via buttons, they have been split into 2 differenct catatories\
 *				 in FriendTracker, btnDataMods ~ They mod, delete, save friend data -> saved\ 
 *				 the sencond type, btnAppNavigation ~They mod the display of which friend   \
 *				 get (next, prev, first, last) friend, they can also search for friend    
*/
/* Naming Conventions ~ C++ Derived
 * Cammel Case Typed
	 * m_  == Private field 
	 * d_  == DataStructor
	 * s_  == Static
	 * k_  == Constatnt ~ Final
	 * is_  == Boolean
	 * n_  == Number (n_dVar == Double...)
	 * st_ == String
	 * c_  == Char
	 * md_ == m + d: private field dataStructor
 */
public class Controller extends Application
{
	private static enum IteratorDirection { NEXT, PREV, FIRST, LAST };
	private static enum FriendDataEvent { NEW, SAVE, DELETE, FIND, CLOSE };
	
	private FriendTracker m_view;
	private Friend[] md_friends;
	private int m_currentFriend;
	private int m_numOfFriends;
	
	public Controller()
	{
		m_view = new FriendTracker();
		m_currentFriend = 0;
		
		md_friends = new Friend[1024];
		
		// This could be more effiecent, i.e. get length from Frend.DataFormatter, or part of data Structor
		Friend[] temp = Friend.FriendDataFormatter.getFriends();
		m_numOfFriends = temp.length;
		
		for(int i = 0; i < temp.length; i++)
			md_friends[i] = temp[i];
	}
	
	/**Class Methods**/ 
	//Final Methods
	public static final void launchApp()
	{
		Application.launch(Controller.class); 
	}
	
	/**Instance Methods&Behaviour**/ 
	// Super Overrides 
	@Override
	public void start(Stage stage) throws Exception 
	{
		// Get the friend data setup 
		setUpDataModButtonEvents  ();
		setUpFriendDataModsButtons();
		// Sort then load First Friend Data -> View
		sortFriendArray();
		modData( null, IteratorDirection.FIRST);
		
		m_view.getAppNavigationButtons()[4].setOnAction(e -> binarySearchFriendsBday());
		
		
		// Tell view to: Init and Show View
		m_view.start(stage);
	}
	/**Button Events ~ View**/ 
	
	/* Get Event, get Event Id, Mod State, 
	 * 	Next, Prev, First, Last 
	 * 		> mod index (m_currentFriend)
	 * 		> Get friend at index
	 * 		> Update Fields 
	 * */
	private void setUpDataModButtonEvents()
	{
		m_view.getAppNavigationButtons()[0].setOnAction(e -> modData( e, IteratorDirection.FIRST));
		m_view.getAppNavigationButtons()[1].setOnAction(e -> modData( e, IteratorDirection.PREV));
		m_view.getAppNavigationButtons()[2].setOnAction(e -> modData( e, IteratorDirection.NEXT));
		m_view.getAppNavigationButtons()[3].setOnAction(e -> modData( e, IteratorDirection.LAST));
	}
	// This gets Called every time a State Mod Btn is pressed 
	private void modData(ActionEvent e, IteratorDirection d)
	{ 
		updateCurrentFriendIndex(d);
		updateFriendFields();
	}
	private void updateCurrentFriendIndex(IteratorDirection direction)
	{
		switch(direction) {
		case NEXT:
			if (m_currentFriend < (m_numOfFriends - 1))
				m_currentFriend++;
			break;
		case PREV:
			if (m_currentFriend > 0)
				m_currentFriend--;
			break;
		case FIRST:
			m_currentFriend = 0;
			break;
		case LAST:
			m_currentFriend = m_numOfFriends - 1;
			break;
		}
	}
	private void updateFriendFields()
	{
		// Update the Text Fields 
		int index = 0;
		System.out.println("Current Friend: " + m_currentFriend );//+ ", NumOfFriends; " + m_numOfFriends);
		m_view.getFriendTextFields()[index++].setText(md_friends[m_currentFriend].getM_Name         ());
		m_view.getFriendTextFields()[index++].setText(md_friends[m_currentFriend].getM_Likes        ());
		m_view.getFriendTextFields()[index++].setText(md_friends[m_currentFriend].getM_Dislikes     ());
		m_view.getFriendTextFields()[index++].setText(md_friends[m_currentFriend].getM_BirthdayDay  ());
		m_view.getFriendTextFields()[index  ].setText(md_friends[m_currentFriend].getM_BirthdayMonth());
	}
	// Data Modifer Events
	private void setUpFriendDataModsButtons()
	{
		int index = 0;
		m_view.getDataMods()[index++].setOnAction(e -> modFriendData(e, FriendDataEvent.NEW   ));
		m_view.getDataMods()[index++].setOnAction(e -> modFriendData(e, FriendDataEvent.SAVE  ));
		m_view.getDataMods()[index++].setOnAction(e -> modFriendData(e, FriendDataEvent.DELETE));
		m_view.getDataMods()[index++].setOnAction(e -> modFriendData(e, FriendDataEvent.FIND  ));
		m_view.getDataMods()[index  ].setOnAction(e -> modFriendData(e, FriendDataEvent.CLOSE ));
	}
	// new, save, delete, find, close 
	private void modFriendData(ActionEvent e, FriendDataEvent fde)
	{
		findFriendEvent(fde);
	}
	private void findFriendEvent(FriendDataEvent fde)
	{
		switch(fde) {
		case NEW: 
			// add new Friend(numOfFriends++, new Friend()) :: if not saved, undo ++ func -> stack local fields
			newFriend();
			break;
		case SAVE:
			// save the current changes to existing friend || new Friend
			saveFriendChanges();
			break;
		case DELETE:
			// delete current Friend from database and current session
			deleteFriend();
			break;
		case FIND:
			// Search alg -> New Screen
			findFriend();
			break;
		case CLOSE:
			// Save and close application , save is optional 
			m_view.getStage().close();
			break;
		}
	}
	private void newFriend()
	{
		m_numOfFriends++;
		Friend newFriend = new Friend();
		newFriend.setFriendData(new String[] {});
		newFriend.setM_Name("Name");
		newFriend.setM_Likes("Likes");
		newFriend.setM_Dislikes("Dislikew");
		newFriend.setM_BirthdayDay("BirthDay Day");
		newFriend.setM_BirthdayMonth("Birthday Month");
		
		md_friends[m_numOfFriends - 1] = newFriend;
		m_currentFriend = m_numOfFriends - 1;
		updateFriendFields();
	}
	private void saveFriendChanges()
	{
		// get field data
		// change friend data to field data
		int index = 0;
		md_friends[m_currentFriend].setM_Name         (m_view.getFriendTextFields()[index++].getText());
		md_friends[m_currentFriend].setM_Likes        (m_view.getFriendTextFields()[index++].getText());
		md_friends[m_currentFriend].setM_Dislikes     (m_view.getFriendTextFields()[index++].getText());
		md_friends[m_currentFriend].setM_BirthdayDay  (m_view.getFriendTextFields()[index++].getText());
		md_friends[m_currentFriend].setM_BirthdayMonth(m_view.getFriendTextFields()[index  ].getText());
	}
	private void deleteFriend()
	{
		for(int i = m_currentFriend; i < m_numOfFriends - 1; i++) 
			md_friends[i] = md_friends[i + 1];
		
		m_numOfFriends--;
		
		if(m_currentFriend == m_numOfFriends)
			m_currentFriend--;
		
		updateFriendFields();
	}
	private void findFriend()
	{
		sortFriendArray    ();
		binarySearchFriends();
		updateFriendFields ();
	}
	private void sortFriendArray()
	{
		Arrays.sort(md_friends, new Comparator<Friend>() {
			@Override
			public int compare(Friend o1, Friend o2) {
				if (o1 == null || o2 == null)
					return 0;
				return o1.getM_Name().compareToIgnoreCase(o2.getM_Name());
			}
		});
	}
	private void binarySearchFriends()
	{
		String x = m_view.getFriendTextFields()[0].getText();
		
	    int low = 0;
	    int high = m_numOfFriends - 1;
	    int mid = -1;
        
	    while (low <= high) {
	        mid = (low + high) / 2;
	
	        if (md_friends[mid].getM_Name().compareToIgnoreCase(x) < 0) 
	            low = mid + 1;
	         else if (md_friends[mid].getM_Name().compareToIgnoreCase(x) > 0) 
	            high = mid - 1;
	         else 
	            break;// mid;
	    }
	    System.out.println("Mid: " + mid);
	    m_currentFriend = mid;
	}
	private void binarySearchFriendsBday()
	{
		
		// Sort by Birthday Month
		Arrays.sort(md_friends, new Comparator<Friend>() {
			@Override
			public int compare(Friend o1, Friend o2) {
				if (o1 == null || o2 == null)
					return 0;
				Integer f1 = Integer.valueOf(o1.getM_BirthdayMonth());
				Integer f2 = Integer.valueOf(o2.getM_BirthdayMonth());
				return f1.compareTo(f2);
			}
		});
		
		// Binary Search for Month
		int x = Integer.valueOf(m_view.getSearch().getText());
		
	    int low = 0;
	    int high = m_numOfFriends - 1;
	    int mid = -1;
	    while (low <= high) {
	        mid = (low + high) / 2;
	        if (Integer.valueOf(md_friends[mid].getM_BirthdayMonth()) < x ) 
	            low = mid + 1;
	        else if (Integer.valueOf(md_friends[mid].getM_BirthdayMonth()) > x) 
	            high = mid - 1;
	        else 
	        		break;
	    }
	    // if No Result is found 
	    if(Integer.valueOf(md_friends[mid].getM_BirthdayMonth()) != x)
	    		return;
	   
	    
	    // Found a Sequence 
	    System.out.println("Index Found: " + mid);
	    
	    // Get all Indexs on Left 
	    int startIndex = mid;
	    while(  (startIndex >= 0) && ( (Integer.valueOf(md_friends[startIndex].getM_BirthdayMonth())) == x)) {
	    		System.out.println("Friend at: " + startIndex + " Month: " + Integer.valueOf(md_friends[startIndex].getM_BirthdayMonth()) + "Name: " + md_friends[startIndex].getM_Name());
	    		startIndex--;
	    }
	    // Get all Indexs on Right
	    int endIndex = mid + 1;
	    while(  (endIndex <= 50 - 1) && ( (Integer.valueOf(md_friends[endIndex].getM_BirthdayMonth())) == x)) {
	    		System.out.println("Friend at: " + endIndex + " Month: " + Integer.valueOf(md_friends[endIndex].getM_BirthdayMonth()) + "Name: " + md_friends[endIndex].getM_Name());
	    		endIndex++;
	    }
	    // SideAffects due to looping 
	    startIndex++;
	    endIndex--;
	    
	    // Num Of Birthdays 
	    int numOfBDays = 0;
	    int temp = startIndex;
	    while(temp <= endIndex) {
	    		numOfBDays++;
	    		temp++;
	    }
	    System.out.println("Num Of Birthdays: " + numOfBDays);
	    
	   // Get Friend Data, put into String[][]
	    String friendData[][] = new String[numOfBDays][5];
	    int tempIndex = 0, index = 0;
	    for(int i = startIndex; i <= endIndex; i++) {
	    		friendData[tempIndex][index++] = md_friends[i].getM_BirthdayDay  ();
	    		friendData[tempIndex][index++] = md_friends[i].getM_BirthdayMonth();
	    		friendData[tempIndex][index++] = md_friends[i].getM_Name         ();
	    		friendData[tempIndex][index++] = md_friends[i].getM_Likes        ();
	    		friendData[tempIndex][index++] = md_friends[i].getM_Dislikes     ();
	    		tempIndex++; index = 0;
	    }
	  
	    // Send to Sub-View
	    m_view.getSearchResultView().addFriendDatas(friendData);
	    m_currentFriend = startIndex;
	    updateFriendFields();  
	} 
}// End of Class



/**
 * I think the problem with this due to the looping use in a while loop, i.e. the loop runs for and extra in both left and right unless rules are broke n >0 < 49..
 * 
 * 	    // found sequence of Bdays Months 
 * 
 * 
	    		// need to find Start & End of Sequence 
	    int startingPoint = mid - 1;
	    int endPoint = mid;
	    
	    int bdayMonth = x;   //Integer.valueOf(md_friends[mid].getM_BirthdayMonth());
	    System.out.println("Month: " + x);
	    while( startingPoint > 0) {
	    		if( bdayMonth == (Integer.valueOf(md_friends[startingPoint].getM_BirthdayMonth()))) {
	    			startingPoint--;
	    		}
	    		else 
	    			break;
	    }
	    while( endPoint < (m_numOfFriends - 1)) {
	    	if( bdayMonth == (Integer.valueOf(md_friends[endPoint].getM_BirthdayMonth()))) {
	    		System.out.println("Loop: " + Integer.valueOf(md_friends[endPoint].getM_BirthdayMonth()));
	    		++endPoint;	
	    	}
	 	else 
    			break;
	    }
	    
	    
	    
//	    if((Integer.valueOf(md_friends[endPoint].getM_BirthdayMonth())) != x)
//	    		System.out.println("You are fucked Java");
	
	 
	    
	    
	    int index = endPoint - startingPoint;
	    System.out.println("Mid Index: "+ mid);
	    System.out.println("NumOfBdays: " + index + "| Start Index: " + startingPoint + " | End Index: " + endPoint);
	  
	    m_currentFriend = endPoint;
	    updateFriendFields();
	    
	    
	    String[][] friendData = new String[index][5];
//	    int subIndex = 0;
//	    for(int i = startingPoint; i <= endPoint; i++) {
//	    			friendData[i][subIndex++] = md_friends[i].getM_Name() + " ";
//	    			friendData[i][subIndex++] = md_friends[i].getM_Likes() + " ";
//	    			friendData[i][subIndex++] = md_friends[i].getM_Dislikes() + " " ;
//	    			friendData[i][subIndex++] = md_friends[i].getM_BirthdayDay() + " ";
//	    			friendData[i][subIndex++] = md_friends[i].getM_BirthdayMonth();
//	    			System.out.println("Friend Index: " + i + ", ");
//	    			subIndex = 0;
//	    }
//	   m_view.getSearchResultView().addFriendDatas(friendData);
//	   m_currentFriend = startingPoint;
//	   updateFriendFields();
 * 
 * /
 */