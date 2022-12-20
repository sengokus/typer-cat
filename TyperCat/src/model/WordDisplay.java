package model;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WordDisplay extends HBox{
	
	private char[] letters;
	private int correctLetters = 0;
	private String word;
	private final String FONT = "/resources/PixelAE-Regular.ttf";
	
	public WordDisplay(String word) {
		this.word = word;
		letters = word.toUpperCase().toCharArray();
		for (char c: letters ) {
			Text letter = new Text (c + "");
			letter.setFont(Font.loadFont(getClass().getResourceAsStream(FONT), 50));
			
			getChildren().add(letter);
		}
		
		setAlignment(Pos.CENTER);
	}
	
	public boolean  handleKeyPress(String letter) {
		if (isFinished()) {
			return true;
		}
		
		char c = letters[correctLetters];
		
		if (letter.charAt(0) == c) {
			getChildren().get(correctLetters).setVisible(false);
			correctLetters++ ;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isFinished() {
		return correctLetters == letters.length;
	}
	
	  // Add a getWord method that returns the word being displayed
	  public String getWord() {
	    return word;
	  }

		
	  public void resetCorrectLetters() {
		  correctLetters = 0;
	  }

	
}
