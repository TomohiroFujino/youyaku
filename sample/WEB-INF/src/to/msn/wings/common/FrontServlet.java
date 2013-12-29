package to.msn.wings.common;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class FrontServlet extends HttpServlet {
	public static final String INDEX = "index.jsp";
	public static final String ERROR = "validateErr.jsp";
	private String jsp_path=null;
	private String action=null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.jsp_path=config.getInitParameter("jsp.path");
		this.action=config.getInitParameter("action.package");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String[] params=request.getPathInfo().split("/",0);
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement(
				"SELECT mid, title, keywd, descript, skip FROM contents WHERE mid=?");
			ps.setString(1, params[1]);
			rs=ps.executeQuery();
			if(rs.next()){
				request.setAttribute("page.title", rs.getString("title"));
				request.setAttribute("page.keywd", rs.getString("keywd"));
				request.setAttribute("page.descript", rs.getString("descript"));
				request.setAttribute("page.url", rs.getString("mid") + ".jsp");
				String path = null;
				if(rs.getBoolean("skip")){	
					path = this.jsp_path + FrontServlet.INDEX;
				}else{
					Class cls = Class.forName(this.action + rs.getString("mid"));
					Class[] mp = {String[].class, HttpServletRequest.class, HttpServletResponse.class};
					Action instance = (Action)cls.newInstance();
					Method m = cls.getMethod("perform", mp);
					String result = (String)m.invoke(instance, params, request, response);
					if(result.equals(Action.SUCCESS)){
						path = this.jsp_path + FrontServlet.INDEX;
					}else if(result.equals(Action.FAILURE)){
						path = this.jsp_path + FrontServlet.ERROR;
					}else if(result.equals(Action.NO_MASTER)){
						path = this.jsp_path + request.getAttribute("page.url");
					}else if(result.equals(Action.NO_ACTION)){
						return;
					}else{
						path = request.getServletPath() + "/" + result;
					}
				}
				request.setAttribute("app.context", request.getContextPath());
				request.setAttribute("app.base", request.getContextPath() + request.getServletPath());				
				this.getServletContext().getRequestDispatcher(path).forward(request,response);
			} else {
				response.getWriter().println("mid is incorrect.");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doGet(request, response);
	}
}
