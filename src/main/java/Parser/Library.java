package Parser;

import static Parser.ResourceType.TEXTURE;

import java.io.File;
import java.io.IOException;

import Bean.ResourceData;
import Tool.ImageTool;
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
			Image image=new Image(file.toURL().toString());
			addToLibrary(file.getName(),ResourceType.TEXTURE,image);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * add a image to library
	 * @param name
	 * @param type
	 * @param data
	 */
	public void addToLibrary(String name,ResourceType type, Object data) {
		ResourceData resourceData=new ResourceData();
		resourceData.name=name;
		resourceData.type=TEXTURE;
		resourceData.data=data;
		
		list.add(resourceData);
		
	}

	public ObservableList<ResourceData> getResources() {
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * remove all selected items
	 * @param selectedItems
	 */
	public void removeAll(ObservableList<ResourceData> selectedItems) {
		while(selectedItems.size()>0)
		{
			remove(selectedItems.get(0));
		}
		
	}

	private void remove(ResourceData resourceData) {
		
		list.remove(resourceData);
		
	}

	/**
	 * check whether a image with name has exist
	 * @param name
	 * @return
	 */
	public boolean hasSameName(String name) {
		
		return list.filtered(d->d.name.equals(name)).size()>0;
	}

	/**
	 * slice a image and add to library
	 * @param image
	 * @param xAmount
	 * @param yAmount
	 * @param name
	 */
	public void sliceAndAdd(Image image, int xAmount, int yAmount, String name) {
	
		
		int partWidth=(int) (image.getWidth()/xAmount);
		int partHeight=(int) (image.getHeight()/yAmount);
		
		for(int x=0;x<xAmount;x++)
		{
			for(int y=0;y<yAmount;y++)
			{
				int xPos=x*partWidth;
				int yPos=y*partHeight;
				String autoNewName=name+x+y;
				
				sliceAndAdd(image,xPos,yPos,partWidth,partHeight,autoNewName);
			}
		}
		
		
		
	}

	/**
	 * slice a image and add to library
	 * @param image
	 * @param xPos
	 * @param yPos
	 * @param partWidth
	 * @param partHeight
	 * @param autoNewName
	 */
	public void sliceAndAdd(Image image, int xPos, int yPos, int partWidth, int partHeight, String autoNewName) {
		
		Image newImage=ImageTool.subImage(image,xPos,yPos,partWidth,partHeight);
		//if this do not exist,then add it
		if(hasSameName(autoNewName)==false)
		{
			addToLibrary(autoNewName, ResourceType.TEXTURE, newImage);
		}
		
		
	}
}
