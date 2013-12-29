package to.msn.wings.youyaku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;

public class BbsDisplayA extends Action {
	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsArticle art=new BbsArticle();
		//要約投稿一覧ページへ遷移
		if(request.getParameter("flag")==null){
			art.getArticleById(params[2],0);
			request.setAttribute("article",art);
			request.setAttribute("articles",
					BbsArticle.getArticlesByIDs(params[2]));
			return Action.SUCCESS;
		//要約ページまたは、削除ページに遷移
		}else{
			if(request.getParameter("flag").equals("del")){
				art.getArticleById(params[2],1);
				request.setAttribute("article",art);
				return "BbsDelete";
			}else{
				art.getArticleById(params[2],0);
				request.setAttribute("article",art);
				return "BbsRes";
			}
		}
	}
}
