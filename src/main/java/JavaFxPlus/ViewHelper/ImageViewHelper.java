package JavaFxPlus.ViewHelper;

import Bean.AnimeClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewHelper {

	private ImageView imageView;
	private Thread thread;
	private AnimeClip animeClip;

	public ImageViewHelper(ImageView imageView) {
		this.imageView = imageView;
		this.thread = new Thread() {

			public void run() {
				
				while(true)
				{
					try {
						Thread.sleep(1000/30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(animeClip!=null)
					{
	
						animeClip.update();
						Image image=animeClip.getTexture();
						resizeToImage(image);
						imageView.setImage(image);
					}
				}
			};
		};
		thread.start();
	}

	public void resizeToImage(Image image) {
		imageView.setFitWidth(image.getWidth());
		imageView.setFitHeight(image.getHeight());

	}

	
	public void play(AnimeClip animeClip) {
		this.animeClip = animeClip;

	}

	public void stop() {
		animeClip=null;
	}

}
