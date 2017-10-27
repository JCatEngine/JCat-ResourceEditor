package Manager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import Main.Config;
import javafx.scene.image.Image;

/**
 * the class is for cache icon
 * @author Administrator
 *
 */
public class ImageManager {

	public static final String ICON_TEXTURE = "icon_image";
	public static final String ICON_MOVIECLIP = "icon_movieclip";
	public static final String ICON_MUSIC = "icon_music";
	
	
	
	private static ImageManager instance;

	public static ImageManager getInstance()
	{
		if(instance==null)
		{
			instance=new ImageManager();
		}
		return instance;
		
	}
	
	private Map<String, Image> map=new HashMap<>();
	
	public ImageManager()
	{
		loadImage();
		
	}
	
	
	private void loadImage(){
		_loadImage(ICON_TEXTURE);
		_loadImage(ICON_MOVIECLIP);
		_loadImage(ICON_MUSIC);
	}

	private void _loadImage(String name){
		
		try {
			//System.out.println(new File(Config.IMAGE_DIRECTORY+name+".png").toURL().toString());
			map.put(name, new Image(new File(Config.IMAGE_DIRECTORY+name+".png").toURL().toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Image getIcon(String name)
	{
		return map.get(name);	
	}
}
