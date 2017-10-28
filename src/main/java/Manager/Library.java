package Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Bean.AnimeClip;
import Bean.ResourceData;
import JavaFxPlus.Tool.ImageTool;
import JavaFxPlus.Util.FilterAbleList;
import Parser.SerializaOutput;
import Parser.SerializaParser;
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

	private FilterAbleList<ResourceData> list; 
	
	private int id=0;
	
	public Library() {
		ObservableList<ResourceData> list=FXCollections.observableArrayList();
		this.list=new FilterAbleList<>(list);
	}

	

	public void importImage(File file) {
		
		try {
			Image image=new Image(file.toURL().toString());
			String name=file.getName();
			name=name.substring(0, name.lastIndexOf("."));
			addToLibrary(name,ResourceType.TEXTURE,image);
			
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
		resourceData.type=type;
		resourceData.data=data;
		
		list.add(resourceData);
		
	}

	public ObservableList<ResourceData> getResources() {
		return list.getList();
	}

	/**
	 * remove all selected items
	 * @param selectedItems
	 */
	public void removeAll(ObservableList<ResourceData> selectedItems) {
		
		for (ResourceData resourceData : selectedItems) {
			remove(resourceData);
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
		
		return list.filter(d->d.name.equals(name)).size()>0;
	}

	/**
	 * slice a image and add to library
	 * @param image
	 * @param xAmount
	 * @param yAmount
	 * @param name
	 * @param jgzs 
	 * @param anime 
	 * @param zscdh  是否只生成动画不生成切片
	 */
	public void sliceAndAdd(Image image, int xAmount, int yAmount, String name, Boolean anime, int jgzs, Boolean zscdh) {
	
		
		int partWidth=(int) (image.getWidth()/xAmount);
		int partHeight=(int) (image.getHeight()/yAmount);
		
		
		
		for(int y=0;y<yAmount;y++)
		{
			//if need to generate a new anime
			AnimeClip animeClip = null;
			if(anime)
			{
				int maxFrame=xAmount*jgzs;
				animeClip=new AnimeClip(name+"anime"+y, maxFrame);
			}
			
			for(int x=0;x<xAmount;x++)
			{
				int xPos=x*partWidth;
				int yPos=y*partHeight;
				String autoNewName=name+x+y;
				
				
				Image sub=slice(image,xPos,yPos,partWidth,partHeight,autoNewName,zscdh);
				
				
				if(anime)
				{
					animeClip.insertFrame(x*jgzs+1, sub);
				}
				
			}
			
			if(anime)
			{
				addToLibrary(animeClip.getName(), ResourceType.MOVIECLIP, animeClip);
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
	 * @param zscdh 
	 * @return 
	 */
	public Image slice(Image image, int xPos, int yPos, int partWidth, int partHeight, String autoNewName, boolean zscdh) {
		
		Image newImage=ImageTool.subImage(image,xPos,yPos,partWidth,partHeight);
		//if this do not exist,then add it
		if(hasSameName(autoNewName)==false&&!zscdh)
		{
			addToLibrary(autoNewName, ResourceType.TEXTURE, newImage);
		}
		
		
		return newImage;
		
	}



	public void fliterResource(String text) {
		list.filterList(d->d.name.startsWith(text));
	}



	public void createEmptyAnime() {
		
		String name="anime"+id++;
		addToLibrary(name,ResourceType.MOVIECLIP,new AnimeClip(name, 1));
			
		
	}



	public void outputToFile(File file) {
		
		//arraylist 才可以序列化 
		ArrayList<ResourceData> resourceDatas=new ArrayList<>(getResources());
		new SerializaOutput(resourceDatas,file);
		
		
		
	}



	public void inputFromFile(File file) {
		ArrayList<ResourceData> resourceDatas=new SerializaParser(file).toList();
		this.list.addAll(resourceDatas);
		
	}
}
