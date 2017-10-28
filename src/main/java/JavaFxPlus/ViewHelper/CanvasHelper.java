package JavaFxPlus.ViewHelper;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * canvas's helper class
 * @author Administrator
 *
 */
public class CanvasHelper {

	private Canvas canvas;
	private GraphicsContext graphics;

	public CanvasHelper(Canvas canvas) {
		this.canvas = canvas;
		this.graphics=canvas.getGraphicsContext2D();
		
	}

	/**
	 * set line width
	 * @param width
	 */
	public void setLineWidth(int width) {
		graphics.setLineWidth(5);
		
	}

	/**
	 * set color
	 * @param green
	 */
	public void setColor(Color green) {
		
		graphics.setStroke(Color.GREEN);
		graphics.setFill(Color.GREEN);
		
	}

	/**
	 * get instace graphics
	 * @return
	 */
	public GraphicsContext getGraphicsContext2D() {
		
		return graphics;
	}

	/**
	 * fit the size of canvas to image
	 * @param image
	 */
	public void resizeToImage(Image image) {
		canvas.setHeight(image.getHeight());
		canvas.setWidth(image.getWidth());
		
	}

	/**
	 * draw grid line
	 * @param y 
	 * @param x 
	 */
	public void drawGridLine(int x, int y) {
		
		int partWidth=(int) (canvas.getWidth()/x);
		int partHeight=(int) (canvas.getHeight()/y);
		
		for(int i=0;i<x+1;i++)
		{
			graphics.strokeLine(i*partWidth, 0, i*partWidth, canvas.getHeight());
		}
		for(int i=0;i<y+1;i++)
		{
			graphics.strokeLine(0,i*partHeight,canvas.getWidth(),i*partHeight);
		}
		
	}

	public void clear() {
		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
	}

}
