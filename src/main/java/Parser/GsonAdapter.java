package Parser;

import java.lang.reflect.Type;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import Bean.AnimeClip;
import Bean.ResourceData;
import Manager.ResourceType;
import javafx.scene.image.Image;

public class GsonAdapter implements JsonSerializer<ResourceData>,JsonDeserializer<ResourceData> {


	@Override
	public ResourceData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
			
			Gson gson=getGson();
			
		
			JSONObject jsonObject=new JSONObject(json.getAsString());
			String data1=jsonObject.getString("data1");
			
			ResourceData src=gson.fromJson(data1, ResourceData.class);
			String data2=jsonObject.getString("data2");
			if(src.type==ResourceType.TEXTURE)
			{
				Image image=gson.fromJson(data2, Image.class);
				src.setData(image);
				
			}
			else if(src.type==ResourceType.MOVIECLIP)
			{
				AnimeClip animeClip=gson.fromJson(data2, AnimeClip.class);
				src.setData(animeClip);
				
			}
				
			//return 
			
			return src;
	}

	Gson getGson() {
		GsonBuilder gsonBuilder=new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Image.class, new GsonImage());
//		Type type=new TypeToken<ObservableList<Frame>>(){}.getType();
//		gsonBuilder.registerTypeAdapter(type, new GsonList());
		return gsonBuilder.create();
	}

	@Override
	public JsonElement serialize(ResourceData src, Type typeOfSrc, JsonSerializationContext context) {
		
		
		Gson gson=getGson();
		
		String data1=gson.toJson(src);
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data1", data1);
		
		
		
		if(src.type==ResourceType.TEXTURE)
		{
			Image image=(Image) src.getData();
			String string=gson.toJson(image);
		
			jsonObject.put("data2", string);
		}
		else if(src.type==ResourceType.MOVIECLIP)
		{
//			AnimeClip animeClip=(AnimeClip) src.data;
//			String string=gson.toJson(animeClip);
//			jsonObject.put("data2", string);
			
		}
		
		
		JsonElement jsonElement=new JsonPrimitive(jsonObject.toString());
		
		return jsonElement;
	}

}