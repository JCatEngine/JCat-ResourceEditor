package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ResourceData;
import JavaFxPlus.Tool.AlertTool;
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
import javafx.scene.control.Accordion;
import javafx.scene.layout.HBox;

public class ImageOpController extends BaseController implements Initializable{

	@FXML TitledPane ksqbAC;
	@FXML CheckBox animeCB;
	@FXML TextField hqpLB;
	@FXML TextField lqpLB;
	@FXML Label widthLB;
	@FXML Label heightLB;
	@FXML Button qbButton;
	@FXML TitledPane qpAC;
	@FXML Accordion acc;
	@FXML HBox jgzsHbox;
	@FXML TextField jgzsTF;
	@FXML CheckBox zscdbCB;

	protected void initView() {
		//expand first
		acc.setExpandedPane(ksqbAC);
		hqpLB.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ResourceData data=getSelectedItem();
				Image image=(Image) data.data;
				try {
					int value=Integer.parseInt(newValue);
					widthLB.setText(image.getWidth()/value+"");
					redrawCanvas();
				} catch (Exception e) {
					e.printStackTrace();
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
					redrawCanvas();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	
		jgzsHbox.setVisible(false);
		zscdbCB.setVisible(false);
		animeCB.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				jgzsHbox.setVisible(newValue);
				zscdbCB.setVisible(newValue);
				
			}
		});
		
		
		
	}

	protected void redrawCanvas() {
		
		try {
			int x=Integer.parseInt(hqpLB.getText());
			int y=Integer.parseInt(lqpLB.getText());
			getMainController().redrawCanvasSlice(x,y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	
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
			int jgzs=Integer.parseInt(jgzsTF.getText());
			Boolean anime=animeCB.isSelected();
			Boolean zscdh=zscdbCB.isSelected();
			getLibrary().sliceAndAdd(image,x,y,resourceData.name,anime,jgzs,zscdh);
			
		} catch (Exception e) {
			AlertTool.showSimpleAlert("错误", "参数错误");
		}
		
	}

	

}
