package httpserve.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestObjectTest {

	@Test
	public void testGetParameterValue() {
		RequestObject request = new RequestObject("/myweb/user/save?");
		System.out.println(request.getParameterValue("username"));
		RequestObject request1 = new RequestObject("/myweb/user/save?username=");
		System.out.println(request1.getParameterValue("username"));
		RequestObject request2 = new RequestObject("/myweb/user/save?username=zhangsna");
		System.out.println(request2.getParameterValue("username"));
		RequestObject request3 = new RequestObject("/myweb/user/save?username=zhangsna&gender=1");
		System.out.println(request3.getParameterValue("gender"));
		
	}

	@Test
	public void testGetParameterValues() {
		RequestObject request4 = new RequestObject("/myweb/user/save?username=zhangsna&gender=1&interest=food&interest=music");
		String[] interests = request4.getParameterValues("interest");
		for(String interest:interests) {
			System.out.println(interest);
		}
		
	}

}
