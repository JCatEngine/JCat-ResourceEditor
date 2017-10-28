package Bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Frame implements Externalizable{


	/**
	 * 
	 */
	public int index;
	public Image texture;

	public Frame(AnimeClip animeClip, int index, Image texture) {
		this.index = index;
		this.texture = texture;
		// TODO Auto-generated constructor stub
	}

	public Frame() {
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		Image image = texture;
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "PNG", stream);
		byte[] imgBytes = stream.toByteArray();
		out.writeInt(index);
		out.write(imgBytes);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		index = in.readInt();

		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		byte[] readBuff = new byte[1024];
		int hasRead;
		while ((hasRead = in.read(readBuff)) != -1) {
			buff.write(readBuff, 0, hasRead);
		}
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(buff.toByteArray()));
		this.texture = SwingFXUtils.toFXImage(image, null);

		
	}

}