package family_finances;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class users implements ActionListener,ItemListener{
	JFrame f0=new JFrame();
	Container ctp=f0.getContentPane();
	JButton b1=new JButton();
    JButton b2=new JButton();
    JLabel lb=new JLabel("修该密码:",JLabel.CENTER);
    JLabel lb1=new JLabel("用户名:",JLabel.CENTER);
   	JLabel lb2=new JLabel("旧密码:",JLabel.CENTER);
   	JLabel lb3=new JLabel("新密码:",JLabel.CENTER);
   	
   	JTextField tf1=new JTextField();
   /* JTextField tf2=new JTextField(); 
    JTextField tf3=new JTextField();*/
    
    JPasswordField tf2=new JPasswordField();
    JPasswordField tf3=new JPasswordField();
    JCheckBox cb=new JCheckBox();
    JCheckBox cb1=new JCheckBox();
	public users() {
		ctp.setLayout(null);
		cb=new JCheckBox("显示密码",false);
    	cb.addItemListener(this);
    	cb1=new JCheckBox("显示密码",false);
    	cb1.addItemListener(this);
    	ctp.add(cb);
    	ctp.add(cb1);
    	
    	ctp.add(tf2);
    	ctp.add(tf2);
    	f0.setTitle("修改密码");
    	b1.setText("修改");
    	b2.setText("取消");
    	ctp.add(b1);
        ctp.add(b2);
        ctp.add(lb);
        ctp.add(lb1);
        ctp.add(lb2);
        ctp.add(lb3);
        ctp.add(tf1);
        ctp.add(tf2);
        ctp.add(tf3);
        lb.setFont(new Font("微软雅黑",Font.BOLD,20));
        lb.setBounds(70,10,150,80);
        lb1.setBounds(10,100,60,20);
    	lb2.setBounds(10,130,60,20);
    	lb3.setBounds(10,160,60,20);
    	
    	tf1.setBounds(80,100,120,20);
    	tf2.setBounds(80,130,120,20);
    	tf3.setBounds(80,160,120,20);
    	cb.setBounds(200,130,120,20);
    	cb1.setBounds(200,160,120,20);
    	
    	b1.setBounds(50,200,60,30);
    	b2.setBounds(180,200,60,30);
    	b1.setHorizontalTextPosition(JLabel.CENTER);
    	b2.setHorizontalTextPosition(JLabel.CENTER);
    	  b1.addActionListener(this);
          b2.addActionListener(this);
    	f0.setResizable(false);//窗口不可缩放
        f0.setVisible(true);//设置窗口可见
        f0.setSize(300,300);
        f0.setLocationRelativeTo(null);//窗口居中
        tf2.setEchoChar('*');
        tf3.setEchoChar('*');
        
        cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				JCheckBox cbx=(JCheckBox)e.getItem();
				if("显示密码".equals(cbx.getText()))
				{
					if(e.getStateChange()==ItemEvent.SELECTED)
						tf2.setEchoChar((char)0);
					else
					{
						tf2.setEchoChar('*');
					}
				}
			}
		});
        
cb1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				JCheckBox cbx=(JCheckBox)e.getItem();
				if("显示密码".equals(cbx.getText()))
				{
					if(e.getStateChange()==ItemEvent.SELECTED)
						tf3.setEchoChar((char)0);
					else
					{
						tf3.setEchoChar('*');
					}
				}
			}
		});
        
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String title="提示";
		    	int type=JOptionPane.PLAIN_MESSAGE;
		    	String str1;
		    	String str2;
		    	String str3;
		    	str1=tf1.getText();
		    	str2=tf2.getText();
		    	str3=tf3.getText();
		    	Statement stmt=null;
				ResultSet rs=null;
				
				try {
					stmt = sql.conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM users where users='"+str1+"'and password='"+str2+"'");
				
			    	try {
			    		
						if(rs.next())//如果rs中有值
						{
							if(str3.equals(null)==false||str3.equals("")==false) {
			        	String s2="update users set password='"+str3+"'where users='"+str1+"'";
			        	stmt.executeUpdate(s2);}
							type=JOptionPane.PLAIN_MESSAGE;
							String message="密码修改成功，请重新登陆！";
							JOptionPane.showMessageDialog(f0, message,title,type);
							f0.dispose();
							
			    			databese d=new databese();
			    			stmt.close();
						}
						else
						{
							type=JOptionPane.PLAIN_MESSAGE;
							String message="用户名或密码错误！";
							JOptionPane.showMessageDialog(f0, message,title,type);
							stmt.close();
						}
			    	}
					 catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						e1.getMessage();
						type=JOptionPane.PLAIN_MESSAGE;
						JOptionPane.showMessageDialog(f0, e1.getMessage(),title,type);
					}	
						
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		});
        
        b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f0.dispose();
				Menu menu=new Menu();
			}
		});
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String []args) {
		users u=new users();
	}


	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}	