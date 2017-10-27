package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Bean.ResourceData;
import Cell.LibraryCell;
import Parser.ResourceType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * deal with the main command,and load sub pane
 * @author Administrator
 *
 */
public class MainController extends BaseController implements Initializable{

	
	@FXML ListView<ResourceData> libraryLV;
	@FXML ImageView selectIV;
	@FXML VBox leftPane;
	@FXML VBox bottomPane;
	
	
	public static Stage stage;
	
	private InspectorController inspectorController;
	private ImageOpController imageOpController;
	private Parent imageOpControllerPane;
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initView();
		
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_image.png"));
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_movieclip.png"));
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_music.png"));

		
	}

	private void initView() {
		initList();
		initInspector();
		initBottomPane();
	}

	private void initBottomPane() {
		//reload bottom pane
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_bottom_image.fxml").toURL());
			this.imageOpControllerPane= loader.load();
			this.imageOpController=loader.getController();
			
//			FXMLLoader loader=new FXMLLoader();
//			loader.setLocation(new File("_res/fxml/pane_bottom_image.fxml").toURL());
//			Parent root= loader.load();
//			this.imageOpController=loader.getController();
//			
//			FXMLLoader loader=new FXMLLoader();
//			loader.setLocation(new File("_res/fxml/pane_bottom_image.fxml").toURL());
//			Parent root= loader.load();
//			this.imageOpController=loader.getController();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void initInspector() {
		
		FXMLLoader loader=new FXMLLoader();
		try {
			loader.setLocation(new File("_res/fxml/pane_inspector.fxml").toURL());
			Parent root= loader.load();
			leftPane.getChildren().add(root);
			inspectorController=loader.getController();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void initList() {
		libraryLV.setItems(getLibrary().getResources());
		libraryLV.setEditable(true);
		//set select multiple
		libraryLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		libraryLV.setCellFactory(param -> new LibraryCell());
		libraryLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ResourceData>() {

			@Override
			public void changed(ObservableValue<? extends ResourceData> observable, ResourceData oldValue,
					ResourceData newValue) {
				
				//if a image,show image
				if(newValue.type==ResourceType.TEXTURE)
				{
					//set image
					selectIV.setImage((Image) newValue.data);
					//update inspector
					inspectorController.update(newValue);
					//update bottom pane
					updateBottomPane();
					
				}
				//if a movieclip,play anime
				else
				{
					//remove
					updateBottomPane();
				}
				
			}
		});
		//when delete press,delete selected items
		libraryLV.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.DELETE)
				{
					removeCurrentSelectedItems();
					
				}
				
			}
		});
		//context menu1 (select multiple)
		ContextMenu contextMenuCanvas=new ContextMenu();
		MenuItem menuItem=new MenuItem("删除");
		menuItem.setOnAction(event -> removeCurrentSelectedItems());
		MenuItem menuItem2=new MenuItem("重命名");
		menuItem.setOnAction(event -> removeCurrentSelectedItems());
		MenuItem menuItem3=new MenuItem("导入图片");
		menuItem.setOnAction(event -> removeCurrentSelectedItems());
		
		contextMenuCanvas.getItems().addAll(menuItem,menuItem2,menuItem3);
		libraryLV.setContextMenu(contextMenuCanvas);
		
	}

	protected void updateBottomPane() {
		bottomPane.getChildren().clear();
		ResourceData data=libraryLV.getSelectionModel().getSelectedItem();
		if(data.type==ResourceType.TEXTURE)
		{
			bottomPane.getChildren().add(imageOpControllerPane);
		}
		
		
		
	}

	protected void removeCurrentSelectedItems() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * import image to library
	 * @param event
	 */
	@FXML public void press_ImportImage(ActionEvent event) {
		List<File> files=openImageFileChooser("选择图片");
		if(files!=null&&files.size()>0)
		{
			for (File file : files) {
				getLibrary().importImage(file);
			}
		}
		
	}

	
	


	private List<File> openImageFileChooser(String title) {
		
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().addAll(
				    new FileChooser.ExtensionFilter("All Images", "*.jpg","*.png","*.jpeg")
	            );
		
		//set init directory
		if(getConfigureManager().getLastChoosePath()!=null)
		{
			fileChooser.setInitialDirectory(getConfigureManager().getLastChoosePath());
		}
		List<File> files=fileChooser.showOpenMultipleDialog(stage);
		
		//save init directory
		if(files!=null&&files.size()>0)
		{
			File file=files.get(0);
			if(!file.isDirectory())
			{
				getConfigureManager().setLastChoosePath(file.getParentFile());
			}
			
		}
		
		return files;
	}
}
