package to.msn.wings.youyaku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import to.msn.wings.common.Action;

public class Top extends Action {
	public String perform(String[] params, HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.setAttribute("param",params[2]);
		request.setAttribute("msg","正常に動作しています！");
		return Action.SUCCESS;
	}
}
