package family_finances;
import javax.swing.*;

import family_finances.Menu;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class regist implements ActionListener{
	JFrame f9=new JFrame();
	Container ctp=f9.getContentPane();
	JLabel lb1=new JLabel("用户名",JLabel.CENTER);
	JLabel lb2=new JLabel("密    码",JLabel.CENTER);
	JLabel lb3=new JLabel("注册",JLabel.CENTER);
	JTextField tf1=new JTextField(10);
    JTextField tf2=new JTextField(10); 
    JButton bt=new JButton();
    JButton bt1=new JButton();
    
	public regist() {
	/*f9.setVisible(true);
	f9.setSize(200,300);*/
	f9.setTitle("注册");
	ctp.setLayout(null);
	ctp.setBounds(0,0,355,265);
	
	bt.setText("注册");
	bt1.setText("返回");
	bt.setBounds(30,180,60,30);
	bt1.setBounds(200,180,60,30);
	bt.setHorizontalTextPosition(JLabel.CENTER);
	bt1.setHorizontalTextPosition(JLabel.CENTER);
	
	lb3.setFont(new Font("微软雅黑",Font.BOLD,20));
	lb1.setBounds(30,100,60,20);
	lb2.setBounds(30,130,60,20);
	lb3.setBounds(70,10,150,80);
	
	tf1.setBounds(100,100,150,20);
	tf2.setBounds(100,130,150,20);
	
	ctp.add(lb3);
    ctp.add(lb1);
    ctp.add(tf1);
    ctp.add(lb2);
    ctp.add(tf2);
    ctp.add(bt);
    ctp.add(bt1);
    
    bt.addActionListener(this);
    bt1.addActionListener(this);
    
     f9.setResizable(false);//窗口不可缩放
     f9.setVisible(true);//设置窗口可见
     f9.setSize(300,300);
     f9.setLocationRelativeTo(null);//窗口居中
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String title="提示";
    	int type=JOptionPane.PLAIN_MESSAGE;
    	String str1;
    	String str2;
    	str1=tf1.getText();
    	str2=tf2.getText();
    	
    	Statement stmt=null;
		ResultSet rs=null;
		
		
		try {
			stmt = sql.conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users where users='"+str1+"'");
			if(e.getSource()==bt)
	    	{
	    	try {
	    		
				if(!rs.next())//如果rs中没有值
				{
	        	String s2="INSERT INTO users(users,password) VALUES('"+str1+"','"+str2+"')";
	        	try {
					rs=null;
					PreparedStatement ps=sql.conn.prepareStatement(s2);
					int rs1=ps.executeUpdate();    //将数据放入数据库中
					
						type=JOptionPane.PLAIN_MESSAGE;
						String message="注册成功,欢迎使用！";
						JOptionPane.showMessageDialog(f9, message,title,type);
             
						f9.dispose();
						Menu m = new Menu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					type=JOptionPane.PLAIN_MESSAGE;
					String message="注册失败！";
					JOptionPane.showMessageDialog(f9, message,title,type);
				}
			 catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
				}
				else
				{
					type=JOptionPane.PLAIN_MESSAGE;
					String message="注册失败，用户名已存在！";
					JOptionPane.showMessageDialog(f9, message,title,type);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	}
	    	else if	(e.getSource()==bt1)
	    	{
	    		
	    		int i=JOptionPane.showConfirmDialog(f9, "确定返回登陆吗？","提示ʾ",JOptionPane.YES_NO_OPTION);
	    		if(i==JOptionPane.YES_OPTION)
	    		{
	    			f9.dispose();
	    			databese d=new databese();
	    			//System.exit(0);
	    			
	    		}
	    	}
			stmt.close();
			//if(!rs.next())
		} catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	public static void main(String []args) {
		regist es=new regist();
	}
}