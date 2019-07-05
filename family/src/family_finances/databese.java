package family_finances;
import java.sql.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.PasswordAuthentication;

import family_finances.Menu;
import family_finances.sql;
import family_finances.regist;
public class databese implements ActionListener,ItemListener{
	/*public static final String Connection = null;*/
	JFrame f1 =new JFrame();
	  //Connection conn = new Connection();
	Container ctp=f1.getContentPane();
	JLabel lb1=new JLabel("用户名",JLabel.CENTER);
	
	JLabel lb2=new JLabel("密    码",JLabel.CENTER);
	JLabel lb3=new JLabel("生财有道",JLabel.CENTER);
    JTextField tf1=new JTextField(10);
    JPasswordField tf2=new JPasswordField();
   
    JCheckBox cb=new JCheckBox();
    
    JButton bt=new JButton();
    JButton bt1=new JButton();
    JButton bt2=new JButton();
    public databese()
    {
    	cb=new JCheckBox("显示密码",false);
    	cb.addItemListener(this);
    	//f1.setSize(200,300);
    	f1.setTitle("登陆");
    	ctp.setLayout(null);
    	ctp.setBounds(0,0,355,265);
    	
    	bt.setText("登陆");
    	bt2.setText("注册");
    	bt1.setText("取消");
    	
    	bt2.setBounds(115,180,60,30);
    	bt.setBounds(30,180,60,30);
    	bt1.setBounds(200,180,60,30);
    	bt.setHorizontalTextPosition(JLabel.CENTER);
    	bt1.setHorizontalTextPosition(JLabel.CENTER);
    	lb1.setBounds(10,100,60,20);
    	lb2.setBounds(10,130,60,20);
    	lb3.setBounds(70,10,150,80);
    	lb3.setFont(new Font("微软雅黑",Font.BOLD,20));
    	tf1.setBounds(80,100,120,20);
    	tf2.setBounds(80,130,120,20);
    	ctp.add(cb);
    	cb.setBounds(200,130,120,20);
    	ctp.add(lb3);
        ctp.add(lb1);
        ctp.add(tf1);
        ctp.add(lb2);
        ctp.add(tf2);
        ctp.add(bt);
        ctp.add(bt1);
        ctp.add(bt2);
        //tf1.addActionListener(this);
        bt.addActionListener(this);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        f1.setResizable(false);//窗口不可缩放
         f1.setVisible(true);//设置窗口可见
          f1.setSize(300,300);
          tf1.setVisible(true);
          tf2.setEchoChar('*');
          f1.setLocationRelativeTo(null);//窗口居中
         /* bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
          
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	
    	// 静态代码块（将加载驱动、连接数据库放入静态块中）
    	
    	

    	String title="提示";
    	int type=JOptionPane.PLAIN_MESSAGE;
    	String str1;
    	String str2;
    	str1=tf1.getText();
    	str2=tf2.getText();
    	/*str3="SELECT users FROM users";
    	str4="SELECT password FROM users";
    	str5="SELECT * FROM users where users='"+tf1.getText()+"' and password='"+tf2.getText()+"'";
    */	
    	Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = sql.conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users where users='"+str1+"' and password='"+str2+"'");
			if(e.getSource()==bt)
	    	{
	    	try {
	    		
				if(rs.next())//如果rs中有值
				{
					Menu me=new Menu();
					f1.dispose();//释放本界面
				}
				else
				{
					type=JOptionPane.PLAIN_MESSAGE;
					String message="用户名或密码错误！";
					JOptionPane.showMessageDialog(f1, message,title,type);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	}
	    	else if	(e.getSource()==bt1)
	    	{
	    		
	    		int i=JOptionPane.showConfirmDialog(f1, "确定取消登陆吗？","提示ʾ",JOptionPane.YES_NO_OPTION);
	    		if(i==JOptionPane.YES_OPTION)
	    			System.exit(0);
	    	}
	    	else if(e.getSource()==bt2)
	    	{
	    		f1.dispose();
	    		regist es=new regist();
	    		
	    	}
			//if(!rs.next())
		} catch(SQLException e2) {
			e2.printStackTrace();
		}
		
    	
    	/*sql.stmt.executeQuery(str5);*/
    	
    }
    public static void main(String []args) {
    	
    	databese m=new databese();
    	
    }

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

}
