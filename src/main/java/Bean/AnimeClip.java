package Bean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * change texture 
 * @author Administrator
 *
 */
public class AnimeClip {
	
	/**
	 * name of the anime
	 */
	protected String name;
	/**
	 * max frame
	 */
	protected int maxFrame;
	/**
	 * whether stop
	 */
	protected boolean stop;
	/**
	 * currentframe
	 */
	protected int currentFrame=-1;


	public class Frame {

		public int index;
		public Image texture;

		public Frame(int index, Image texture) {
			this.index = index;
			this.texture = texture;
			// TODO Auto-generated constructor stub
		}

	}

	
	/**
	 * frames
	 */
	private ObservableList<Frame> frames=FXCollections.observableArrayList();
	
	
	/**
	 * insert one frame,the first frame must set index 1!
	 * @param index
	 * @param texture
	 */
	public void insertFrame(int index,Image texture)
	{
		if(currentFrame==-1)
		{
			//if add first frame,then init current frame
			currentFrame=1;
		}
		
		if(frames.size()==0&&index!=1)
		{
			throw new RuntimeException("the first inserted frame must set index 1");
		}
		Frame frame=new Frame(index,texture);
		frames.add(frame);
		frames.sort(Comparator.comparing(a->frame.index));
	}
	
	/**
	 * calculate the current texture based on the index
	 * @param currentFrame
	 * @return
	 */
		public Image getTexture()
		{
			if(currentFrame<0)
			{
				return null;
			}
			
			//just return first texture
			if(currentFrame==1)
			{
				return frames.get(0).texture;
			}
			//just return last
			else if(currentFrame>=frames.get(frames.size()-1).index)
			{
				return frames.get(frames.size()-1).texture;
			}
			//in the center area
			else
			{
				
				for(int i=0;i<frames.size();i++)
				{
					if(currentFrame<frames.get(i).index)
					{
						if(i-1<0)
						{
							throw new RuntimeException("invalid index");
						}
						
						return frames.get(i-1).texture;
					}
				}
			}
			
			throw new RuntimeException("invalid index "+currentFrame);
		}

	
		
	public AnimeClip(String name,int maxFrame) {
		this.name = name;
		this.maxFrame = maxFrame;
	}
	
	/**
	 * check LastFrame
	 * @return 
	 * 
	 */		
	public boolean isLastFrame()
	{
		
		return getCurrentFrame()==getTotalFrames();
		
	}
	
	/**
	 * update, auto called every frame
	 */
	public void update()
	{
		if(!stop)
		{
			currentFrame++;
			if(currentFrame>getTotalFrames())
			{
				currentFrame=1;
			}
		}	
	}
	
	/**
	 * return current frame
	 * @return
	 */
	public int getCurrentFrame() {
		// TODO Auto-generated method stub
		return currentFrame;
	}


	/**
	 * stop update
	 */
	public void stop() {
		
		stop=true;
		
	}


	/**
	 * start play
	 */
	public void play() {
		stop=false;
		
	}


	/**
	 * return total frames
	 * @return
	 */
	public int getTotalFrames() {
		// TODO Auto-generated method stub
		return maxFrame;
	}

	/**
	 * goto and stop
	 * @param index
	 */
	public void gotoAndStop(int index) {
		checkIndex(1, getTotalFrames(),index);
		this.currentFrame=index;
		stop=true;
		
	}
	
	/**
	 * goto and play
	 * @param index
	 */
	public void gotoAndPlay(int index)
	{
		checkIndex(1,getTotalFrames(),index);
		gotoAndStop(index);
		play();
	}
	
	
	protected void checkIndex(int min, int max, int value) {
		if(value<min||value>max)
		{
			throw new RuntimeException();
		}
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}

	public ObservableList<Frame> getFrames() {
		return frames;
	}
	
	
	
}
