package Parser;

import java.io.File;
import java.util.List;

import Bean.ResourceData;
import javafx.collections.ObservableList;

public class BaseOutput {
	
	protected List<ResourceData> resources;
	protected File file;

	public BaseOutput(List<ResourceData> resources, File file) {
		this.resources = resources;
		this.file = file;
			
	}
}
