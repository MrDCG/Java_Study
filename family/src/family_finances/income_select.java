package family_finances;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import family_finances.Menu;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class income_select implements ActionListener,ItemListener{
	JFrame f6=new JFrame();
	Container ctp=f6.getContentPane();
	JButton b1=new JButton();
    JButton b2=new JButton();
    JButton b3=new JButton();
    
    JLabel lb1=new JLabel("收入编号:",JLabel.CENTER);
	JLabel lb2=new JLabel("姓名:",JLabel.CENTER);
	JLabel lb3=new JLabel("收入类型:",JLabel.CENTER);
	JLabel lb4=new JLabel("收入时间:",JLabel.CENTER);
	JLabel lb5=new JLabel("收入金额:",JLabel.CENTER);
	JLabel lb6=new JLabel("查询方式:",JLabel.CENTER);
	JLabel lb7=new JLabel("请先选择查询方式，再按查询键！",JLabel.LEFT);
	
	JTextField tf1=new JTextField();
    JTextField tf2=new JTextField(); 
    JTextField tf3=new JTextField();
    JTextField tf4=new JTextField(); 
    JTextField tf5=new JTextField();
    
    String name[]= {"","编号查询","姓名查询","收入类型查询","收入时间查询","收入金额查询"};
    JComboBox cbx=new JComboBox();
    
    Object[][]Datename= {};
    String s[]= {"ino","name","category","income_day","income_money","remark"};//列名
    DefaultTableModel model=new DefaultTableModel(Datename,s);//建立表头
   
    JTable ta=new JTable(model);//建立表
    JScrollPane sp=new JScrollPane(ta);//滚轮
	int c;


	
	
	public income_select() {
	
		cbx.setEditable(false);
		for(int j=0;j<name.length;j++)
		    cbx.addItem(name[j]);
		ctp.add(cbx);
		cbx.setBounds(280,470,80,20);
		cbx.addItemListener(this);
		
		f6.setSize(600,550);
    	f6.setTitle("收入查询");
    	ctp.setLayout(null);
    	b1.setText("查        询");
    	b2.setText("模糊查询");
    	b3.setText("返        回");
    	sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    ctp.add(sp);
	    ctp.add(b1);
        ctp.add(b2);
        ctp.add(lb1);
        ctp.add(lb2);
        ctp.add(lb3);
        ctp.add(lb4);
        ctp.add(lb5);
        ctp.add(lb6);
        ctp.add(lb7);
        lb7.setBounds(30,350,220,20);
        
        ctp.add(b1);
        ctp.add(b2);
        ctp.add(b3);
        
        ctp.add(tf1);
        ctp.add(tf2);
        ctp.add(tf3);
        ctp.add(tf4);
        ctp.add(tf5);
        
        sp.setBounds(30,30,540,300);
        lb1.setBounds(50,390,60,20);
        lb2.setBounds(210,390,60,20);
        lb3.setBounds(50,430,60,20);
        lb4.setBounds(210,430,60,20);
        lb5.setBounds(50,470,60,20);
        lb6.setBounds(210,470,60,20);
        
        tf1.setBounds(120,390,80,20);
        tf2.setBounds(280,390,80,20);
        tf3.setBounds(120,430,80,20);
        tf4.setBounds(280,430,80,20);
        tf5.setBounds(120,470,80,20);
        
        b1.setBounds(380,380,100,30);
    	b2.setBounds(380,420,100,30);
    	b3.setBounds(380,460,100,30);
    	
        f6.setVisible(true);
        f6.setLocationRelativeTo(null);
		ta.setEnabled(false);//设置表不可编辑

		
b3.addActionListener(new ActionListener() {
			
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
				
				f6.dispose();
				Menu m = new Menu();
			}
		});
		cbx.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String str=(String)e.getItem();
				for(int i=0;i<name.length;i++) {
				if(str.equals(name[i]))
				{
					c=cbx.getSelectedIndex();}
				}
				

					b1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							int n=0;
							String title="提示";
							int type=JOptionPane.PLAIN_MESSAGE;
							String str1;
							str1=tf1.getText();
							String str2;
							str2=tf2.getText();
							String str3;
							str3=tf3.getText();
							String str4;
							str4=tf4.getText();
							String str5;
							str5=tf5.getText();
							Statement stmt=null;
							

							
							try {
								stmt = sql.conn.createStatement();
								
								if(c==1) {
									
									
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								
								
								if(str1.equals(null)||str1.equals(""))
								{
									ResultSet rs1=null;
									rs1 = stmt.executeQuery("SELECT * FROM income");
									while(rs1.next())        //如果rs1有数据，循环输出
									{
										String arr[]=new String[6];
										arr[0]=rs1.getString("ino");
										arr[1]=rs1.getString("name");
										arr[2]=rs1.getString("category");
										arr[3]=rs1.getString("income_day");
										arr[4]=rs1.getString("income_money");
										arr[5]=rs1.getString("remark");
										model.addRow(arr);
											}
									
								}
								else {
									ResultSet rs=null;
									rs = stmt.executeQuery("SELECT * FROM income where ino='"+str1+"'");
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
								}
								}
								if(c==2)
								{
									while(model.getRowCount()>0) {
										model.removeRow(model.getRowCount()-1);}//清空表格
									
									
									if(str2.equals(null)||str2.equals(""))
									{
										ResultSet rs1=null;
										rs1 = stmt.executeQuery("SELECT * FROM income");
										while(rs1.next())        //如果rs1有数据，循环输出
										{
											String arr[]=new String[6];
											arr[0]=rs1.getString("ino");
											arr[1]=rs1.getString("name");
											arr[2]=rs1.getString("category");
											arr[3]=rs1.getString("income_day");
											arr[4]=rs1.getString("income_money");
											arr[5]=rs1.getString("remark");
											model.addRow(arr);
												}
										
									}
									
									else {
										ResultSet rs=null;
										rs = stmt.executeQuery("SELECT * FROM income where name='"+str2+"'");
									while(rs.next())        //如果rs有数据，循环输出
									{
										String arr[]=new String[6];
										arr[0]=rs.getString("ino");
										arr[1]=rs.getString("name");
										arr[2]=rs.getString("category");
										arr[3]=rs.getString("income_day");
										arr[4]=rs.getString("income_money");
										arr[5]=rs.getString("remark");
										model.addRow(arr);
											}
									
									}
									
								}
								if(c==3)
								{
									while(model.getRowCount()>0) {
										model.removeRow(model.getRowCount()-1);}//清空表格
									
									
									if(str3.equals(null)||str3.equals(""))
									{
										ResultSet rs1=null;
										rs1 = stmt.executeQuery("SELECT * FROM income");
										while(rs1.next())        //如果rs1有数据，循环输出
										{
											String arr[]=new String[6];
											arr[0]=rs1.getString("ino");
											arr[1]=rs1.getString("name");
											arr[2]=rs1.getString("category");
											arr[3]=rs1.getString("income_day");
											arr[4]=rs1.getString("income_money");
											arr[5]=rs1.getString("remark");
											model.addRow(arr);
												}
										
									}
									
									else {
										ResultSet rs=null;
										rs = stmt.executeQuery("SELECT * FROM income where category='"+str3+"'");
									while(rs.next())        //如果rs有数据，循环输出
									{
										String arr[]=new String[6];
										arr[0]=rs.getString("ino");
										arr[1]=rs.getString("name");
										arr[2]=rs.getString("category");
										arr[3]=rs.getString("income_day");
										arr[4]=rs.getString("income_money");
										arr[5]=rs.getString("remark");
										model.addRow(arr);
											}
									
									}
									
								}
								if(c==4)
								{
									while(model.getRowCount()>0) {
										model.removeRow(model.getRowCount()-1);}//清空表格
									
									
									if(str4.equals(null)||str4.equals(""))
									{
										ResultSet rs1=null;
										rs1 = stmt.executeQuery("SELECT * FROM income");
										while(rs1.next())        //如果rs1有数据，循环输出
										{
											String arr[]=new String[6];
											arr[0]=rs1.getString("ino");
											arr[1]=rs1.getString("name");
											arr[2]=rs1.getString("category");
											arr[3]=rs1.getString("income_day");
											arr[4]=rs1.getString("income_money");
											arr[5]=rs1.getString("remark");
											model.addRow(arr);
												}
										
									}
									
									else {
										ResultSet rs=null;
										rs = stmt.executeQuery("SELECT * FROM income where income_day='"+str4+"'");
									while(rs.next())        //如果rs有数据，循环输出
									{
										String arr[]=new String[6];
										arr[0]=rs.getString("ino");
										arr[1]=rs.getString("name");
										arr[2]=rs.getString("category");
										arr[3]=rs.getString("income_day");
										arr[4]=rs.getString("income_money");
										arr[5]=rs.getString("remark");
										model.addRow(arr);
											}
									
									}
									
								}
								if(c==5)
								{
									while(model.getRowCount()>0) {
										model.removeRow(model.getRowCount()-1);}//清空表格
									
									
									if(str5.equals(null)||str5.equals(""))
									{
										ResultSet rs1=null;
										rs1 = stmt.executeQuery("SELECT * FROM income");
										while(rs1.next())        //如果rs1有数据，循环输出
										{
											String arr[]=new String[6];
											arr[0]=rs1.getString("ino");
											arr[1]=rs1.getString("name");
											arr[2]=rs1.getString("category");
											arr[3]=rs1.getString("income_day");
											arr[4]=rs1.getString("income_money");
											arr[5]=rs1.getString("remark");
											model.addRow(arr);
												}
										
									}
									
									else {
										ResultSet rs=null;
										rs = stmt.executeQuery("SELECT * FROM income where income_money='"+str5+"'");
									while(rs.next())        //如果rs有数据，循环输出
									{
										String arr[]=new String[6];
										arr[0]=rs.getString("ino");
										arr[1]=rs.getString("name");
										arr[2]=rs.getString("category");
										arr[3]=rs.getString("income_day");
										arr[4]=rs.getString("income_money");
										arr[5]=rs.getString("remark");
										model.addRow(arr);
											}
									
									}
									
								}
								
								} catch(SQLException e2) {
								e2.printStackTrace();
							   e2.getMessage();
							   type=JOptionPane.PLAIN_MESSAGE;
								JOptionPane.showMessageDialog(f6, e2.getMessage(),title,type);
								}
					
						}
					});	
					
					b2.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							String title="提示";
							int type=JOptionPane.PLAIN_MESSAGE;
							String str1;
							str1=tf1.getText();
							String str2;
							str2=tf2.getText();
							String str3;
							str3=tf3.getText();
							String str4;
							str4=tf4.getText();
							String str5;
							str5=tf5.getText();
							Statement stmt=null;
							ResultSet rs=null;
							try {
								stmt = sql.conn.createStatement();
								if(c==1)
								{
								rs = stmt.executeQuery("SELECT * FROM income where ino like '"+str1+""+"%"+"'");
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
							
								}
								if(c==2)
								{
								rs = stmt.executeQuery("SELECT * FROM income where name like '"+str2+""+"%"+"'");
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
							
								}
								
								if(c==3)
								{
								rs = stmt.executeQuery("SELECT * FROM income where category like '"+str3+""+"%"+"'");
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
							
								}
								
								if(c==4)
								{
								rs = stmt.executeQuery("SELECT * FROM income where income_day like '"+str4+""+"%"+"'");
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
							
								}
								
								if(c==5)
								{
								rs = stmt.executeQuery("SELECT * FROM income where income_money like '"+str5+""+"%"+"'");
								while(model.getRowCount()>0) {
									model.removeRow(model.getRowCount()-1);}//清空表格
								while(rs.next())        //如果rs有数据，循环输出
								{
									String arr[]=new String[6];
									arr[0]=rs.getString("ino");
									arr[1]=rs.getString("name");
									arr[2]=rs.getString("category");
									arr[3]=rs.getString("income_day");
									arr[4]=rs.getString("income_money");
									arr[5]=rs.getString("remark");
									model.addRow(arr);
										}
							
								}
								
								
							} catch(SQLException e2) {
								e2.printStackTrace();
							   e2.getMessage();
							   type=JOptionPane.PLAIN_MESSAGE;
								JOptionPane.showMessageDialog(f6, e2.getMessage(),title,type);
								}
						}
					});
					
                	
                
				

				

			}
		});
		
						
						
							
		
		
        
	}
	public static void main(String []args) {
		income_select is=new income_select();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
