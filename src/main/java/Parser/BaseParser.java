package Parser;

import java.io.File;
import java.util.ArrayList;

import Bean.ResourceData;

public abstract class BaseParser {
	protected File file;

	public BaseParser(File file) {
		this.file = file;
		
	}
	
	abstract public ArrayList<ResourceData> toList();
	
}
