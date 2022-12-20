package model;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TyperCatButton extends Button{
	
	private final String FONT_PATH = "/resources/PixelAE-Bold.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-size: 200 80; -fx-background-image: url('/resources/blank_button_2.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-size: 200 80; -fx-background-image: url('/resources/blank_button_1.png');";
	
	
	public TyperCatButton(String text) {
		setText(text);
		setTextFill(Color.WHITE);
		setButtonFont();
		setPrefWidth(200);
		setPrefHeight(80);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
		
	}

	private void setButtonFont() {
		
		setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 25));
		
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(76);
		setLayoutY(getLayoutY() + 4);
		
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(76);
		setLayoutY(getLayoutY() - 4);
		
	}
	
	private void initializeButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
				String userHome = System.getProperty("user.dir");
				//view.SoundHandler.RunMusic(userHome+"/src/resources/click.wav");
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
				
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());

				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}