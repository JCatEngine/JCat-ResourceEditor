package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ResourceData;
import Parser.Library;
import Tool.AlertTool;
import Tool.ImageTool;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;

public class ImageOpController extends BottomBaseController implements Initializable{

	@FXML TitledPane ksqbAC;
	@FXML CheckBox animeCB;
	@FXML TextField hqpLB;
	@FXML TextField lqpLB;
	@FXML Label widthLB;
	@FXML Label heightLB;
	@FXML Button qbButton;
	@FXML TitledPane qpAC;

	protected void initView() {
		//expand first
		ksqbAC.setExpanded(true);
		hqpLB.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ResourceData data=getSelectedItem();
				Image image=(Image) data.data;
				try {
					int value=Integer.parseInt(newValue);
					widthLB.setText(image.getWidth()/value+"");
				} catch (Exception e) {
					
				}
				
			}
		});
		lqpLB.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ResourceData data=getSelectedItem();
				Image image=(Image) data.data;
				try {
					int value=Integer.parseInt(newValue);
					heightLB.setText(image.getHeight()/value+"");
				} catch (Exception e) {
					
				}
				
			}
		});
		
	
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML public void press_qiepian() {
		try {
			int x=Integer.parseInt(hqpLB.getText());
			int y=Integer.parseInt(lqpLB.getText());
			ResourceData resourceData=getSelectedItem();
			Image image=(Image) resourceData.data;
			getLibrary().sliceAndAdd(image,x,y,resourceData.name);
			
		} catch (Exception e) {
			AlertTool.showSimpleAlert("错误", "参数错误");
		}
		
	}

	

}
