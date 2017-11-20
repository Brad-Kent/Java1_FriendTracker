package ICTPRG418;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//TODO: make this source file a Functional and OOP like project!


// The rest is on my Main Computer, Updated featues, layout... 
public class FriendTracker extends Application
{
	// Final Static Members
	private static final String appTitle = "Friend Tracker";
	private static final int screenY = 400;
	private static final int screenX = 640;
	
	
	// FX Entery Point
	@Override
	public void start(Stage stage) throws Exception
	{
		// Top
		Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        GridPane contentRoot = new GridPane();
		contentRoot.setPadding(new Insets(60, 20, 20, 20));
		contentRoot.setHgap(10);
		contentRoot.setVgap(10);
		
		Label title = new Label("Birthday Tracker");
		title.setFont(new Font("Cambbria", 40));
		
        
        contentRoot.add(title, 0, 0, 2, 1);
        Label paddMe = new Label();
        paddMe.setMinHeight(30);
        contentRoot.add(paddMe, 0, 1);
        // Friend Attributes 
        Label[] inputLabels = new Label[5];
        String[] inputLabelNames = {"Person's Name: ", "Likes: ", "Dislikes: ",
        	"B/Day - Day: ", "B/Day - Month: "};
        int width = 80, height = 25;
        
        // Inputs
        TextField[] inputs = new TextField[5];
        
        for(int i = 0; i < inputLabels.length; i++) {
        		//Labels
        		inputLabels[i] = new Label(inputLabelNames[i]);
        		inputLabels[i].minWidth(width);
        		inputLabels[i].minHeight(height);
        		// Input
        		inputs[i] = new TextField();
        		inputs[i].setMinWidth(300);
        		inputs[i].setMinHeight(height - 5);
        		// Add to Grid
        		contentRoot.add(inputLabels[i], 0, i + 2);
        		contentRoot.add(inputs[i], 1, i + 2);
        }
        
        // Friend Data Modifiers 
        Button[] dataMods = new Button[5]; // new, save, delete, find
        String[] dataModsNames = {"New", "Save", "Delete", "Find", "Close"};
       
        for(int i = 0; i < dataMods.length; i++) {
        		 // Data Mods
        		dataMods[i] = new Button(dataModsNames[i]);
        		dataMods[i].setMinWidth(80);
        		// Add to Grid
        		contentRoot.add(dataMods[i], 4, i + 2);
        }
       
        //  Data Selector/'s finders, exit
        Button[] navigation = new Button[5];
        String[] navSymboles = {"|<", "<", ">", ">|", "Birthdays In Month Of: "};
        
       
        HBox hbox = new HBox();
        for(int i = 0; i < navigation.length; i++) {
        		navigation[i] = new Button(navSymboles[i]);
        		hbox.getChildren().add(navigation[i]);
        }
		
        contentRoot.add(hbox, 0, 8, 5, 1);
		
		//GridPane.setHalignment(btn, HPos.CENTER);
//		contentRoot.add(btn, 1, 0);
//		contentRoot.add(new Label("Hello"), 0, 0);
		//contentRoot.setGridLinesVisible(true);
		
      
       // Scene -> Stage ->> Show
		setupStage(stage, new Scene(contentRoot, screenX, screenY, Color.BLUE));
	}
	// Moduels / Functional 
	private void setupStage(Stage stage, Scene scene)
	{
		stage.setTitle(appTitle);
		stage.setScene(scene);
		stage.show();
	}
}
