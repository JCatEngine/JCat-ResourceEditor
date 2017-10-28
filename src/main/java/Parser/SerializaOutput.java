package Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

import Bean.ResourceData;

public class SerializaOutput extends BaseOutput {

	public SerializaOutput(ArrayList<ResourceData> resourceDatas, File file) {
		super(resourceDatas, file);
		
		
		
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(resourceDatas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
