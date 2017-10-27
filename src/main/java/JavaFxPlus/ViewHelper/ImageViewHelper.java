package JavaFxPlus.ViewHelper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewHelper {

	private ImageView imageView;

	public ImageViewHelper(ImageView imageView) {
		this.imageView = imageView;
	}

	public void resizeToImage(Image image) {
		imageView.setFitWidth(image.getWidth());
		imageView.setFitHeight(image.getHeight());
		
	}

	

}
