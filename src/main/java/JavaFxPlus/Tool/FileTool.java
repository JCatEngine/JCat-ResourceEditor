package JavaFxPlus.Tool;

import com.google.common.io.*;

import java.io.*;
import java.nio.charset.Charset;

public class FileTool {


	/**
	 * 复制一个文件
	 * @param sourceName 源路径名
	 * @param targetName 目标路径名
	 * @param checkExist 如果目标文件存在,是否覆盖目标文件
	 * @throws IOException
	 */
	public static void copyFile(String sourceName,String targetName,boolean checkExist) throws IOException
	{
		CharSource charSource=Files.asCharSource(new File(sourceName),Charset.defaultCharset());
		CharSink charSink=Files.asCharSink(new File(targetName),Charset.defaultCharset());
		charSource.copyTo(charSink);

	}
	
	
	/**
	 * 将字符串写到文件中
	 * @param file 文件
	 * @param line 字符串
	 */
	static public void writeFile(File file,String line) throws IOException {

		Files.asCharSink(file,Charset.defaultCharset()).write(line);
		
		
		
	}

	/**
	 * 快速读一个文件
	 * @param selectedFile 文件
	 * @return 文件内容
	 */
	public static String readFile(File selectedFile) throws IOException {

		return  Files.asCharSource(selectedFile, Charset.defaultCharset()).read();


	}
	
	
}
