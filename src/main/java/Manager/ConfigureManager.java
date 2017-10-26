package Manager;

import java.io.File;

import org.json.JSONObject;

import Main.Config;
import Parser.Library;
import Tool.FileTool;

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
	
	
	
	private File lastChoosePath;

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
		
		this.lastChoosePath=new File(jsonObject.getString("lastChoosePath"));
		
	}

	public void saveConfigure() {
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("lastChoosePath", lastChoosePath.getAbsolutePath());
		
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
}
