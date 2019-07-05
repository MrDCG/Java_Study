package Demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class weapon {

	  static  int   gun = 500;
	  static  int   archibald = 40;
	  static  int   grenade = 5000;
	  static  int  ammunition1 = 10000;
	  static  int  ammunition2 = 500;
	  public static Scanner  sc   = new Scanner(System.in);
	  static List<Map<String, Object>>  list  = new ArrayList<Map<String,Object>>();
	  static List<Map<String, Object>>  listUser  = new ArrayList<Map<String,Object>>();
	static{
		
		Map<String, Object> map1   = new  HashMap<String, Object>();
		map1.put("数量", gun);
		map1.put("种类", "枪支");
		map1.put("弹药数", ammunition1);
		
		Map<String, Object> map2   = new  HashMap<String, Object>();
		map2.put("数量", archibald);
		map2.put("种类", "高射炮");
		map2.put("导弹数", ammunition2);
		
		Map<String, Object> map3   = new  HashMap<String, Object>();
		map3.put("数量", grenade);
		map3.put("种类", "投掷武器");
		
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
	}
	
	
	public List<Map<String, Object>> showRm() {
		return list;
	}
public List<Map<String, Object>> showRm1() {
		
		return listUser;
	}

	public void add(String type, int amount, int ammunition) {
		
		if(type.equals("1")){
			int a =(int)list.get(0).get("数量")-amount;
			int b =(int)list.get(0).get("弹药数")-ammunition;
			Map<String, Object> map   = new  HashMap<String, Object>();
			map.put("数量", a);
			map.put("种类", "枪支");
			map.put("弹药数", b);
			list.set(0, map);
			
		}
		if(type.equals("2")){
			int a =(int)list.get(1).get("数量")-amount;
			int b =(int)list.get(1).get("导弹数")-ammunition;
			Map<String, Object> map   = new  HashMap<String, Object>();
			map.put("数量", a);
			map.put("种类", "高射炮");
			map.put("导弹数", b);
			list.set(1, map);
		}
	
		if(type.equals("3")){
			int a =(int)list.get(2).get("数量")-amount;
			Map<String, Object> map   = new  HashMap<String, Object>();
			map.put("数量", a);
			map.put("种类", "投掷武器");
			list.set(2, map);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("1")) {
		map.put("选择武器种类", "枪支");
		map.put("数量", amount);
		map.put("弹药数", ammunition);}
		else if(type.equals("2")) {
			map.put("选择武器种类", "高射炮");
			map.put("数量", amount);
			map.put("导弹数", ammunition);}
		else if(type.equals("3")) {
			map.put("选择武器种类", "投掷武器");
			map.put("数量", amount);
			}
		
		listUser.add(map);
	}
	public void div(int type1) {
		
			if("枪支".equals(listUser.get(type1-1).get("选择武器种类").toString())) {
				System.out.println("请选择使用枪支数量：");
				int n = sc.nextInt();
				if(n>(int)listUser.get(type1-1).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("枪支不够,请重新输入！");
					n = sc.nextInt();
					if(n<=(int)listUser.get(type1-1).get("数量")) {
						a=false;
					}
					}
				}
				System.out.println("请选择使用弹药数量：");
				int d = sc.nextInt();
				if(d>(int)listUser.get(type1-1).get("弹药数")) {
					boolean a=true;
					while(a) {
					System.out.println("弹药不够,请重新输入！");
					d = sc.nextInt();
					if(d<=(int)listUser.get(type1-1).get("弹药数")) {
						a=false;
					}
					}
				}
				 Map<String, Object> newmap = new HashMap<String, Object>();
			     newmap.put("选择武器种类", "枪支");
			     newmap.put("数量",(int)listUser.get(type1-1).get("数量")-n); 
			     newmap.put("弹药数",(int)listUser.get(type1-1).get("弹药数")-d); 
			     listUser.set(type1-1, newmap);
				System.out.println("发射：");
				boolean t=true;
				int j=0;
				while(t) {
					System.out.print("哒");
					n--;
					int a=(int) (Math.random()*10);
					if(a==6) {
						j++;
					}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(n==0) {
						System.out.println("  发射完毕,共击中"+j+"个敌军！！！");
						t=false;
					}
				}
				
			}
			else if("高射炮".equals(listUser.get(type1-1).get("选择武器种类").toString())) {
				System.out.println("请选择使用高射炮数量：");
				int n = sc.nextInt();
				if(n>(int)listUser.get(type1-1).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("高射炮不够,请重新输入！");
					n = sc.nextInt();
					if(n<=(int)listUser.get(type1-1).get("数量")) {
						a=false;
					}
					}
				}
				System.out.println("请选择使用导弹数量：");
				int d = sc.nextInt();
				if(d>(int)listUser.get(type1-1).get("导弹数")) {
					boolean a=true;
					while(a) {
					System.out.println("导弹不够,请重新输入！");
					d = sc.nextInt();
					if(d<=(int)listUser.get(type1-1).get("导弹数")) {
						a=false;
					}
					}
				}
				 Map<String, Object> newmap = new HashMap<String, Object>();
			     newmap.put("选择武器种类", "高射炮");
			     newmap.put("数量",(int)listUser.get(type1-1).get("数量")-n); 
			     newmap.put("导弹数",(int)listUser.get(type1-1).get("导弹数")-d); 
			     listUser.set(type1-1, newmap);
				System.out.println("发射：");
				boolean t=true;
				int j = 0;
				while(t) {
					System.out.print("轰");
					n--;
					int a=(int) (Math.random()*10);
					if(a==6) {
						j++;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(n==0) {
						System.out.println("发射完毕,共击中"+j+"个敌军！！！");
						t=false;
					}
				}
				
			}
			else if("投掷武器".equals(listUser.get(type1-1).get("选择武器种类").toString())) {
				System.out.println("请选择使用投掷武器数量：");
				int n = sc.nextInt();
				if(n>(int)listUser.get(type1-1).get("数量")) {
					boolean a=true;
					while(a) {
					System.out.println("投掷武器不够,请重新输入！");
					n = sc.nextInt();
					if(n<=(int)listUser.get(type1-1).get("数量")) {
						a=false;
					}
					}
				}
				 Map<String, Object> newmap = new HashMap<String, Object>();
			     newmap.put("选择武器种类", "投掷武器");
			     newmap.put("数量",(int)listUser.get(type1-1).get("数量")-n); 
			  
			     listUser.set(type1-1, newmap);
				System.out.println("投掷：");
				boolean t=true;
				int j = 0;
				while(t) {
					System.out.print("boom ");
					n--;
					int a=(int) (Math.random()*10);
					if(a==6) {
						j++;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(n==0) {
						System.out.println("投掷完毕,共击中"+j+"个敌军！！！");
						t=false;
					}
				}
				
			}
	}
	public void recycle() {
		
		for (int i = 0; i < listUser.size(); i++) {
			
			if("枪支".equals(listUser.get(i).get("选择武器种类").toString()))
			{
			Map<String, Object> newmap = new HashMap<String, Object>();
		     newmap.put("种类", "枪支");
		     newmap.put("数量",(int)listUser.get(i).get("数量")+(int)list.get(0).get("数量")); 
		     newmap.put("弹药数",(int)listUser.get(i).get("弹药数")+(int)list.get(0).get("弹药数")); 
		    
		     list.set(0, newmap);
			}
			else if("高射炮".equals(listUser.get(i).get("选择武器种类").toString()))
			{
				Map<String, Object> newmap = new HashMap<String, Object>();
		     newmap.put("种类", "高射炮");
		     newmap.put("数量",(int)listUser.get(i).get("数量")+(int)list.get(1).get("数量")); 
		     newmap.put("导弹数",(int)listUser.get(i).get("导弹数")+(int)list.get(1).get("导弹数")); 
		     
		  list.set(1, newmap);
			}
			else if("投掷武器".equals(listUser.get(i).get("选择武器种类").toString()))
			{
			
			Map<String, Object> newmap = new HashMap<String, Object>();
		     newmap.put("种类", "投掷武器");
		     newmap.put("数量",(int)listUser.get(i).get("数量")+(int)list.get(2).get("数量")); 
		     
		  list.set(2, newmap);
		  
			}
		}
		listUser.clear();
	}
	
	

}
