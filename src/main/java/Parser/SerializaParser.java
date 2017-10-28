package Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import Bean.ResourceData;

public class SerializaParser extends BaseParser{

	private ArrayList<ResourceData> list;

	public SerializaParser(File file) {
		super(file);
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			this.list=(ArrayList<ResourceData>) objectInputStream.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ResourceData> toList() {
		// TODO Auto-generated method stub
		return list;
	}

	

	

}
