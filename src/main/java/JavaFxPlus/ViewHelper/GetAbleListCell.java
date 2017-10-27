package JavaFxPlus.ViewHelper;

import javafx.scene.control.ListCell;

public class GetAbleListCell<T> extends ListCell<T>{
	
	private ListViewHelper<T> libraryListHelper;

	public GetAbleListCell(ListViewHelper<T> libraryListHelper) {
		this.libraryListHelper = libraryListHelper;
		
	
	}
	
	@Override
	public void updateIndex(int i) {
		// TODO Auto-generated method stub
		super.updateIndex(i);
		
		if(getIndex()>0)
		{
			libraryListHelper.setCell(getIndex(),this);
		}
	}
	
}
