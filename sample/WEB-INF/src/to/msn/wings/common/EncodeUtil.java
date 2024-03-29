package to.msn.wings.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeUtil implements Filter {
	private FilterConfig config=null;
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		request.setCharacterEncoding(
			config.getInitParameter("EncodeName"));
		chain.doFilter(request,response);
	}
	public void destroy() {}
}