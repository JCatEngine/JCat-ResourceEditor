package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Bean.ResourceData;
import javafx.scene.image.Image;

public class GsonParser extends BaseParser{

	private ArrayList<ResourceData> list;

	public GsonParser(File file) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		super(file);
		GsonBuilder gsonBuilder=new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(ResourceData.class, new GsonAdapter());
		gsonBuilder.registerTypeAdapter(Image.class, new GsonImage());
		Gson gson=gsonBuilder.create();
		Type collectionType = new TypeToken<ArrayList<ResourceData>>(){}.getType();
		ArrayList<ResourceData> list=gson.fromJson(new FileReader(file), collectionType);
		this.list=list;
		
	}

	@Override
	public ArrayList<ResourceData> toList() {
		// TODO Auto-generated method stub
		return list;
	}

	

}
