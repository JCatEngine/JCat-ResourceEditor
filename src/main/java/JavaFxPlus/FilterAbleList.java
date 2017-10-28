package JavaFxPlus;

import java.util.List;
import java.util.function.Predicate;

import Bean.ResourceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilterAbleList<T> {

	/**
	 * list save all data
	 */
	private ObservableList<T> cacheList;
	/**
	 * list that can be visible
	 */
	private ObservableList<T> showList;

	public FilterAbleList(ObservableList<T> list) {
		this.cacheList=list;
		this.showList=FXCollections.observableArrayList();
		showList.addAll(list);
	}

	public void add(T data) {
		cacheList.add(data);
		showList.add(data);
	}

	public ObservableList<T> getList() {
		// TODO Auto-generated method stub
		return showList;
	}

	public void remove(T data) {

		cacheList.remove(data);
		showList.remove(data);
	}

	public List<T> filterList(Predicate<T> predicate) {
		
		//fliter cache list,and add data to show List
		List<T> list=cacheList.filtered(predicate);
		showList.clear();
		for (T t : list) {
			showList.add(t);
		}
		
		
		return showList;
	}

	public List<T> filter(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return cacheList.filtered(predicate);
	}

	
	
	
	

}
