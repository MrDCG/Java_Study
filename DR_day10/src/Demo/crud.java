package Demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class crud {

	static List<Map<String, Object>> list  = new  ArrayList<Map<String,Object>>();
   
	public List<Map<String, Object>> add( String name, String password) {
		Map<String, Object> map  = new  HashMap<String, Object>();
		int  id ;
		if(list.size()==0){
			id=1;
		}
		else{
			id= new Random().nextInt(1000);
		}
		map.put("id", id);  
		map.put("name", name); 
		map.put("password", password); 
		list.add(map);
		return  list;
	}

	public int delete(String id) {
		for (int i = 0; i < list.size(); i++) {
		   Map<String, Object> map = list.get(i);	
		   if(id.equals(map.get("id").toString())){
			   list.remove(i);
			  return 0;
		   }
		}
		 return 1;
	}

	public List<Map<String, Object>> show() {
		return list;
	}

	public void update(String updateid, String str) {
	     Map<String, Object> newmap = new HashMap<String, Object>();
	     newmap.put("id", updateid);
	   //用split('-')方法将字符串以"-"分割形成一个字符串数组，然后再通过索引[0]取出所得数组中的第1个元素的值
	     newmap.put("name", str.split("-")[0]);   
	     newmap.put("password", str.split("-")[1]); 
			
		  for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if(updateid.equals(map.get("id").toString())){
				list.set(i, newmap);
			}
		}
		
	}

	public void em() {
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
			  
			}
		
	}
		
	 
	
	
}
