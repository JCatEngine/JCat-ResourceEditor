package Controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainController extends BaseController implements Initializable{

	public static Stage stage;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * import image to library
	 * @param event
	 */
	@FXML public void press_ImportImage(ActionEvent event) {
		File file=openImageFileChooser("选择图片");
		if(file!=null)
		{
//			try {
//				main.getHistoryManager().recordURL(file.toURL());
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			playMedia(main.getMediaManager().getMediaByLocalPath(file.getAbsolutePath()));
		}
		
	}

	
	


	private File openImageFileChooser(String title) {
		
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("image", "*.jpg"),
	                new FileChooser.ExtensionFilter("image", "*.jpeg"),
	                new FileChooser.ExtensionFilter("image", "*.png")
	            );
		
		if(getConfigureManager().getLastChoosePath()!=null)
		{
			fileChooser.setInitialDirectory(getConfigureManager().getLastChoosePath());
		}
		File file=fileChooser.showOpenDialog(stage);
		if(file!=null&&!file.isDirectory())
		{
			getConfigureManager().setLastChoosePath(file.getParentFile());
			return file;
		}
		
		return null;
	}
}
