package JavaFxPlus.Tool;

import java.awt.image.BufferedImage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageTool {

	public static Image subImage(Image image, int xPos, int yPos, int partWidth, int partHeight) {
		
		 Image img=Stream.of(image)
				.map(ImageTool::toSwing)
				.map(i->i.getSubimage(xPos, yPos, partWidth, partHeight))
				.map(ImageTool::toFX)
				.collect(Collectors.toList())
				.get(0);
		
		
		 return img;
		
		
	}

	
	static public BufferedImage toSwing(Image image)
	{
		return SwingFXUtils.fromFXImage(image, null);
	}
	
	static public Image toFX(BufferedImage image)
	{
		return SwingFXUtils.toFXImage(image, null);
	}
}
