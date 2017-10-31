package JavaFxPlus.Tool;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.Objects;

import Main.Config;
import com.google.common.base.Preconditions;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import  static  com.google.common.base.Preconditions.*;

public class FxmlTool {

	
	public static Parent loadFxml(String path, Object controller) {

		checkNotNull(path);

		FXMLLoader loader=new FXMLLoader();
		if(controller!=null)
		{
			loader.setController(controller);
		}
		
		try {
			loader.setLocation(new File(Config.FXML_DIRECTORY +path+".fxml").toURL());
			return loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}

	/**
	 * load image from img directory through file name
	 * @param name
	 * @return
	 */
	public static Image loadImage(String name) {

		checkNotNull(name);
		
		try {
			return new Image(new File(Config.IMAGE_DIRECTORY+name+".png").toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new InvalidParameterException();
	}

}
