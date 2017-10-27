package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ResourceData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class ImageOpController extends BaseController implements Initializable{

	@FXML TitledPane ksqbAC;
	@FXML CheckBox animeCB;
	@FXML TextField hqpLB;
	@FXML TextField lqpLB;
	@FXML Label widthLB;
	@FXML Label heightLB;
	@FXML Button qbButton;
	@FXML TitledPane qpAC;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initView();
	}
	private void initView() {
		//expand first
		ksqbAC.setExpanded(true);
		hqpLB.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					
					
				} catch (Exception e) {
					
				}
				
			}
		});
		
		
	}
	
	

}
