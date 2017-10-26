package Tool;

import java.net.URL;

public class URLTool {

	public static String trimURL(URL url) {
		
		String string=url.getPath();
		return string.substring(string.lastIndexOf("/")+1, string.length());
		
	}

}
