package view;

import java.util.Arrays;
import java.util.Comparator;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Friend;

public class FriendTracker 
{
	// Screen Fields   
	//private static JFXPanel fxPanel = new JFXPanel();
	private static final String appTitle = "Friend Tracker";
	private static final int screenY = 400;
	private static final int screenX = 640;
	private Controller conn;

	/// Root Content Branch 
	private GridPane contentRoot;
	private Label contentTitle;
	private Stage stage;
	private Scene scene;
	
	// Friend Data Widgets 
	private Label    [] inputLabels         ; 
	private TextField[] friendTextFields    ;
	private Button   [] dataMods            ;
    private Button   [] appNavigationButtons;
    private TextField search;
    
    // Search Result View
    private SearchResultView searchResultView;

    /**Construtor**/
	public FriendTracker()
	{
		setUpContentGrid      ();
		setUpTitle            ();
		setUpInputLables      ();
		setUpAppDataModButtons();
		setUpAppStateButtons  ();
		
		searchResultView = new SearchResultView();
	}
	/**Getters & Setters**/
	public TextField[] getFriendTextFields    () { return friendTextFields    ; }
	public Button   [] getDataMods            () { return dataMods            ; }
	public Button   [] getAppNavigationButtons() { return appNavigationButtons; }
	public Stage       getStage			     () { return stage               ; }
	public TextField getSearch                () { return search              ; }
	
	public SearchResultView getSearchResultView() { return searchResultView; }


	public void setFriendTextFields    (TextField[] friendTextFields    ) { this.friendTextFields     = friendTextFields    ; }
	public void setDataMods            (Button   [] dataMods            ) { this.dataMods             = dataMods            ; }
	public void setAppNavigationButtons(Button   [] appNavigationButtons) { this.appNavigationButtons = appNavigationButtons; }

	/**JavaFx Applicaton**/
	//public void launchApp(String[] args, Controller conn){  Application.launch(this.getClass(), args); Platform.runLater( () -> conn.setView(getInstance()));}
	public void start(Stage stage) 
	{
        Label paddMe = new Label();
        paddMe.setMinHeight(20);
        contentRoot.add(paddMe, 0, 1);
   
        this.stage = stage;
		scene = new Scene(contentRoot, screenX, screenY, Color.BLUE);
		setupStage();
	}
	
	/**Set-Up-Methods **/
	private void setUpTitle()
	{
		contentTitle = new Label("Birthday Tracker"); 
		contentTitle.setFont(new Font("Cambbria", 40));
		contentRoot.add(contentTitle, 0, 0, 4, 1);
	}
	private void setupStage()
	{
		stage.setResizable(false   );
		stage.setTitle    (appTitle);
		stage.setScene    (scene   );
		stage.show        (        );
	}
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
		search = new TextField();
		search.setMinWidth(80); search.setMaxWidth(110);
		hbox.getChildren().add(search);
		contentRoot.add(hbox, 0, 8, 4 ,1);
	}
	public class SearchResultView
	{
		private static final String appTitle = "Birtthdays by Month";
		private static final int screenY = 400;
		private static final int screenX = 640;
		
		private Stage stage ;
		private Scene scene;
		
		private VBox     contentRoot;
		private Label    label      ;
		private TextArea ta_Bdays   ;
		
		public void addFriendDatas(String[][] data)
		{
			Arrays.sort(data, new Comparator<String[]>() {
				@Override
				public int compare(String[] o1, String[] o2) {
					return o1[3].compareToIgnoreCase(o2[3]);
				}
			});
			
			ta_Bdays.clear();
			
			for(String[] friend : data) {
				for(int i =  0; i < friend.length; i++) {
					ta_Bdays.appendText(" - " + friend[i]);
					
				}
					
				ta_Bdays.appendText("\n");
			}
			
		}
	    public SearchResultView()
		{
			setUpContentRoot();
			setUpScene      ();
			setUpStage      ();
		}
		/** SetUp Methods **/
		private void setUpContentRoot()
		{
			// Needs more data: month of year 
			label = new Label("Birthdays for: ");
			label.setFont(new Font("Cambbria", 20));
			label.setPadding(new Insets(5));
		
			ta_Bdays = new TextArea();
			ta_Bdays.setMaxSize(600, 360);
			ta_Bdays.setMinHeight(320);
			ta_Bdays.setStyle("-fx-text-alignment: center;");
			contentRoot = new VBox(label, ta_Bdays);
			contentRoot.setPadding(new Insets(10, 0, 10, 20));
			//VBox.setMargin(contentRoot, new Insets(5));
		}
		private void setUpScene()
		{
			scene = new Scene(contentRoot, screenX, screenY);
		}
		private void setUpStage()
		{
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(appTitle);
			stage.setResizable(false);
			stage.show();
		}
	} // End of Innder Class
} // End of Class
