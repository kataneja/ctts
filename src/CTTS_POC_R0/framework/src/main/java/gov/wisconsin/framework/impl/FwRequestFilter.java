package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.bean.impl.FwActivityBean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class FwRequestFilter extends FwBaseClass implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		FwActivityBean activityBean = new FwActivityBean();
		FwRequestWrapper requestWrapper = new FwRequestWrapper((HttpServletRequest) req);
		
		activityBean.handleIncomingActivity(requestWrapper);
		chain.doFilter(requestWrapper, res);
		activityBean.handleOutgoingActivity(requestWrapper);
	}

	@Override
	public void destroy() {}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}