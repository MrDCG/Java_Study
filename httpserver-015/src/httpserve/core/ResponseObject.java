package httpserve.core;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;
/**
 * 负责封装响应参数对象
 * @author DCG19
 *
 */
public class ResponseObject implements ServletResponse{
	private PrintWriter out;
	public void setWriter(PrintWriter out) {
		this.out=out;
	}
	public PrintWriter getWriter(){
		return out;
	} 
		
	
}
