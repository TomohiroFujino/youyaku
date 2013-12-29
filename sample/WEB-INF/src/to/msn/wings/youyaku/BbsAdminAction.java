package to.msn.wings.youyaku;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import to.msn.wings.common.Action;
import to.msn.wings.common.CheckUtil;

public class BbsAdminAction extends Action {

	public String perform(String[] params, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection db=null;
		PreparedStatement ps=null;
		CheckUtil c = new CheckUtil();
		c.dateTypeCheck(request.getParameter("old"),"削除範囲");
		c.rangeCheck(request.getParameter("num"),999,1,"記事番号");
		if(c.hasErrors()){
			request.setAttribute("sample.err.msg",c.getErrors());
			return Action.FAILURE;
		}else{
			try {
				Context ctx=new InitialContext();
				DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
				db=ds.getConnection();
				switch(request.getParameter("sbm").charAt(0)){
					case '古' :
						ps=db.prepareStatement("DELETE FROM bbs_master WHERE pdat<=?");
						ps.setString(1,request.getParameter("old"));
						break;
					case '特' :
						ps=db.prepareStatement("DELETE FROM bbs_parent WHERE p_id=?");
						ps.setString(1,request.getParameter("num"));
						ps.executeUpdate();
						ps=db.prepareStatement("DELETE FROM bbs_master WHERE parent=?");
						ps.setString(1,request.getParameter("num"));
						break;
				}
				ps.executeUpdate();
			} catch (NamingException e) {
				throw new ServletException(e);
			} catch (SQLException e) {
				throw new ServletException(e);
			} finally {
				try {
					if(ps!=null){ps.close();}
					if(db!=null){db.close();}
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			return "BbsIndex";
		}
	}
}
