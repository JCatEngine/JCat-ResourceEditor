package Main;

import java.io.File;
import java.io.IOException;

import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Entry extends Application{

	
	public static void main(String[] args) throws IOException {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(new File("_res/fxml/main.fxml").toURL());
		Parent root= loader.load();
		Scene scene = new Scene(root);	
		stage.setTitle("main");
		stage.setScene(scene);
		stage.show();
		
		MainController.stage=stage;
	}
	
	
}