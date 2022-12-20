package view;


import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.CAT;
import model.SmallInfoLabel;
import model.TyperCatButton;
import model.WordDisplay;
import model.WordFish;
import view.SoundHandler;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;	
	
	private BackgroundImage backgroundImage;
	private final static int GAME_WIDTH = 1300;
	private final static int GAME_HEIGHT = 700;

	private final static String BACKGROUND_IMAGE = "/resources/ocean_temp.png";

	private Stage menuStage;
	private ImageView ship;
	private String level;
	
	private AnimationTimer gameTimer;

	private WordDisplay wordView;
  	private String userInput = "" ;
  	

	private void initializeStage() { 
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		gameScene.setOnKeyPressed(e -> onKeyPress(e.getCode().toString()));
	}
	public GameViewManager() {
		backgroundImage = new BackgroundImage(
		        new Image(BACKGROUND_IMAGE, GAME_WIDTH, GAME_HEIGHT, false, true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT, null);
		
		initializeStage();

	}
	
	 
	public void gameProper(CAT choosenShip) {
		
	  // Setting the initial value of userInput to be an empty string
		level = choosenShip.getUrlLevel();
		
		WordFish wordFish = new WordFish(level);
		String word = wordFish.getRandomWord();
		
		wordView = new WordDisplay(word);
		gamePane.getChildren().add(wordView);
		wordView.setLayoutX(GAME_WIDTH/2 - wordView.getBoundsInLocal().getWidth()/2);
		wordView.setLayoutY(GAME_HEIGHT/2 - wordView.getBoundsInLocal().getHeight()/2);
		userInput = "";
	}
 
	 private void showNextWord() {
		 WordFish wordFish = new WordFish(level);
		 	String word = wordFish.getRandomWord();
			wordView = new WordDisplay(word);
			gamePane.getChildren().add(wordView);
			wordView.setLayoutX(GAME_WIDTH/2 - wordView.getBoundsInLocal().getWidth()/2);
			wordView.setLayoutY(GAME_HEIGHT/2 - wordView.getBoundsInLocal().getHeight()/2);
			userInput = "";
	  }
	 
	  private void onKeyPress(String key) {
	      // Check if the key pressed is a letter
	      if (key.length() == 1) {
	    	
	    	  if (!wordView.handleKeyPress(key)){
	    	
	    			  // Incorrect letter, move the ship faster to the right
	    			ship.setX(ship.getX() + 20);
	    			
	    	  };
				
				if (wordView.isFinished()) {
					//score += 1;
					//System.out.println(score);
					ship.setX(ship.getX() - 30);
					showNextWord();
					
				}
				
	      }
	  }
	      

	//Create a method to switch to the main menu scene
	private void switchToMainMenu() {
	  //Create a new main menu view manager and show the main menu stage
		ViewManager manager = new ViewManager();
		Stage primaryStage = manager.getMainStage();
		primaryStage.show();
	  //Close the game stage
	  gameStage.close();
	}


	
	public void createNewGame(Stage menuStage, CAT choosenShip) {
	
		this.menuStage = menuStage;
		this.menuStage.hide();
		
		createGameLoop();
		addBackground();
		gameStage.show();
		gameStage.setResizable(false);
		gameProper(choosenShip);
		createShip(choosenShip);
		
		String userHome = System.getProperty("user.dir");
		view.SoundHandler.runMusic(userHome+"/src/resources/bg_music.wav", 0);
		view.SoundHandler.runMusic(userHome+"/src/resources/2.wav", 1);

	}
	
	private void createShip(CAT choosenShip) {
		ship = new ImageView(choosenShip.getUrl());
		ship.setFitHeight(150);
		ship.setFitWidth(150);
		ship.setLayoutX(GAME_WIDTH - 1250);
		ship.setLayoutY(GAME_HEIGHT/1.5);
		gamePane.getChildren().add(ship);
	}
	

	
	  private void moveShip(double x, double y) {
	      ship.setLayoutX(x);
	      ship.setLayoutY(y);
	  }
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveShip(ship.getLayoutX() + 0.2, ship.getLayoutY());

			}
		};
		gameTimer.start();
	} 	
	private void addBackground() {
	    // Use the same BackgroundImage object instead of creating a new one
	    gamePane.setBackground(new Background(backgroundImage));
	}
}