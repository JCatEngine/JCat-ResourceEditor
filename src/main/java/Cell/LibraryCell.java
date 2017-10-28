package Cell;


import Bean.ResourceData;
import JavaFxPlus.Tool.AlertTool;
import JavaFxPlus.Tool.FxmlTool;
import JavaFxPlus.ViewHelper.GetAbleListCell;
import JavaFxPlus.ViewHelper.ListViewHelper;
import Manager.ImageManager;
import Manager.Library;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LibraryCell extends GetAbleListCell<ResourceData>{

	
	public LibraryCell(ListViewHelper<ResourceData> libraryListHelper) {
		super(libraryListHelper);
	}

	@FXML
	private ImageView typeLV;
	@FXML
	private Label nameLB;
	@FXML
	private TextField nameTF;
	
	
	private ChangeListener<Boolean> changeListener=new ChangeListener<Boolean>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
				Boolean newValue) {
			if(newValue.booleanValue()==false)
			{
				tryCommit();
			}
			
		}
	};
	
	

	@Override
	protected void updateItem(ResourceData item, boolean empty) {
		super.updateItem(item, empty);
		setText(null);
		setGraphic(null);

		
		if(item!=null)
		{
			if(isEditing())
			{
				HBox hBox=(HBox) FxmlTool.loadFxml("item_library_edit", this);
				typeLV.setImage(ImageManager.getInstance().getIcon(item.getImageName()));
				nameTF.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						tryCommit();
						
					}
				});
				this.focusedProperty().addListener(changeListener);
				setGraphic(hBox);
			}
			else
			{
				this.focusedProperty().removeListener(changeListener);
				HBox hBox=(HBox) FxmlTool.loadFxml("item_library", this);
				nameLB.setText(item.name);
				typeLV.setImage(ImageManager.getInstance().getIcon(item.getImageName()));
				setGraphic(hBox);
			}
			
			
			
		}
		
	}
	
	protected void tryCommit() {
		if(checkName())
		{
			getItem().name=nameTF.getText();
		}
		else
		{
			AlertTool.showSimpleAlert("错误","名字不能为空,不能重复");
		}
		
		commitEdit(getItem());
	}

	protected boolean checkName() {
		
		String name=nameTF.getText();
		if(name==null||name.equals(""))
		{
			return false;
		}
		else
		{
			return !Library.getInstance().hasSameName(name);
		}
	}

	/**
	 * when nameTF lose focus and select a new item occur at the same time,
	 * list seem will not to update,so i add this
	 */
	@Override
	public void commitEdit(ResourceData newValue) {
		// TODO Auto-generated method stub
		super.commitEdit(newValue);
		updateItem(getItem(), isEmpty());
	}
	
	@Override
	public void startEdit() {
		// TODO Auto-generated method stub
		super.startEdit();
		updateItem(getItem(), isEmpty());
	}
}


