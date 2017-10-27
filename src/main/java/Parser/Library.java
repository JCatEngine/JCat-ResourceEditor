package Parser;

import static Parser.ResourceType.TEXTURE;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Bean.ResourceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Library {

	private static Library instance;

	public static Library getInstance()
	{
		if(instance==null)
		{
			instance=new Library();
		}
		return instance;
		
	}

	private ObservableList<ResourceData> list=FXCollections.observableArrayList();

	public void importImage(File file) {
		
		try {
			@SuppressWarnings("deprecation")
			Image image=new Image(file.toURL().toString());
			ResourceData resourceData=new ResourceData();
			resourceData.name=file.getName();
			resourceData.type=TEXTURE;
			resourceData.data=image;
			
			list.add(resourceData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ObservableList<ResourceData> getResources() {
		// TODO Auto-generated method stub
		return list;
	}

	public void removeAll(ObservableList<ResourceData> selectedItems) {
		for (ResourceData resourceData : selectedItems) {
			remove(resourceData);
		}
		
	}

	private void remove(ResourceData resourceData) {
		
		list.remove(resourceData);
		
	}

	public boolean hasSameName(String name) {
		
		return list.filtered(d->d.name.equals(name)).size()>0;
	}
}
