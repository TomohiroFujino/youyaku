package to.msn.wings.youyaku;

/**
 * 目的の文字列に、CSSクラスを付与する。
 * @param regex 置き換えたい文字列
 * @param reql 置換後文字列
 * @param text 置換対象文字列
 */
public class StrReplace {
	static String 	grantTag(String text,String regex,String tag,String cssclass){
		String result=text.replaceAll(
				regex,"<"+tag+" class=\""+cssclass+"\">"+regex+"</"+tag+">");
		
		//String regexUp=regex.toUpperCase();
		//result = result.replaceAll(
		//		regexUp,"<"+tag+" class=\""+cssclass+"\">"+regexUp+"</"+tag+">");
		
		//String regexLow=regex.toLowerCase();
		//result = result.replaceAll(
		//		regexLow,"<"+tag+" class=\""+cssclass+"\">"+regexLow+"</"+tag+">");
		return  result;
	}

}
