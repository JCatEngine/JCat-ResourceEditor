package Controller;

import Bean.ResourceData;
import JavaFxPlus.Manager.ConfigureManager;
import Manager.ImageManager;
import Manager.Library;

public abstract class BaseController {

	protected Library getLibrary()
	{
		
		return Library.INSTANCE;
				
	}
	protected ConfigureManager getConfigureManager()
	{
		
		return ConfigureManager.INSTANCE;
				
	}
	
	protected ImageManager getImageManager()
	{
		
		return ImageManager.INSTANCE;
				
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
