package Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Bean.ResourceData;

public class SerializaOutput extends BaseOutput {

	public SerializaOutput(ArrayList<ResourceData> resourceDatas, File file) {
		super(resourceDatas, file);
		
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(resourceDatas);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
