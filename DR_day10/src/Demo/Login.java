package Demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Login {
	   static  int sum =0;
	   public static Scanner  sc   = new Scanner(System.in);
	   static int i =0;
	   static  crud crud = new crud();
	   static  weapon weapon = new weapon();
	public static void main(String[] args) {
		 int sum =0;
		System.out.println("----------欢迎使用终极武器系统------------");
		System.out.println("    ------1.登录---------2.注册------");
		
		int index = sc.nextInt();
		if(index==1){
			//登录
			while (true) {
				System.out.println("请输入您的用户名");
				String name = sc.next();
				System.out.println("请输入您的密码");
				String pwd = sc.next();
				if(name.equals("admin")&&pwd.equals("admin")){
					System.out.println("您好，将军！您已获取最高权限，系统有自爆程序，请小心使用！");
					loginAdmin();
					return;
				}
				List<Map<String, Object>> list = crud.show();
				if(list.size()==0){
					System.out.println("暂无用户");
					main(null);
					return;
				}
				for (int i = 0; i < list.size(); i++) {
					if(name.equals(list.get(i).get("name").toString())){
						if(pwd.equals(list.get(i).get("password").toString())){
							System.out.println("登录成功");
							loginOk();
						}
						else{
							  System.out.println("密码错误");	
							  sum++;
							}
					}
					else{
						 System.out.println("用户名错误");	
						 sum++;
					}
				}
				if(sum==3){
					System.out.println("您已累计三次错误。。。");
					main(null);
					return;
				}
			}
			
		}
		if(index==2){
			//注册   注册成功的信息存入到数组中
		    //  二维数组
			user_name();
		}
		}
	
	
	private static void loginOk() {
		System.out.println("--1.查看武器装备------2.选择武器-----3.发射-----4.回收武器-----0.退出");
//		500 枪支 10000
//		40  高射炮  500
//		5000   投掷武器  	
		String weaponindex = sc.next();
		if(weaponindex.equals("1")){
			List<Map<String, Object>> list = weapon.showRm();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			loginOk();
		}
		
		if(weaponindex.equals("2")){
			boolean t=true;
			while(t) {
			System.out.println("选择武器类型：");
			List<Map<String, Object>> list = weapon.showRm();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i+1 + ":");
				System.out.println(list.get(i));
			}
			
			
			String type = sc.next();
			if(type.equals("1")) {
				System.out.println("请选择枪支数量：");
				int amount = sc.nextInt();
				if(amount>(int)list.get(0).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("没有那么多枪支可供选择,请重新输入！");
					amount = sc.nextInt();
					if(amount<=(int)list.get(0).get("数量")) {
						a=false;
					}
					}
				}
				
				System.out.println("请选择弹药数量：");
				int ammunition = sc.nextInt();
				if(ammunition>(int)list.get(0).get("弹药数")) {
					boolean a=true;
					while(a) {
					System.out.println("没有那么多弹药可供选择,请重新输入！");
					ammunition = sc.nextInt();
					if(ammunition<=(int)list.get(0).get("弹药数")) {
						a=false;
					}
					}
				}
				weapon.add(type,amount,ammunition);
			}
			else if(type.equals("2")) {
				System.out.println("请选择高射炮数量：");
				int amount = sc.nextInt();
				if(amount>(int)list.get(1).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("没有那么多高射炮可供选择,请重新输入！");
					amount = sc.nextInt();
					if(amount<=(int)list.get(1).get("数量")) {
						a=false;
					}
					}
				}
				System.out.println("请选择导弹数量：");
				int ammunition = sc.nextInt();
				if(ammunition>(int)list.get(1).get("导弹数")) {
					boolean a=true;
					while(a) {
					System.out.println("没有那么多导弹可供选择,请重新输入！");
					ammunition = sc.nextInt();
					if(ammunition<=(int)list.get(1).get("导弹数")) {
						a=false;
					}
					}
				}
				weapon.add(type,amount,ammunition);
			}
			else if(type.equals("3")) {
				System.out.println("请选择投掷武器数量：");
				int amount = sc.nextInt();
				if(amount>(int)list.get(2).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("没有那么多投掷武器可供选择,请重新输入！");
					amount = sc.nextInt();
					if(amount<=(int)list.get(2).get("数量")) {
						a=false;
					}
					}
				}
				int ammunition = 0;
				weapon.add(type,amount,ammunition);
			}
		    System.out.println("是否继续选择？[y/n]");
		    String yn = sc.next();
		    if(yn.equals("y")||yn.equals("Y")) {
		    	t=true;
		    }
		    else if(yn.equals("n")||yn.equals("N")) {
		    	t=false;
		    }
			}
			List<Map<String, Object>> listUser = weapon.showRm1();
			for (int i = 0; i < listUser.size(); i++) {
				System.out.println("选择的武器:");
				System.out.println(listUser.get(i));
			}
		    loginOk();
		}
		if(weaponindex.equals("3")){
			
			System.out.println("请选择使用武器：");
			List<Map<String, Object>> listUsers = weapon.showRm1();
			if(listUsers.size()==0) {
				System.out.println("没有武器，请选择武器作战！");
				loginOk();
			}
			for (int i = 0; i < listUsers.size(); i++) {
				System.out.println(i+1 + ":");
				System.out.println(listUsers.get(i));
			}
			int type1 = sc.nextInt();
			
				weapon.div(type1);
			
			loginOk();
		}
		if(weaponindex.equals("4")){
			List<Map<String, Object>> listUsers = weapon.showRm1();
			if(listUsers.size()==0) {
				System.out.println("没有武器可回收！");
				loginOk();
			}
			weapon.recycle();
			System.out.println("回收成功");
			loginOk();
		}
		if(weaponindex.equals("0")){
			
			main(null);
		}
	}


	private static void loginAdmin() {
		System.out.println("--1.查看------2.添加-----3.移除------4.更改----5自爆----返回上一层/P ");
	    String logindex = sc.next();
	    if(logindex.equals("1")){
	    	List<Map<String, Object>> list = crud.show();
	    	if(list.size()==0){
	    		System.out.println("暂无数据，请去添加");
	    		loginAdmin();
	    	}
	    	else{
	    		for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
	    		loginAdmin();
	    	}
	    	
	    }
	    if(logindex.equals("2")){
	    	System.out.println("请输入姓名");
	    	String name = sc.next();
	    	System.out.println("请输入密码");
	    	String password = sc.next();
	    	crud.add(name, password);
	    	System.out.println("添加成功");
	    	loginAdmin();
	    }
	    
	    if(logindex.equals("3")){
	    	List<Map<String, Object>> list = crud.show();
	    	for (int i = 0; i < list.size(); i++) {
	    		int a=i+1;
				System.out.println("编号:"+a+list.get(i));
			}
	    	if(list.size()==0) {
	    		System.out.println("暂无用户！");
	    		loginAdmin();
	    	}
	    	System.out.println("请输入移动信息的编号");
	    	String  id= sc.next();
	    	int deletesum =crud.delete(id);
	    	
	    	if(deletesum==0){
	    		System.out.println("删除成功");
	    		loginAdmin();
	    	}
	    	else{
	    		System.out.println("删除失败");
	    		loginAdmin();
	    	}
	    	
	    	
	    }
	    
	    if(logindex.equals("4")){
	    	int  sum=0;
	    	 
	    	List<Map<String, Object>> list = crud.show();
	    	for (int i = 0; i < list.size(); i++) {
	    		int b=i+1;
				System.out.println("编号："+b+list.get(i));
			}
	    	if(list.size()==0) {
	    		System.out.println("暂无用户！");
	    		loginAdmin();
	    	}
	    	System.out.println("请选择您要修改的数据的编号");
	    	String updateid = sc.next();
	    	for (int i = 0; i < list.size(); i++) {
				if(updateid.equals(list.get(i).get("id").toString())){
					
					System.out.println(list.get(i));
					sum++;
				}
			}
	    	if(sum==1){
	    		System.out.println("请输入您要修改的数据内容 （姓名-密码）");
		    	String str = sc.next();
		    	crud.update(updateid,str);
		    	System.out.println("修改成功");
		    	loginAdmin();
	    	}else{
	    		System.out.println("您输入的编号有误，暂无这条数据");
	    		loginAdmin();
	    	}
	    	
	    	
	    }
	    if(logindex.equals("5")) {
    	System.out.println("自爆程序已启动，系统将于5秒后自爆，请尽快撤离（你应该跑不了了）！！！");
    	List<Map<String, Object>> list = crud.show();
    	crud.em();
    	int a=5;
    	while(true) {
	    	System.out.println(a--);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	if(a==0) {
	    		System.out.println("boom，嘣。。。boom。。。！！！");
	    		main(null);
	    	}
	    }
	    }
	    if(logindex.equals("p")){
	    	main(null);
	    }
	    
	}


	private static  String user_password( String uname ) {
		System.out.println("请输入密码");
		String upwd = sc.next();
		if(upwd.length()>=4 && upwd.length()<=8 ){
		
			 crud.add(uname, upwd);
			
			System.out.println("注册成功");
			main(null);
		}
		else{
			System.out.println("请输入正确的4-8位长度密码");
			user_password(uname);
		}
		return null;
	}
	private static String user_name() {
		
		System.out.println("请输入用户名");
		String uname = sc.next();
		if(uname.length()>=4 && uname.length()<=8 ){
			user_password(uname);
		}
		else{
			System.out.println("请输入正确的4-8位用户名");
			user_name();
		}
		return null;
	}
}
