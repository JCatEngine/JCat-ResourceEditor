package Bean;

import Manager.ImageManager;
import Parser.ResourceType;

public class ResourceData {

	public String name;
	public ResourceType type;
	public Object data;
	public String getImageName() {
		String name="";
		if(type==ResourceType.TEXTURE)
		{
			name=ImageManager.ICON_TEXTURE;
		}
		
		return name;
	}
	@Override
	public String toString() {
		return "ResourceData [" + (name != null ? "name=" + name + ", " : "")
				+ (type != null ? "type=" + type + ", " : "") + (data != null ? "data=" + data : "") + "]";
	}

	
	
	
}
