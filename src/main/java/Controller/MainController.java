package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Bean.AnimeClip;
import Bean.ResourceData;
import Cell.LibraryCell;
import JavaFxPlus.Tool.FileChooserTool;
import JavaFxPlus.ViewHelper.CanvasHelper;
import JavaFxPlus.ViewHelper.ImageViewHelper;
import JavaFxPlus.ViewHelper.ListViewHelper;
import Manager.ResourceType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	@FXML VBox centerPane;
	@FXML ImageView showIV;
	@FXML Canvas canvas;
	@FXML TextField inputTF;
	
	public static Stage stage;
	
	
	

	private GraphicsContext graphics;
	private CanvasHelper canvasHelper;
	private ImageViewHelper imageViewHelper;
	private ListViewHelper<ResourceData> libraryListHelper;
	
	/**
	 * sub pane and it's controller
	 */
	private ImageInspectorController imageInspectorController;
	private Parent imageInsepctorControllerPane;
	private ImageOpController imageOpController;
	private Parent imageOpControllerPane;
	
	private MovieclipInspectorController movieclipInspectorController;
	private Parent movieclipInspectorControllerPane;
	private MovieclipOpController movieclipOpController;
	private Parent movieclipOpControllerPane;
	
	private ParticleInspectorController particleInspectorController;
	private Parent particleInspectorControllerPane;
	private ParticleOpController particleOpController;
	private Parent particleOpControllerPane;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initView();
		
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_image.png"));
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_movieclip.png"));
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\icon_music.png"));
		getLibrary().importImage(new File("A:\\java\\JavaProject\\2017.10.26JCat-ResourceEditor\\_res\\img\\move.png"));

		
	}

	protected void initView() {
		initList();
		initInspector();
		initBottomPane();
		initCanvas();
		initSearchInput();
		
		
	}



	private void initSearchInput() {
		this.inputTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				getLibrary().fliterResource(inputTF.getText());
			}
		});
		
	}

	private void initCanvas() {
		
		this.imageViewHelper=new ImageViewHelper(showIV);
		this.canvasHelper=new CanvasHelper(canvas);
		graphics=canvasHelper.getGraphicsContext2D();
		canvasHelper.setLineWidth(5);
		canvasHelper.setColor(Color.GREEN);
	}

	private void initBottomPane() {
		//reload bottom pane
		try {
			//image
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_bottom_image.fxml").toURL());
			this.imageOpControllerPane= loader.load();
			this.imageOpController=loader.getController();
			this.imageOpController.setMainController(this);
			this.imageOpController.init();
			//movieclip
			loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_bottom_movieclip.fxml").toURL());
			this.movieclipOpControllerPane= loader.load();
			this.movieclipOpController=loader.getController();
			this.movieclipOpController.setMainController(this);
			this.movieclipOpController.init();
			//particle
			loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_bottom_particle.fxml").toURL());
			this.particleOpControllerPane= loader.load();
			this.particleOpController=loader.getController();
			this.particleOpController.setMainController(this);
			this.particleOpController.init();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * load all Inspector pane but do not show then
	 */
	private void initInspector() {
		
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_inspector_image.fxml").toURL());
			this.imageInsepctorControllerPane= loader.load();
			this.imageInspectorController=loader.getController();
			this.imageInspectorController.setMainController(this);
			this.imageInspectorController.init();
			
			loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_inspector_movieclip.fxml").toURL());
			this.movieclipInspectorControllerPane= loader.load();
			this.movieclipInspectorController=loader.getController();
			this.movieclipInspectorController.setMainController(this);
			this.movieclipInspectorController.init();
			
			loader=new FXMLLoader();
			loader.setLocation(new File("_res/fxml/pane_inspector_particle.fxml").toURL());
			this.particleInspectorControllerPane= loader.load();
			this.particleInspectorController=loader.getController();
			this.particleInspectorController.setMainController(this);
			this.particleInspectorController.init();
			
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
		this.libraryListHelper=new ListViewHelper<>();
		libraryLV.setCellFactory(param -> new LibraryCell(libraryListHelper));
		libraryLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ResourceData>() {

			@Override
			public void changed(ObservableValue<? extends ResourceData> observable, ResourceData oldValue,
					ResourceData newValue) {
				
				if(newValue!=null)
				{
					//if a image,show image
					if(newValue.type==ResourceType.TEXTURE)
					{
						//set library small image
						selectIV.setImage((Image) newValue.data);
					
					}
					//if a movieclip,play anime
					else if(newValue.type==ResourceType.MOVIECLIP)
					{
						
					}
					
					canvasHelper.clear();
					imageViewHelper.stop();
					
					updateInspector();
					//update bottom pane
					updateBottomPane();
					//update center pane
					updateCenterPane();
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
		menuItem2.setOnAction(event -> renameCurrentSelectedItems());
		MenuItem menuItem3=new MenuItem("导入图片");
		menuItem3.setOnAction(event -> removeCurrentSelectedItems());
		MenuItem menuItem4=new MenuItem("导出图片");
		menuItem4.setOnAction(event -> removeCurrentSelectedItems());
		
		contextMenuCanvas.getItems().addAll(menuItem,menuItem2,menuItem3,menuItem4);
		libraryLV.setContextMenu(contextMenuCanvas);
		
	}

	protected void updateInspector() {
		leftPane.getChildren().clear();
		ResourceData data=libraryLV.getSelectionModel().getSelectedItem();
		if(data.type==ResourceType.TEXTURE)
		{
			leftPane.getChildren().add(imageInsepctorControllerPane);
			imageInspectorController.update(data);
		}
		else if(data.type==ResourceType.MOVIECLIP)
		{
			leftPane.getChildren().add(movieclipInspectorControllerPane);
			movieclipInspectorController.update(data);
		}
		else if(data.type==ResourceType.PARTICLE)
		{
			leftPane.getChildren().add(particleInspectorControllerPane);
			particleInspectorController.update(data);
		}
	}

	private void renameCurrentSelectedItems() {
		LibraryCell cell=(LibraryCell) libraryListHelper.getCell(libraryLV.getSelectionModel().getSelectedIndex());
		cell.startEdit();
	}

	protected void updateCenterPane() {
		ResourceData data=libraryLV.getSelectionModel().getSelectedItem();
		if(data.type==ResourceType.TEXTURE)
		{
			//set the width and height to fit the width and height of image
			Image image=(Image) data.data;
			canvasHelper.resizeToImage(image);
			imageViewHelper.resizeToImage(image);
			
			showIV.setImage((Image) data.data);
		}
		else if(data.type==ResourceType.MOVIECLIP)
		{
			AnimeClip animeClip=(AnimeClip) data.data;
			Image image=animeClip.getTexture();
			imageViewHelper.play(animeClip);
			
		}
		
	}

	protected void updateBottomPane() {
		bottomPane.getChildren().clear();
		ResourceData data=libraryLV.getSelectionModel().getSelectedItem();
		if(data.type==ResourceType.TEXTURE)
		{
			bottomPane.getChildren().add(imageOpControllerPane);
		}
		else if(data.type==ResourceType.MOVIECLIP)
		{
			bottomPane.getChildren().add(movieclipOpControllerPane);
			movieclipOpController.update(data);
		}
		
		
	}

	protected void removeCurrentSelectedItems() {
		getLibrary().removeAll(libraryLV.getSelectionModel().getSelectedItems());
		
	}

	/**
	 * import image to library
	 * @param event
	 */
	@FXML public void press_ImportImage(ActionEvent event) {
		
		ArrayList<ExtensionFilter> arrayList=new ArrayList<>();
		arrayList.add(new FileChooser.ExtensionFilter("All Images", "*.jpg","*.png","*.jpeg"));
		List<File> files=FileChooserTool.openMultipleDialog("选择图片",arrayList,stage);
		if(files!=null&&files.size()>0)
		{
			for (File file : files) {
				getLibrary().importImage(file);
			}
		}
		
	}





	public ResourceData getSelectedItem() {
		return libraryLV.getSelectionModel().getSelectedItem();
		
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void redrawCanvasSlice(int x, int y) {
		
		if(getSelectedItem()!=null)
		{
			Image image=(Image) getSelectedItem().data;
		
			//graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvasHelper.clear();
			canvasHelper.drawGridLine(x,y);
			
		}
	}

	@FXML public void press_createAnime() {
		//getLibrary().createEmptyAnime();
		
	}

	@FXML public void press_importData() {
		ArrayList<ExtensionFilter> arrayList=new ArrayList<>();
		arrayList.add(new FileChooser.ExtensionFilter("资源文件", "*.dgsb"));
		File file=FileChooserTool.openDialog("导入", arrayList, stage);
		getLibrary().inputFromFile(file);
	}

	
	@FXML public void press_outputData() {
		ArrayList<ExtensionFilter> arrayList=new ArrayList<>();
		arrayList.add(new FileChooser.ExtensionFilter("资源文件", "*.dgsb"));
		File file=FileChooserTool.openDialog("保存", arrayList, stage);
	
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		getLibrary().outputToFile(file);
	}

	
	
	
	
}
