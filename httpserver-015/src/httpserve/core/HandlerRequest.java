package httpserve.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.servlet.Servlet;

import httpserver.util.Logger;
import myweb.servlet.LoginServlet;

/**
 * 处理客户端请求
 * 
 * @author DCG19
 *
 */
public class HandlerRequest implements Runnable {
	public Socket clientSocket;

	public HandlerRequest(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		// 处理客户端请求
		BufferedReader br = null;
		PrintWriter out = null;
		Logger.log("httpserver thread: " + Thread.currentThread().getName());
		// 接收客户端消息
		try {
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// 获取响应流对象
			out = new PrintWriter(clientSocket.getOutputStream());

			/*
			 * //打印客户端消息 String temp = null; while ((temp = br.readLine()) != null) {
			 * System.out.println(temp); }
			 */
			// 获取请求协议的请求行
			String requestLine = br.readLine(); // GET /myweb/li.html HTTP/1.1
			// 获取URI->请求行（requestLine） ->请求方式 URL 请求协议版本号->三者之间通过一个空格隔开
			String requestURI = requestLine.split(" ")[1];// {"GET","/myweb/li.html","HTTP/1.1"}
			 System.out.println(requestURI);
			// 判断用户请求是否为静态页面
			if (requestURI.endsWith(".html") || requestURI.endsWith(".htm")) {
				// 处理静态页面的方法
				responseStaticPage(requestURI, out);
			} else {// 动态资源：Java程序，业务处理类
					// requestURI:/myweb/login?username=zhangsan&password=111
					// requestURI:/myweb/login
				String servletPath = requestURI;
				// 判断servletPath是否包含参数
				if (servletPath.contains("?")) {
					servletPath = servletPath.split("[?]")[0];// /myweb/login
				}
//				if("/myweb/login".equals(servletPath)) {
//					LoginServlet loginServlet=new LoginServlet();
//					loginServlet.service();
//				}

				// 获取应用的名称：myweb在uri里： /myweb/login
				String webAppName = servletPath.split("[/]")[1];
				// 获取servletMaps集合中的value值->servletMap->key:urlPattern value:servletClassName
				Map<String, String> servletMap = WebParser.servletMaps.get(webAppName);
				// 获取servletMap集合中的key值->存在与uri中/myweb/login
				String urlPattern = servletPath.substring(1 + webAppName.length());
				// 获取servletClassName
				String servletClassName = servletMap.get(urlPattern);
				//判断该业务处理的Servlet类是否存在
				if(servletClassName!=null) {
					
					//获取封装响应参数对象
					ResponseObject responseObject=new ResponseObject();
					responseObject.setWriter(out);
					
					//获取封装请求参数对象
					RequestObject requestObject = new RequestObject(requestURI);
					out.print("HTTP/1.1 200 OK\n");
					out.print("Content-Type:text/html;charset=utf-8\n\n");
					
					//创建Servlet对象之前，先从缓存池查找
					//1、有：拿来直接用
					//2、没有：创建Servlet对象，放进缓存池
					Servlet servlet = ServletCache.get(urlPattern);
					if(servlet==null) {
						// 通过反射机制创建该业务处理类
						Class c = Class.forName(servletClassName);
						Object obj = c.newInstance();
						servlet = (Servlet) obj;
						//将创建好的Servlet对象放到缓存池中
						ServletCache.put(urlPattern, servlet);
					}
					Logger.log("Servlet 对象：" + servlet);
					servlet.service(requestObject,responseObject);
				}else {//找不到该业务处理类：404
					// 404找不到资源
					StringBuilder html = new StringBuilder();
					html.append("HTTP/1.1 404 NotFound\n");
					html.append("Content-Type:text/html;charset=utf-8\n\n");
					html.append("<html>");
					html.append("<head>");
					html.append("<title>404-错误</title>");
					html.append("<meta content='text/html;charset=utf-8'/>");
					html.append("</head>");
					html.append("<body>");
					html.append("<center><font size='35xp' color='red'>404-Not Found</font></center>");
					html.append("</body>");
					html.append("</html>");
					out.print(html);
					
				}
				
			}
			// 强制刷新
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 处理静态页面
	 * 
	 * @param requestURI 请求URI
	 * @param out        响应流对象
	 */
	private void responseStaticPage(String requestURI, PrintWriter out) {
		// requestURI:/myweb/练习.html
		// 静态页面路径：myweb/练习.html
		String htmlPath = requestURI.substring(1);
		BufferedReader br = null;
		try {
			// 读取页面
			br = new BufferedReader(new FileReader(htmlPath));
			StringBuilder html = new StringBuilder();
			// 拼接响应信息
			html.append("HTTP/1.1 200 OK\n");
			html.append("Content-Type:text/html;charset=utf-8\n\n");
			String temp = null;
			while ((temp = br.readLine()) != null) {
				html.append(temp);
			}
			// 输出html
			out.print(html);
		} catch (FileNotFoundException e) {
			// 404找不到资源
			StringBuilder html = new StringBuilder();
			html.append("HTTP/1.1 404 NotFound\n");
			html.append("Content-Type:text/html;charset=utf-8\n\n");
			html.append("<html>");
			html.append("<head>");
			html.append("<title>404-错误</title>");
			html.append("<meta content='text/html;charset=utf-8'/>");
			html.append("</head>");
			html.append("<body>");
			html.append("<center><font size='35xp' color='red'>404-Not Found</font></center>");
			html.append("</body>");
			html.append("</html>");
			out.print(html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
