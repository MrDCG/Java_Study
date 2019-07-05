package family_finances;
//import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
//import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
//import java.beans.DefaultPersistenceDelegate;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//import family_finances.Menu;
import family_finances.sql;
public class family_member implements ActionListener{
	JFrame f3=new JFrame();
	Container ctp=f3.getContentPane();
	JButton b=new JButton();
    JButton b1=new JButton();
    JButton b2=new JButton();
    JButton b3=new JButton();
    JButton b4=new JButton();
    
    JLabel lb1=new JLabel("称呼:",JLabel.CENTER);
	JLabel lb2=new JLabel("姓名:",JLabel.CENTER);
	JLabel lb3=new JLabel("出生日期:",JLabel.CENTER);
	JLabel lb4=new JLabel("工作:",JLabel.CENTER);
	JLabel lb5=new JLabel("待删除或修改者姓名:",JLabel.LEFT);
    
    JTextField tf1=new JTextField();
    JTextField tf2=new JTextField(); 
    JTextField tf3=new JTextField();
    JTextField tf4=new JTextField(); 
    JTextField tf5=new JTextField();
    

   
   // model.setModel(model);
    Object[][]Datename= {};
    String s[]= {"call","name","birthday","work"};//列名
    DefaultTableModel model=new DefaultTableModel(Datename,s);//建立表头
   
    JTable ta=new JTable(model);//建立表
    JScrollPane sp=new JScrollPane(ta);//滚轮
    
