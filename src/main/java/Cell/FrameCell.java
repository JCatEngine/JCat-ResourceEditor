package Cell;

import Bean.AnimeClip.Frame;
import Bean.ResourceData;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class FrameCell extends ListCell<Frame>{

	
	@Override
	protected void updateItem(Frame item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
		setText(null);
		setGraphic(null);
		if(item!=null)
		{
			HBox hBox=new HBox();
			Rectangle rectangle=new Rectangle(30, 30);
			
			
		}
	}
}
