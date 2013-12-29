package to.msn.wings.youyaku;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;
import to.msn.wings.common.CheckUtil;

public class BbsAdmin extends Action {

	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean flag=false; //user・passが正しいか判定するフラグ
		//ユーザ名とパスワードが織り込まれているヘッダを取得する
		String auth=request.getHeader("authorization");
		if(auth !=null && auth.substring(0,6).equals("Basic ")){
			String decoded;
			try {
				decoded = MimeUtility.decodeText("=?iso-8859-1?B?" + auth.substring(6) + "?=");
			} catch (UnsupportedEncodingException e) {
				throw new ServletException(e);
			}
			String uid   =decoded.substring(0,decoded.indexOf(":"));
			String passwd=decoded.substring(decoded.indexOf(":")+1);
			//user・passが正しいか判定
			if(uid.equals("Q9w7zi") && passwd.equals("Q9w7zi")){flag=true;}
		}
		//管理ページの表示
		if(flag){
			//request.setAttribute("authed","TRUE");
			return Action.SUCCESS;
		//user・passの再入力を促す。
		}else{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("www-authenticate", "basic realm=\"Threaded BBS\"");
			CheckUtil c = new CheckUtil();
			c.setError("アクセスが拒否されました。");
			request.setAttribute("sample.err.msg",c.getErrors());
			return Action.FAILURE;
		}
	}
}
