package Tool;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageTool {

	public static Image subImage(Image image, int xPos, int yPos, int partWidth, int partHeight) {
		
		BufferedImage bufferedImage=SwingFXUtils.fromFXImage(image, null);
		BufferedImage sub=bufferedImage.getSubimage(xPos, yPos, partWidth, partHeight);
		Image image2=SwingFXUtils.toFXImage(sub, null);
		
		
		return image2;
	}

}
