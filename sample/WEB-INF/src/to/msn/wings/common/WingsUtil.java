package to.msn.wings.common;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class WingsUtil {
	public static String htmlEncode(String value){
		StringBuffer result=new StringBuffer();
		for(int i=0;i<value.length();i++){
			switch(value.charAt(i)){
				case '&' :
					result.append("&amp;");
					break;
				case '<' :
					result.append("&lt;");
					break;
				case '>' :
					result.append("&gt;");
					break;
				case '\t' :
					result.append("&nbsp;&nbsp;&nbsp;&nbsp;");
					break;
				default :
					result.append(value.charAt(i));
					break;
			}
		}
		return result.toString();
	}
	
	public static String encodeJis(String value){
		String result=null;
		try {
			result=new String(value.getBytes("ISO-2022-JP"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(System.err);
		}
		return result;
	}

	public static String sqlEscape(String value){
		StringBuffer result=new StringBuffer();
		for(int i=0;i<value.length();i++){
			switch(value.charAt(i)){
				case '\'' :
					result.append("\\\'");
					break;
				case '"' :
					result.append("\\\"");
					break;
				case '\\' :
					result.append("\\\\");
					break;
				default :
					result.append(value.charAt(i));
					break;
			}
		}
		return result.toString();
	}
	
	public static File[] fileList(ServletContext app, String path){
		File fl=new File(app.getRealPath(path));
		return fl.listFiles();
	}
	
	public static File file(ServletContext app, String path){
		File fl=new File(app.getRealPath(path));
		return fl;
	}
	
	public static long lastModified(File file){
		return file.lastModified();
	}
	
	public static String systemProperty(String key){
		return System.getProperty(key,"");
	}
	
	public static boolean isUserInRole(PageContext ctx, String role){
		HttpServletRequest req = (HttpServletRequest)ctx.getRequest();
		return req.isUserInRole(role);
	}
}
