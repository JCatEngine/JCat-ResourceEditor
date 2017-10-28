package Controller;

import Bean.ResourceData;
import Manager.ConfigureManager;
import Manager.ImageManager;
import Manager.Library;

public abstract class BaseController {

	protected Library getLibrary()
	{
		
		return Library.getInstance();
				
	}
	protected ConfigureManager getConfigureManager()
	{
		
		return ConfigureManager.getInstance();
				
	}
	
	protected ImageManager getImageManager()
	{
		
		return ImageManager.getInstance();
				
	}
	
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
