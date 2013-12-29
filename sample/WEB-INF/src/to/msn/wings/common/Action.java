package to.msn.wings.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	public static final String SUCCESS="success";
	public static final String NO_MASTER="no_master";
	public static final String NO_ACTION="no_action";
	public static final String FAILURE="failure";
	
	public abstract String perform(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
