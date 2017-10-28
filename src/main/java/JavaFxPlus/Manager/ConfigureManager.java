package JavaFxPlus.Manager;

import java.io.File;

import org.json.JSONObject;

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
	
	
	
	/**
	 * lastChoosePath 
	 */
	private File lastChoosePath;
	/**
	 * lastSavePath
	 */
	private File lastSavePath;

	public ConfigureManager() {
		loadConfigure();
	}

	public void loadConfigure() {
		
		File file=new File(Config.CONFIGUE);
		if(file.exists())
		{
			String result=FileTool.readFile(file);
			JSONObject jsonObject=new JSONObject(result);
			_parseJson(jsonObject);
		}
	}
	
	private void _parseJson(JSONObject jsonObject) {
	
		
		try {
			this.lastChoosePath=new File(jsonObject.getString("lastChoosePath"));
		} catch (Exception e) {
		}
		
		try {
			this.lastSavePath=new File(jsonObject.getString("lastSavePath"));
		} catch (Exception e) {
		}
		
		
	}

	

	public void saveConfigure() {
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("lastChoosePath", lastChoosePath.getAbsolutePath());
		jsonObject.put("lastSavePath", lastSavePath.getAbsolutePath());
		
		_outputJson(jsonObject);
		
	}

	private void _outputJson(JSONObject jsonObject) {
		
		FileTool.writeFile(new File(Config.CONFIGUE), jsonObject.toString());
		
	}

	
	
	public File getLastChoosePath() {
		// TODO Auto-generated method stub
		return lastChoosePath;
	}

	public void setLastChoosePath(File file) {
		this.lastChoosePath=file;
		saveConfigure();
		
	}

	public File getLastSavePath() {
		return lastSavePath;
	}

	public void setLastSavePath(File lastSavePath) {
		this.lastSavePath = lastSavePath;
		saveConfigure();
	}

	
	
	
}
