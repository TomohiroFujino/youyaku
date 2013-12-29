package to.msn.wings.youyaku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;

public class BbsDisplayB extends Action {

	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsArticle art=new BbsArticle();
		art.getArticleById(params[2],0);
		request.setAttribute("article",art);
		//要約投稿一覧ページへ遷移
		if(request.getParameter("flag")==null){
			return Action.SUCCESS;
		//要約投稿ページへ遷移
		}else{
				return "BbsRes";
		}
	}
}