	public family_member() {
		
		f3.setSize(500,550);
    	f3.setTitle("成员管理");
    	ctp.setLayout(null);
    	//ctp.setBounds(0,0,355,265);
		b.setText("添加");
    	b1.setText("删除");
    	b2.setText("修改");
    	b3.setText("刷新");
    	b4.setText("退出");
    	sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ctp.add(sp);
    	ctp.add(b);
        ctp.add(b1);
        ctp.add(b2);
        ctp.add(b3);
        ctp.add(b4);
        ctp.add(lb1);
        ctp.add(lb2);
        ctp.add(lb3);
        ctp.add(lb4);
        ctp.add(lb5);
       
        /*ctp.add(lb5);
        ctp.add(lb6);*/
        ctp.add(tf1);
        ctp.add(tf2);
        ctp.add(tf3);
        ctp.add(tf4);
        ctp.add(tf5);
        
        
        
     
        
      
        tf1.setBounds(70,440,80,20);
        tf2.setBounds(160,440,80,20);
        tf3.setBounds(250,440,80,20);
        tf4.setBounds(340,440,80,20);
        tf5.setBounds(160,470,80,20);
        
        lb1.setBounds(60,420,60,20);
        lb2.setBounds(150,420,60,20);
        lb3.setBounds(250,420,60,20);
        lb4.setBounds(330,420,60,20);
        lb5.setBounds(30,470,120,20);
        /*lb5.setBounds(20,330,400,20);
        lb6.setBounds(20,350,400,20);*/
        sp.setBounds(65,30,350,300);
        
        
        b.setBounds(80,380,60,30);
    	b1.setBounds(170,380,60,30);
    	b2.setBounds(260,380,60,30);
    	b3.setBounds(350,380,60,30);
    	b4.setBounds(350,470,60,30);
    	b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
       
        Statement stmt=null;
		ResultSet rs=null;
		ta.setEnabled(false);//设置表不可编辑
		try {
			stmt = sql.conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM family order by birthday ");
			while(rs.next())        //如果rs有数据，循环输出
			{
				String arr[]=new String[4];
				arr[0]=rs.getString("call");
				arr[1]=rs.getString("name");
				arr[2]=rs.getString("birthday");
				arr[3]=rs.getString("work");
				model.addRow(arr);
					}
			stmt.close();
		} catch(SQLException e2) {
			e2.printStackTrace();
			}
		
	f3.setVisible(true);
	f3.setLocationRelativeTo(null);
	

	
	
	//添加
	b.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String title="提示";
			int type=JOptionPane.PLAIN_MESSAGE;
			String str1;
			String str2;
			String str3;
			String str4;
			
			str1=tf1.getText();
			str2=tf2.getText();
			str3=tf3.getText();
			str4=tf4.getText();
			
			Statement stmt=null;
			if((str1.equals(null)||str1.equals(""))&&(str2.equals(null)||str2.equals("")))
			{
				type=JOptionPane.PLAIN_MESSAGE;
				String message="添加失败，名称或姓名不能为空！";
				JOptionPane.showMessageDialog(f3, message,title,type);
			}
			// TODO Auto-generated method stub
			else {
			String s2="INSERT INTO family(call,name,birthday,work) VALUES('"+str1+"','"+str2+"','"+str3+"','"+str4+"')";
			
			
			try {
				stmt = sql.conn.createStatement();
				PreparedStatement ps=sql.conn.prepareStatement(s2);//将数据放入数据库中
				int rs1=ps.executeUpdate();    //执行语句
				if(rs1>0)
				{
					type=JOptionPane.PLAIN_MESSAGE;
					String message="添加成功！";
					JOptionPane.showMessageDialog(f3, message,title,type);
				}
				
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				type=JOptionPane.PLAIN_MESSAGE;
				String message=e1.getMessage();
				JOptionPane.showMessageDialog(f3, message,title,type);
			}
		 
		}}
	}); 
	//删除
	b1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String title="提示";
			int type=JOptionPane.PLAIN_MESSAGE;
			String str2;
			str2=tf5.getText();
			Statement stmt=null;
			ResultSet rs=null;
			try {
				stmt = sql.conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM family where name='"+str2+"'");
		    	try {
					if(rs.next())//如果rs中有值
					{
						int i=JOptionPane.showConfirmDialog(f3, "确定删除吗？","提示ʾ",JOptionPane.YES_NO_OPTION);
			    		if(i==JOptionPane.YES_OPTION) {
				    String s="delete from family where name='"+str2+"'";
				    stmt.executeUpdate(s);
				    type=JOptionPane.PLAIN_MESSAGE;
					String message="删除成功！";
					JOptionPane.showMessageDialog(f3, message,title,type);
				    }
			    		
					}
					else
					{
						type=JOptionPane.PLAIN_MESSAGE;
						String message="未找到删除对象！";
						JOptionPane.showMessageDialog(f3, message,title,type);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					e1.getMessage();
					type=JOptionPane.PLAIN_MESSAGE;
					JOptionPane.showMessageDialog(f3, e1.getMessage(),title,type);
				}
		    	
		    	stmt.close();
				//if(!rs.next())
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
			
		}
	});
	//修改
			b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String title="提示";
					int type=JOptionPane.PLAIN_MESSAGE;
					String str1;
					String str2;
					String str3;
					String str4;
					String str5;
					str1=tf1.getText();
					str2=tf2.getText();
					str3=tf3.getText();
					str4=tf4.getText();
					str5=tf5.getText();
					Statement stmt=null;
					ResultSet rs=null;
					try {
						stmt = sql.conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM family where name='"+str5+"'");
				    	try {
							if(rs.next())//如果rs中有值
							{
								int i=JOptionPane.showConfirmDialog(f3, "确定修改吗？","提示ʾ",JOptionPane.YES_NO_OPTION);
					    		if(i==JOptionPane.YES_OPTION) {
					    			
                            if(str1.equals(null)==false&&str1.equals("")==false) {
						    String s1="update family set call='"+str1+"'where name='"+str5+"'";
						    stmt.executeUpdate(s1);}
                            if(str2.equals(null)==false&&str2.equals("")==false) {
						    String s2="update family set name='"+str2+"'where name='"+str5+"'";
						    stmt.executeUpdate(s2);}
                            
						    String s3="update family set birthday='"+str3+"'where name='"+str5+"'";
						    stmt.executeUpdate(s3);
                           
						    String s4="update family set work='"+str4+"'where name='"+str5+"'";
						    stmt.executeUpdate(s4);
						    type=JOptionPane.PLAIN_MESSAGE;
							String message="修改成功！";
							JOptionPane.showMessageDialog(f3, message,title,type);
						    }
					    		
							}
							else
							{
								type=JOptionPane.PLAIN_MESSAGE;
								String message="未找到修改对象！";
								JOptionPane.showMessageDialog(f3, message,title,type);
							}
					} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							e1.getMessage();
							type=JOptionPane.PLAIN_MESSAGE;
							JOptionPane.showMessageDialog(f3, e1.getMessage(),title,type);
						
							stmt.close();
				    	
						//if(!rs.next())
					}} catch(SQLException e2) {
						e2.printStackTrace();
					
				}
					}		
				}
				
			);
			//刷新
			b3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					while(model.getRowCount()>0) {
					model.removeRow(model.getRowCount()-1);}//清空表格
					Statement stmt=null;
					ResultSet rs=null;
					try {
						stmt = sql.conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM family order by birthday ");
						while(rs.next())        //如果rs有数据，循环输出
						{
							String arr[]=new String[4];
							arr[0]=rs.getString("call");
							arr[1]=rs.getString("name");
							arr[2]=rs.getString("birthday");
							arr[3]=rs.getString("work");
							model.addRow(arr);
								}
						stmt.close();
					} catch(SQLException e2) {
						e2.printStackTrace();
						}
				}
			});
			//退出
	b4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Statement stmt=null;
			try {
				stmt = sql.conn.createStatement();
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			f3.dispose();
			Menu m = new Menu();
		}
	});
	
	}
	
	public static void main(String []args) {
		family_member f=new family_member();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

