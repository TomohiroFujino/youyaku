package to.msn.wings.youyaku;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;
import to.msn.wings.common.CheckUtil;

public class BbsNewAction extends Action {
		
	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsArticle art=new BbsArticle();
		CheckUtil c = new CheckUtil();
		
		//20130928 update Fujino  start
		art.setTitle(request.getParameter("title"));
		//要約元文章の登録
		if(request.getParameter("flag")==null&&request.getParameter("flagB")==null){
			//値をBeansにセット//////////////////////////////////////////
			art.setPdat(new Timestamp(System.currentTimeMillis()));
			art.setParticle(request.getParameter("article"));
			art.setSource(request.getParameter("source"));
			art.setUrl(request.getParameter("url"));
			art.setPcharalen((request.getParameter("charalen")));
			////////////////////////////////////////////////////////////
			//入力チェック//////////////////////////////////////////////
			c.requiredCheck(art.getTitle(),"件名");
		    c.lengthCheck(art.getTitle(),50,"件名");
		    c.requiredCheck(art.getSource(),"記事提供元の名称");
		    c.lengthCheck(art.getSource(),50,"記事提供元の名称");
		    c.requiredCheck(art.getUrl(),"記事提供元のURL");
		    c.urlCheck(art.getUrl());
		    c.requiredCheck(art.pcharalen,"文字数");
			c.lengthCheck(art.pcharalen,11,"文字数");
			c.numberTypeCheck(art.pcharalen, "文字数");
			/////////////////////////////////////////////////////////////
		//要約
		}else if(request.getParameter("flagB")==null){
			//値をBeansにセット//////////////////////////////////////////
			art.setNam(request.getParameter("nam"));
			art.setSdat(new Timestamp(System.currentTimeMillis()));
			art.setMarticle(request.getParameter("article"));
			art.setParent(Integer.parseInt(request.getParameter("parent")));
			art.setMcharalen(request.getParameter("charalen"));
			art.setPasswd(request.getParameter("passwd"));
			//////////////////////////////////////////////////////////////
			//入力チェック//////////////////////////////////////////////
			c.requiredCheck(art.getMarticle(),"本文");
			c.requiredCheck(art.getNam(),"投稿者名");
		    c.lengthCheck(art.getNam(),10,"投稿者名");
		    //c.requiredCheck(art.getMarticle(),"本文");
			c.requiredCheck(art.getPasswd(),"削除用パスワード");
			c.lengthCheck(art.getPasswd(),15,"削除用パスワード");
			///////////////////////////////////////////////////////////
			
		}
		if(c.hasErrors()){
			request.setAttribute("sample.err.msg",c.getErrors());
			return Action.FAILURE;
		}
		//20130914 update Fujino  end

			//20130928 update Fujino start
			//art.setArticleInfo();
			//新規登録の処理
			if(request.getParameter("flag")==null&&request.getParameter("flagB")==null){
			  art.setArticleInfo();
			//}else if(request.getParameter("flag")=="res"){
			//要約の投稿処理
			}else if(request.getParameter("flagB")==null&&request.getParameter("flag").equals("res")){
			  art.setArticleInfoRes(Integer.parseInt(request.getParameter("pid")));
			//[きれいだね!]ボタンの、クリック時処理
			}else if(ParseIntChk.isInteger(request.getParameter("flagB"))){
			  BbsArticle.setArticleInfoResButton(request.getParameter("flagB"));
			}
				
			
			//20130928 update Fujino end
			return "BbsIndex";
		}
	}

