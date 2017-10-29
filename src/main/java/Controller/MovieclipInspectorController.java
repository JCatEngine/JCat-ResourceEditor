package Controller;

import Bean.AnimeClip;
import Bean.ResourceData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class MovieclipInspectorController extends BaseController{

	@FXML Label typeLB;
	@FXML Label widthLB;
	@FXML Label idLB;
	@FXML Label heightLB;
	@FXML Label zzsLB;
	@FXML Label gjzsLB;

	@Override
	protected void initView() {
		
		
	}

	public void update(ResourceData data) {
		AnimeClip animeClip=(AnimeClip) data.getData();
		
		Image image=animeClip.getTexture();
		if(image!=null)
		{
			widthLB.setText(image.getWidth()+"");
			heightLB.setText(image.getHeight()+"");
			zzsLB.setText(animeClip.getMaxFrame()+"");
			gjzsLB.setText(animeClip.getFrames().size()+"");
		}
		typeLB.setText(data.getTypeName());
		idLB.setText(data.name);
	}

}
