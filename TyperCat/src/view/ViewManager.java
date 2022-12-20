package view;


import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.CAT;
import model.CatPicker;
import model.TyperCatButton;
import model.TyperCatSubScene;


public class ViewManager {
	
	private static final int HEIGHT = 700;
	private static final int WIDTH = 1300;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 80;
	private final static int MENU_BUTTON_START_Y = 590;
	
	
	private TyperCatSubScene creditsSubscene;
	private TyperCatSubScene helpSubscene;
	private TyperCatSubScene scoreSubscene;
	private TyperCatSubScene shipChooserSubscene;
	
	private TyperCatSubScene sceneToHide;
	
	
	
	
	List<TyperCatButton> menuButtons;
	
	List<CatPicker> shipsList;
	private CAT choosenShip;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT );
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		CreateButtons();
		createBackground();
		createLogo();
		String userHome = System.getProperty("user.dir");
		view.SoundHandler.runMusic(userHome+"/src/resources/bg_music.wav", 1);
	}
	
	
	private void showSubScene(TyperCatSubScene subScene) {
		if (sceneToHide != null) { 
			sceneToHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	
	
	
	private void createSubScenes() {
		
		creditsSubscene = new TyperCatSubScene();
		mainPane.getChildren().add(creditsSubscene);
		helpSubscene = new TyperCatSubScene();
		mainPane.getChildren().add(helpSubscene);
		scoreSubscene = new TyperCatSubScene();
		mainPane.getChildren().add(scoreSubscene);

		
		createShipChooserSubScene();
	
	}

	
	private void createShipChooserSubScene() {
		shipChooserSubscene = new TyperCatSubScene();
		shipChooserSubscene.toFront();
		mainPane.getChildren().add(shipChooserSubscene);
;
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR CAT");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		shipChooserSubscene.getPane().getChildren().add(chooseShipLabel);
		shipChooserSubscene.getPane().getChildren().add(createShipsToChoose());
		shipChooserSubscene.getPane().getChildren().add(createButtonToStart());
		
		
		
	}
	
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(30);
		shipsList = new ArrayList<>();
		for (CAT cat : CAT.values()) {
			CatPicker shipToPick = new CatPicker(cat);
			shipToPick.setPrefHeight(80);

			
			shipsList.add(shipToPick);
			box.getChildren().add(shipToPick);
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (CatPicker ship : shipsList) {
						ship.setIsCircleChoosen(false);
					}
					shipToPick.setIsCircleChoosen(true);
					choosenShip = shipToPick.getShip();
					
				}
			});
		}
		
		box.setLayoutX(300 - (118*2));
		box.setLayoutY(100);
		return box;
	}
	
	
	
	private TyperCatButton createButtonToStart() {
		TyperCatButton startButton = new TyperCatButton("CHOOSE");
		startButton.setLayoutX(200);
		startButton.setLayoutY(275);
		
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			

			@Override
			public void handle(ActionEvent event) {
				if (choosenShip != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenShip);;
				}
				
			}
		});
		
		return startButton;
	}


	public Stage getMainStage() {
		return mainStage;
	}
	
	private void AddMenuButtons(TyperCatButton button) {
		button.setLayoutX(MENU_BUTTON_START_X + menuButtons.size() * 300);
		button.setLayoutY(MENU_BUTTON_START_Y );
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	
	
	private void CreateButtons() {
		createStartButton();
		//createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		TyperCatButton startButton = new TyperCatButton("PLAY");
		AddMenuButtons(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(shipChooserSubscene);
				
				
			}
		});
	}
	
	private void createScoresButton() {
		TyperCatButton scoresButton = new TyperCatButton("SCORES");
		AddMenuButtons(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubscene);
				
			}
		});
	}
	
	private void createHelpButton() {
		TyperCatButton helpButton = new TyperCatButton("OPTIONS");
		AddMenuButtons(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubscene);
				
			}
		});
	}
	
	private void createCreditsButton() {
		
		TyperCatButton creditsButton = new TyperCatButton("CREDITS");
		AddMenuButtons(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubscene);
				
			}
		});
	}
	
	private void createExitButton() {
		TyperCatButton exitButton = new TyperCatButton("EXIT");
		AddMenuButtons(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		});
		
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/resources/ocean_temp.png", 1300, 700, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("/resources/title.png");

		logo.setLayoutX(380);
		logo.setLayoutY(10);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
		
				
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
		});
		
		mainPane.getChildren().add(logo);
		
	}
	
	
	
	

}
