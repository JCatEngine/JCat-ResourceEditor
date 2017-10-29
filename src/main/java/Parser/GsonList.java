package Parser;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import Bean.Frame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class GsonList implements JsonSerializer<ObservableList<Frame>>,JsonDeserializer<ObservableList<Frame>>{

	@Override
	public ObservableList<Frame> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		Gson gson=getGson();
		Type type=new TypeToken<ObservableList<Frame>>(){}.getType();
		ArrayList<Frame> arrayList=gson.fromJson(json.getAsString(),type);
		ObservableList<Frame> frames=FXCollections.observableArrayList();
		frames.addAll(arrayList);
		
		
		return frames;
	}

	private Gson getGson() {
		GsonBuilder gsonBuilder=new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Image.class, new GsonImage());
		return gsonBuilder.create();
	}

	@Override
	public JsonElement serialize(ObservableList<Frame> src, Type typeOfSrc, JsonSerializationContext context) {
		
		System.out.println(1234);
		
		Gson gson=getGson();
		ArrayList<Frame> arrayList=new ArrayList<>();
		arrayList.addAll(src);
		
		return new JsonPrimitive(gson.toJson(arrayList));
	}

	

}