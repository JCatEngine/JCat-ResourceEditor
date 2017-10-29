package Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Bean.ResourceData;
import javafx.scene.image.Image;

public class GsonOutput extends BaseOutput{

	public GsonOutput(ArrayList<ResourceData> resourceDatas, File file) throws IOException {
		super(resourceDatas, file);
		
		GsonBuilder gsonBuilder=new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(ResourceData.class, new GsonAdapter());
		gsonBuilder.registerTypeAdapter(Image.class, new GsonImage());
		Gson gson=gsonBuilder.create();
		String string=gson.toJson(resourceDatas);
		
		
		BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
		bufferedWriter.write(string);
		bufferedWriter.close();
		
	}

}
