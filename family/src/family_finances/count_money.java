package family_finances;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class count_money implements ActionListener,ItemListener{
	JFrame f0=new JFrame();
	Container ctp=f0.getContentPane();
	
	 Object[][]Datename= {};
	    String s[]= {"name","income_money","expend_money"};//列名
	    DefaultTableModel model=new DefaultTableModel(Datename,s);//建立表头
	   
	    JTable ta=new JTable(model);//建立表
	    JScrollPane sp=new JScrollPane(ta);//滚轮
	
	    String name[]= {"","个人收入总计","个人支出总计","总收入","总支出","最大收入","最小收入","最大支出","最小支出","平均收入","平均支出"};
	    JComboBox cbx=new JComboBox();
        
	    JTextArea tr=new JTextArea();
	    JScrollPane sp1=new JScrollPane(tr);//滚轮
	    JButton b=new JButton();
	
	public count_money() {
		f0.setSize(450,550);
    	f0.setTitle("统计");
    	ctp.setLayout(null);
    	ctp.add(sp);
    	 f0.setLocationRelativeTo(null);//窗口居中
    	sp.setBounds(60,30,300,250);
    	
    	tr.setEditable(false);
    	
    	cbx.setEditable(false);
		for(int j=0;j<name.length;j++)
		    cbx.addItem(name[j]);
		ctp.add(cbx);
		cbx.setBounds(40,330,100,20);
		cbx.addItemListener(this);
    	
    	ctp.add(sp1);
    	sp1.setBounds(150,300,215,200);
    	sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    	
    	b.setText("返回");
    	ctp.add(b);
    	b.setBounds(370,480,60,30);
    	
    	b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Statement stmt=null;
				try {
					stmt = sql.conn.createStatement();
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				f0.dispose();
				Menu m = new Menu();
			}
		});
    	
    	Statement stmt=null;
		ResultSet rs=null;
		ta.setEnabled(false);//设置表不可编辑
		try {
			stmt = sql.conn.createStatement();
			rs = stmt.executeQuery("select income.name,income_money,expend_money from income full outer join expend on(income.name=expend.name)");
					/*("select income.name,sum(income_money)income_money,sum(expend_money)expend_money from income full outer join expend on(income.name=expend.name)group by income.name ");*/
			while(rs.next())        //如果rs有数据，循环输出
			{
				String arr[]=new String[3];
				arr[0]=rs.getString("name");
				arr[1]=rs.getString("income_money");
				arr[2]=rs.getString("expend_money");
				model.addRow(arr);
					}
			stmt.close();
		} catch(SQLException e2) {
			e2.printStackTrace();
			}
    	
	f0.setVisible(true);
	}
	public static void main(String []args) {
		count_money cm=new count_money();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int c = 0;
		String str=(String)e.getItem();
		for(int i=0;i<name.length;i++) {
		if(str.equals(name[i]))
		{
			c=cbx.getSelectedIndex();}
		}
		Statement stmt=null;
		ResultSet rs=null;//
		if(c==1)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select name,SUM(income_money )income_money from income Group by name ");
				tr.setText("");
				tr.append("name"+"\t"+"income_money"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("name") +"\t" + rs.getString("income_money")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==2)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select name,SUM(expend_money )expend_money from expend Group by name ");
				tr.setText("");
				tr.append("name"+"\t"+"expend_money"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("name") +"\t" + rs.getString("expend_money")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==3)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select SUM(income_money)income_sum from income");
				tr.setText("");
				tr.append("总收入"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("income_sum")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==4)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select SUM(expend_money)expend_sum from expend");
				tr.setText("");
				tr.append("总支出"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("expend_sum")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==5)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select max(income_money)income_max from income");
				tr.setText("");
				tr.append("最大收入"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("income_max")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==6)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select min(income_money)income_min from income");
				tr.setText("");
				tr.append("最小收入"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("income_min")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==7)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select max(expend_money)expend_max from expend");
				tr.setText("");
				tr.append("最大支出"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("expend_max")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		
		if(c==8)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select min(expend_money)expend_min from expend");
				tr.setText("");
				tr.append("最小支出"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("expend_min")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		
		if(c==9)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select avg(income_money)income_avg from income");
				tr.setText("");
				tr.append("平均收入"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("income_avg")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		if(c==10)
		{
			try {
				stmt = sql.conn.createStatement();//数据库连接
				rs = stmt.executeQuery("select avg(expend_money)expend_avg from expend");
				tr.setText("");
				tr.append("平均支出"+"\t\n");
				while(rs.next())        //如果rs有数据，循环输出
				{
					tr.append(rs.getString("expend_avg")+"\t\n");
					        
				}
				stmt.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
				}
			 
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}

}