package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ResourceData;
import Manager.ResourceType;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * left pane
 * @author Administrator
 *
 */
public class ImageInspectorController extends BaseController implements Initializable{

	@FXML Label typeLB;
	@FXML Label widthLB;
	@FXML Label idLB;
	@FXML Label heightLB;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void update(ResourceData newValue) {
			Image image=(Image) newValue.getData();
			typeLB.setText(newValue.getTypeName());
			widthLB.setText(image.getWidth()+"");
			heightLB.setText(image.getHeight()+"");
			idLB.setText(newValue.name);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

}
