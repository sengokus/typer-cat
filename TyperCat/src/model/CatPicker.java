package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CatPicker extends VBox {

	private ImageView circleImage;
	private ImageView shipImage;
	
	private String circleNotChoosen = "/resources/grey_circle.png";
	private String circleChoosen = "/resources/red_choosen.png";
	
	private CAT cat;
	
	private boolean isCircleChoosen;
	
	
	public CatPicker(CAT cat) {
		circleImage = new ImageView(circleNotChoosen);
		shipImage = new ImageView(cat.getUrl());
		this.cat = cat;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(shipImage);
		
	}
	
	public CAT getShip() {
		return cat;
	}
	
	public boolean getCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
		String userHome = System.getProperty("user.dir");
		//view.SoundHandler.RunMusic(userHome+"/src/resources/click.wav");
	}
}
