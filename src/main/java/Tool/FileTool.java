package Tool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Filter;

public class FileTool {

/**
 * 返回给定目录的所有子级文件绝对路径名,起始路径必须是一个目录
 * @param startFile 目录
 * @return 子级文件
 */
	public static ArrayList<File> getAllChildFiles(File startFile)
	{
		     ArrayList<File> allFile=new ArrayList<>();
     
		     //确保开始文件是一个目录后开始搜索
		     if(!(startFile.isDirectory()&&startFile.exists()))
		     {
		    	 System.out.println("输入参数不是目录或不存在....");
		    	 System.exit(0);
		     }
	
		       
				//当前待拓展的节点组(全是目录节点)
				ArrayList<File> nowStartFiles=new ArrayList<>();
				nowStartFiles.add(startFile);
				
				
					Boolean hasChildDirectory=true;
					while(hasChildDirectory==true)
					{
					 hasChildDirectory=false;
					 //新的待扩展节点组
					 ArrayList<File> nowStartFiles2=new ArrayList<>();
					 
					//搜索当前待拓展的目录节点
		            //将每个节点下的子文件中是目录的加入新的拓展节点组,是文件的加入所有文件名字队列
					for(File f:nowStartFiles)
					{
						File[] files=f.listFiles();
						for(File ff:files)
						{
						if(ff.isDirectory())
						{
							nowStartFiles2.add(ff);
							
						}
						else if(ff.isFile())
						{
							allFile.add(ff);
						}
						}
					}
					 
				     //是否有新的节点,有的话则继续循环
					if(nowStartFiles2.size()>0)
					{
						hasChildDirectory=true;
						//覆盖队列
						nowStartFiles=nowStartFiles2;
							}              		     		       
					}
					return allFile;
	}
	
	/**
	 * 返回给定目录的所有子级文件绝对路径名,起始路径必须是一个目录名
	 * @param FileName 目录名
	 * @return 所有子级文件
	 */
	public static ArrayList<File> getAllChildFiles(String FileName)
	{
		File file=new File(FileName);
		if(!file.exists())
		{
			throw new RuntimeException("文件不存在");
		}
		
		return getAllChildFiles(file);
	}
	

	
	/**
	 * 复制一个文件
	 * @param sourceName 源路径名
	 * @param targetName 目标路径名
	 * @param checkExist 如果目标文件存在,是否覆盖目标文件
	 * @throws IOException
	 */
	public static void copyFile(String sourceName,String targetName,boolean checkExist) throws IOException
	{
	  File sourceFile=new File(sourceName);
	  File targetFile=new File(targetName);
	  
	  if(targetFile.exists()&&checkExist==true)
	  {
		  System.out.println("目标文件已经存在!!");
		  return;
	  }
	  
	  if(sourceFile.exists())
	  {
		  FileInputStream fileInputStream=new FileInputStream(sourceFile);
		  FileOutputStream fileOutputStream=new FileOutputStream(targetFile);
		  
		  //将缓冲区设为文件大小的1/100 这样效率和空间占用都比较适合
		  int suitBuffNumber=(int)sourceFile.length()/100;
		  byte[] buffer=new byte[suitBuffNumber];
		  int hasRead;
		  while((hasRead=fileInputStream.read(buffer))>0)
		  {
			  fileOutputStream.write(buffer, 0, hasRead);
		  }
		  
		  fileInputStream.close();
		  fileOutputStream.close();
	  }	
	}
	
	
	/**
	 * 将字符串写到文件中
	 * @param file 文件
	 * @param line 字符串
	 */
	static public void writeFile(File file,String line)
	{
	
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream fileOutputStream=new FileOutputStream(file);
			PrintStream printStream=new PrintStream(fileOutputStream);
			
			printStream.print(line);
			
			printStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * 快速读一个文件
	 * @param selectedFile 文件
	 * @return 文件内容
	 */
	public static String readFile(File selectedFile) {
		
		char[] cbuf=new char[(int) selectedFile.length()];
		String line="";
		try {
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));
		    int hasRead=bufferedReader.read(cbuf);
		    line=new String(cbuf, 0, hasRead);
		    
		    bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return line;
	}
	
	
}
