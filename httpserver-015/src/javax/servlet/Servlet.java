package javax.servlet;

import httpserve.core.RequestObject;

/**
 * 由SUN公司制定的Servlet接口规范,该接口由web服务器开发人员来调用,由webApp开发人来实现
 * @author DCG19
 *
 */
public interface Servlet {
	/**
	 * 处理业务的核心方法
	 * @param requestObject 
	 * @param responseObject
	 */
void service(ServletRequest request, ServletResponse response);
}
