package myweb.servlet;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import httpserve.core.RequestObject;
import httpserve.core.ResponseObject;

/**
 * 处理登陆业务的JAVA程序，该Java程序由webApp开发人员开发，有web服务器开发人员负责调用
 * 
 * @author webApp开发人员
 *
 */


public class LoginServlet implements Servlet{
//处理业务的核心类
	public void service(ServletRequest request,ServletResponse response) {
		System.out.println("正在验证身份，请稍等。。。");
		//获取响应流对象
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>正在验证</title>");
		out.print("<meta content='text/html;charset=utf-8'/>");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><font size='35xp' color='blue'>正在验证身份，请稍等。。。</font></center>");
		out.print("</body>");
		out.print("</html>");
	
	}
}
