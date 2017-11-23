package controller;

import dataBase.DataBaseLoader;
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
	 * b_  == Boolean
	 * n_  == Number (n_dVar == Double...)
	 * st_ == String
	 * c_  == Char
	 * md_ == m + d: private field dataStructor
 */
public class Controller extends Application
{
	private static enum IteratorDirection { NEXT, PREV, FIRST, LAST };

	private FriendTracker m_view;
	private Friend[] md_friends;
	private int m_currentFriend;
	
	public Controller()
	{
		m_view = new FriendTracker();
		m_currentFriend = 0;
		md_friends = Friend.FriendDataFormatter.getFriends();
		System.out.println("Back");
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
		// Test & Experement with setting / Moding friend widgets
		
		m_view.getAppNavigationButtons()[0].setOnAction(this::modData);
		m_view.start(stage);
	}
	/**Button Events ~ View**/ 
	
	/* Get Event, get Event Id, Mod State, 
	 * 	Next, Prev, First, Last 
	 * 		> mod index (m_currentFriend)
	 * 		> Get friend at index
	 * 		> Update Fields 
	 * */
	// This gets Called every time a State Mod Btn is pressed 
	private void updateFriendFields()
	{
//		  for(int i = 0; i < 5; i++) 
//			  m_view.getFriendTextFields()[i].setText(md_friends[i].getFriendData()[i]);
		System.out.println("friend name : " + md_friends[0].getFriendData()[0] + " " + md_friends[0].getFriendData()[1]);
	}
		
	private void modData(ActionEvent e)
	{ 
		//m_view.getFriendTextFields()[0].setText("Yay i am happy");
		updateFriendFields();
	}
	// #State Modifiers 
	public void updateM_m_currentFriendIndex(IteratorDirection direction)
	{
		switch(direction) {
		case NEXT:
			if (m_currentFriend < (md_friends.length - 1))
				m_currentFriend++;
			break;
		case PREV:
			if (m_currentFriend >  1)
				m_currentFriend--;
			break;
		case FIRST:
			m_currentFriend = 0;
			break;
		case LAST:
			m_currentFriend = md_friends.length - 1;
			break;
		}
	}
	


}
