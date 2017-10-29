package Parser;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class GsonImage implements JsonSerializer<Image>,JsonDeserializer<Image> {

	@Override
	public Image deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		Gson gson=new Gson();
		byte[] bs=gson.fromJson(json.getAsString(), byte[].class);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new ByteArrayInputStream(bs));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image image2=SwingFXUtils.toFXImage(image, null);
		
		return image2;
	}

	@Override
	public JsonElement serialize(Image image, Type typeOfSrc, JsonSerializationContext context) {
		
		
		
		Gson gson=new Gson();
		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		BufferedImage bufferedImage=SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bufferedImage, "PNG", stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] imgBytes=stream.toByteArray();
		String string=gson.toJson(imgBytes);
		
		return new JsonPrimitive(string);
	}

}
