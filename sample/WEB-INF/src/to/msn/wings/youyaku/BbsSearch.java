package to.msn.wings.youyaku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;
import to.msn.wings.common.CheckUtil;

public class BbsSearch extends Action {
	
	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckUtil c = new CheckUtil();
		c.requiredCheck(request.getParameter("keywd"),"検索文字");
		if(c.hasErrors()){
			request.setAttribute("sample.err.msg",c.getErrors());
			return Action.FAILURE;
		}
		if(request.getParameter("search").equals("parent")){
			request.setAttribute("articles",
					BbsArticle.getArticlesByKeywordP(request.getParameter("keywd")));
		}else if(request.getParameter("search").equals("master")){
			request.setAttribute("articles",
					BbsArticle.getArticlesByKeywordM(request.getParameter("keywd")));
		}else{}
		return Action.SUCCESS;
	}
}
