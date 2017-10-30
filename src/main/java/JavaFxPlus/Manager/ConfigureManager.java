package JavaFxPlus.Manager;

import java.io.File;

import com.google.gson.Gson;

import JavaFxPlus.Tool.FileTool;
import Main.Config;

/**
 * configureManager
 * @author Administrator
 *
 */
public class ConfigureManager {

	private static ConfigureManager instance;

	public static ConfigureManager getInstance()
	{
		if(instance==null)
		{
			instance=new ConfigureManager();
		}
		return instance;
		
	}
	
	static
	{
		//init directory
		File file=new File("data");
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		
	}
	
	
	
	
	private class ConfigData
	{

		/**
		 * lastChoosePath 
		 */
		private String lastChoosePath;
		/**
		 * lastSavePath
		 */
		private String lastSavePath;
		
		
	}

	private ConfigData configData=new ConfigData();
	
	
	
	
	public ConfigureManager() {
		loadConfigure();
	}

	public void loadConfigure() {
		
		File file=new File(Config.CONFIGUE);
		if(file.exists())
		{
			String result=FileTool.readFile(file);
			Gson gson=new Gson();
			this.configData=gson.fromJson(result, ConfigData.class);
		
			
			
		}
	
	}
	
	

	

	public void saveConfigure() {
		
		Gson gson=new Gson();
		String json=gson.toJson(this.configData);
		
		
		FileTool.writeFile(new File(Config.CONFIGUE),json);
		
	}



	
	
	public File getLastChoosePath() {
		
		if(configData.lastChoosePath!=null)
		{
			return new File(configData.lastChoosePath);
		}
		else
		{
			return null;
		}
	}

	public void setLastChoosePath(File file) {
		this.configData.lastChoosePath=file.getAbsolutePath();
		saveConfigure();
		
	}

	public File getLastSavePath() {
		if(configData.lastSavePath!=null)
		{
			return new File(configData.lastSavePath);
		}
		else
		{
			return null;
		}
	}

	public void setLastSavePath(File lastSavePath) {
		this.configData.lastSavePath = lastSavePath.getAbsolutePath();
		saveConfigure();
	}

	
	
	
}
