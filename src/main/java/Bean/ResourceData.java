package Bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.imageio.ImageIO;

import JavaFxPlus.Tool.ImageTool;
import Manager.ImageManager;
import Manager.ResourceType;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ResourceData  implements Externalizable{

	public String name;
	public ResourceType type;
	public Object data;
	
	public String getImageName() {
		String name="";
		if(type==ResourceType.TEXTURE)
		{
			name=ImageManager.ICON_TEXTURE;
		}
		else if(type==ResourceType.MOVIECLIP)
		{
			name=ImageManager.ICON_MOVIECLIP;
		}
		
		return name;
	}
	
	public String getTypeName() {
		String name="";
		if(type==ResourceType.TEXTURE)
		{
			name="图片";
		}
		else if(type==ResourceType.MOVIECLIP)
		{
			name="影片剪辑";
		}
		
		return name;
	}

	@Override
	public String toString() {
		return "ResourceData [" + (name != null ? "name=" + name + ", " : "")
				+ (type != null ? "type=" + type + ", " : "") + (data != null ? "data=" + data : "") + "]";
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		
		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		
		out.writeObject(type);
		out.writeObject(name);
		if(type==ResourceType.TEXTURE)
		{
			Image image=(Image) data;
			BufferedImage bufferedImage=SwingFXUtils.fromFXImage(image, null);
			ImageIO.write(bufferedImage, "PNG", stream);
			byte[] imgBytes=stream.toByteArray();
			out.writeInt(imgBytes.length);
			out.write(imgBytes);
			
		}
		else if(type==ResourceType.MOVIECLIP)
		{
			AnimeClip animeClip=(AnimeClip) data;
			out.writeObject(animeClip);
			
			
		}
		
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
		
		type= (ResourceType) in.readObject();
		name= (String) in.readObject();
		if(type==ResourceType.TEXTURE)
		{
			int length=in.readInt();
			ByteArrayOutputStream buff = new ByteArrayOutputStream();
		    byte[] readBuff = new byte[1024];
		    int hasRead;
		    while((hasRead=in.read(readBuff)) != -1) {
		      buff.write(readBuff,0,hasRead);
		    }
			BufferedImage image=ImageIO.read(new ByteArrayInputStream(buff.toByteArray()));
			this.data=SwingFXUtils.toFXImage(image, null);
			
		}
		else if(type==ResourceType.MOVIECLIP)
		{
			
			AnimeClip animeClip=(AnimeClip) in.readObject();
			this.data=animeClip;
			
		}
		
		
		
		
		
		
	}

	
	
	
}
