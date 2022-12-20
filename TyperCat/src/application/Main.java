package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.ViewManager;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			
			Image icon = new Image ("/resources/icon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Typer Cat");
			
			
			

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



