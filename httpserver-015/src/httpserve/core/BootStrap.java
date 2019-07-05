package httpserve.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import httpserver.util.Logger;

/**
 * httpserver程序主入口
 * 
 * @author DCG
 * @version
 */
public class BootStrap {
	/**
	 * 主程序
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 程序入口
		start();
	}

	/**
	 * 主程序入口
	 */
	private static void start() {
		ServerSocket serverSocket=null;
		Socket clientSocket=null;
		BufferedReader br=null;
		try {
			Logger.log("httpserver start");
			// 获取当前时间
			long start = System.currentTimeMillis();
			//解析服务中包含的web.xml配置文件
			String[] webAppNames= {"myweb"};
			WebParser.parser(webAppNames);
			
			// 获取系统端口号
			int port = ServerParser.getPort();
			Logger.log("httpserver-poet: " + port);

			// 创建服务器套接字，并绑定端口号：8080
			serverSocket = new ServerSocket(8080);
			// 获取结束时间
			long end = System.currentTimeMillis();

			Logger.log("httpserver started: " + (end - start) + " ms");
			while(true) {
				// 开始监听网络，此时程序处于等待状态，等待接受客户端的消息
				clientSocket = serverSocket.accept();
//				// 接收客户端消息
//				br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				String temp = null;
//				while ((temp = br.readLine()) != null) {
//					System.out.println(temp);
//				}
				new Thread(new HandlerRequest(clientSocket)).start();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			/*if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(clientSocket!=null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
