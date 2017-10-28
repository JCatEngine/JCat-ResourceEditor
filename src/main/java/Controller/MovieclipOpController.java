package Controller;

import Bean.Frame;
import Bean.ResourceData;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

public class MovieclipOpController extends BaseController {

	@FXML Accordion acc;
	@FXML TitledPane bjzTP;
	@FXML ListView<Frame> frameLV;
	@FXML HBox hbox;

	@Override
	protected void initView() {
		//expand first
		acc.setExpandedPane(bjzTP);

	}

	public void update(ResourceData data) {
		
	}

	
}
