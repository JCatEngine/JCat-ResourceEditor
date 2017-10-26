package Controller;

import Manager.ConfigureManager;
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
}
