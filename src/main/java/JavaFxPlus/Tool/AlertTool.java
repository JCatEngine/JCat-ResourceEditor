package JavaFxPlus.Tool;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertTool {

	private static Alert alert;

	//use a instance alert
	static 
	{
		alert = new Alert(AlertType.INFORMATION);
	}
	
	public static void showSimpleAlert(String title, String content) {
		
		if(!alert.isShowing())
		{
			alert.setTitle(title);
			alert.setHeaderText(content);
			alert.showAndWait();
		}
		
		
		
	}

}
