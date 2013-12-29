package to.msn.wings.youyaku;

public class ParseIntChk {
	static boolean isInteger(String pcharalen) {
		try {
		int n = Integer.parseInt(pcharalen);
		return true;
		} catch (NumberFormatException e) {
		return false;
		}
	}
}
