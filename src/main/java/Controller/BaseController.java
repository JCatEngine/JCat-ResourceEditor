package Controller;

import Manager.ConfigureManager;
import Manager.ImageManager;
import Parser.Library;

public class BaseController {

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
}
