package view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FriendTracker extends Application
{
	// Screen Fields   
	//final JFXPanel fxPanel = new JFXPanel();
	private static final String appTitle = "Friend Tracker";
	private static final int screenY = 400;
	private static final int screenX = 640;

	/// Root Content Branch 
	private GridPane contentRoot;
	private Label contentTitle;
	
	// Friend Data Widgets 
	private Label    [] inputLabels         ; 
	private TextField[] friendTextFields    ;
	private Button   [] dataMods            ;
    private Button   [] appNavigationButtons;

    /**Construtor**/
	public FriendTracker()
	{
		setUpContentGrid      ();
		setUpTitle            ();
		setUpInputLables      ();
		setUpAppDataModButtons();
		setUpAppStateButtons  ();
		//contentRoot.setGridLinesVisible(true);
	}
	/**JavaFx Applicaton**/
	// (optional)
	public void launchApp(String[] args){ Application.launch(); }
	// #FX Entry Point
	@Override
	public void start(Stage stage) throws Exception
	{
        // add padding below Title 
        Label paddMe = new Label();
        paddMe.setMinHeight(20);
        contentRoot.add(paddMe, 0, 1);
   
		setupStage(stage, new Scene(contentRoot, screenX, screenY, Color.BLUE));
	}
	/**Set-Up-Methods **/
	private void setUpTitle()
	{
		contentTitle = new Label("Birthday Tracker"); 
		contentTitle.setFont(new Font("Cambbria", 40));
		contentRoot.add(contentTitle, 0, 0, 4, 1);
	}
	private void setupStage(Stage stage, Scene scene)
	{
		stage.setTitle(appTitle);
		stage.setScene(scene);
		stage.show();
	}
	/**Set Up Methods for Application Screen**/
	private void setUpContentGrid()
	{
		contentRoot = new GridPane();
		contentRoot.setPadding(new Insets(40, 40, 40, 40));
		contentRoot.setHgap(10);
		contentRoot.setVgap(10);
	}

	private void setUpInputLables()
	{
		// Friend Attributes 
		inputLabels = new Label[5];
        String[] inputLabelNames = {"Person's Name: ", "Likes: ", "Dislikes: ", "B/Day - Day: ", "B/Day - Month: "};
        int width = 80, height = 25;
        // friendTextFields
        friendTextFields = new TextField[5];
        
        for(int i = 0; i < inputLabels.length; i++) {
        		//Labels
        		inputLabels[i] = new Label(inputLabelNames[i]);
        		inputLabels[i].minWidth(width);
        		inputLabels[i].minHeight(height);
        		// Input
        		friendTextFields[i] = new TextField();
        		friendTextFields[i].setMinWidth(300);
        		friendTextFields[i].setMinHeight(height - 5);
        		// Add to Grid
        		contentRoot.add(inputLabels[i], 0, i + 2);
        		contentRoot.add(friendTextFields[i], 1, i + 2);
        }      
	}
	private void setUpAppDataModButtons()
	{
		 // Friend Data Modifiers 
		dataMods = new Button[5]; // new, save, delete, find, close 
        String[] dataModsNames = {"New", "Save", "Delete", "Find", "Close"};
       
        for(int i = 0; i < dataMods.length - 1; i++) {
        		 // Data Mods
        		dataMods[i] = new Button(dataModsNames[i]);
        		dataMods[i].setMinWidth(80);
        		// Add to Grid
        		contentRoot.add(dataMods[i], 4, i + 2);
        }
        
        TextField temp = new TextField();
        temp.setMinSize(40, 20);
        // Close button needs to be at the bottom line 
        dataMods[4] = new Button(dataModsNames[4]);
        dataMods[4].setMinWidth(80);
        contentRoot.add(dataMods[4], 4, 8);
	}
	private void setUpAppStateButtons()
	{
		 // Data Selector/'s finders, exit
		appNavigationButtons = new Button[5];
        String[] navSymboles = {"|<", "<", ">", ">|", "Birthdays In Month Of: "};
        
        // Bottom Collection of Button Modifiers 
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		for (int i = 0; i < appNavigationButtons.length; i++) {
			appNavigationButtons[i] = new Button(navSymboles[i]);
			hbox.getChildren().add(appNavigationButtons[i]);
		}
		TextField search = new TextField();
		search.setMinWidth(80); search.setMaxWidth(110);
		hbox.getChildren().add(search);
		contentRoot.add(hbox, 0, 8, 4 ,1);
	}
	
	private void setUpButtonEvents()
	{
		// TODO: dataMod Buttons
		// TODO: appNavigationButtons
	}
	
}// End of Class 
