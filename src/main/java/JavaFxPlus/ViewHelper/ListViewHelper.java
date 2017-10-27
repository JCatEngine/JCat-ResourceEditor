package JavaFxPlus.ViewHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListViewHelper<T> {

	private ObservableList<GetAbleListCell<T>> list=FXCollections.observableArrayList();

	
	public GetAbleListCell<T> getCell(int index) {
		
		return list.get(index);
	}

	

	public void setCell(int index, GetAbleListCell<T> getAbleListCell) {
		while(list.size()<index+1)
		{
			list.add(null);
		}
		list.set(index, getAbleListCell);
		
	}

}
