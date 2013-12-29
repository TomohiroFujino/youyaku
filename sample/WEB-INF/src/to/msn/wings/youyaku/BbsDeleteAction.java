package to.msn.wings.youyaku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;
import to.msn.wings.common.CheckUtil;

public class BbsDeleteAction extends Action {

	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BbsArticle art=new BbsArticle();
		art.setMid(params[2]);
		art.setPasswd(request.getParameter("passwd"));
		
		if(!art.removeArticle()){
			CheckUtil c = new CheckUtil();
			c.setError("削除パスワードが間違っています。");
			request.setAttribute("sample.err.msg",c.getErrors());
			return Action.FAILURE;
		}else{
			return "BbsIndex";
		}	
	}
}
