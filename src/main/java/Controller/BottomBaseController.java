package Controller;

import Bean.ResourceData;

public abstract class BottomBaseController extends BaseController{

	protected MainController mainController;

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public ResourceData getSelectedItem() {
		
		return mainController.getSelectedItem();
	}
	
	public void init() {
		initView();
		
	}

	protected abstract void initView();
	
}
