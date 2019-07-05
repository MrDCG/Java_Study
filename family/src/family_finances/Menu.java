package family_finances;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.DatabaseMetaData;

import family_finances.databese;
import family_finances.users;
import family_finances.family_member;
import family_finances.income_money;
import family_finances.expend_money;
import family_finances.income_select;
import family_finances.expend_select;
import family_finances.licai_money;
import family_finances.count_money;
public  class Menu extends JFrame implements ActionListener{
	JFrame f2 =new JFrame();
	Container ctp=f2.getContentPane();
	JTextArea tf=new JTextArea();
	JMenuBar bar=new JMenuBar();
	
	JMenu menu0=new JMenu("用户管理");
	JMenuItem paw=new JMenuItem("修改密码");
	JMenuItem back=new JMenuItem("返回登陆");
	
	JMenu menu6=new JMenu("退出");
	JMenuItem quit=new JMenuItem("退出");
	
	JMenu menu1=new JMenu("信息管理");
	JMenuItem family=new JMenuItem("成员管理");
	JMenuItem income=new JMenuItem("收入管理");
	JMenuItem expend=new JMenuItem("֧支出管理");
	
	
	JMenu menu2=new JMenu("信息查询");
	JMenuItem incomese=new JMenuItem("收入查询");
	JMenuItem expendse=new JMenuItem("支出查询");
	
	JMenu menu3=new JMenu("理财");
	JMenuItem licai=new JMenuItem("银行理财");
	
	
	JMenu menu5=new JMenu("统计");
	JMenuItem count=new JMenuItem("收支统计");
	public  Menu() {
		
		f2.setTitle("菜单");
		f2.setSize(700,550);
        f2.getContentPane().add(new JScrollPane(tf));
		tf.setEditable(false);
		bar.setOpaque(true);
		f2.setJMenuBar(bar);
		menu0.add(paw);menu0.addSeparator();
		menu0.add(back);
		bar.add(menu0);
		paw.addActionListener(this);
		back.addActionListener(this);
		
		menu1.add(family);menu1.addSeparator();//加分界线
		menu1.add(income);menu1.addSeparator();
		menu1.add(expend);
	
		bar.add(menu1);
		family.addActionListener(this);
		income.addActionListener(this);
		expend.addActionListener(this);
		
		
		menu2.add(incomese);menu2.addSeparator();
		menu2.add(expendse);
		bar.add(menu2);
		incomese.addActionListener(this);
		expendse.addActionListener(this);
		
		menu3.add(licai);
		bar.add(menu3);
		licai.addActionListener(this);
		
		menu5.add(count);
		bar.add(menu5);
		count.addActionListener(this);
		
		bar.add(menu6);
		menu6.add(quit);
		quit.addActionListener(this);
		f2.setVisible(true);
		f2.setLocationRelativeTo(null);
	} 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==paw) {
			f2.dispose();
			users u=new users();}
		
		if(e.getSource()==back) {
			f2.dispose();
			databese db=new databese();}
		
		if(e.getSource()==family) {
			f2.dispose();
			family_member fm=new family_member();}
			
		if(e.getSource()==income) {
			f2.dispose();
			income_money im=new income_money();}
		
		if(e.getSource()==expend) {
			f2.dispose();
			expend_money em=new expend_money();}
		
		if	(e.getSource()==quit)
    	{
    		
    		int i=JOptionPane.showConfirmDialog(f2, "确定退出系统吗？","提示ʾ",JOptionPane.YES_NO_OPTION);
    		if(i==JOptionPane.YES_OPTION)
    			System.exit(0);
    	}
		
		if(e.getSource()==incomese) {
			f2.dispose();
			income_select is=new income_select();}
		
		if(e.getSource()==expendse) {
			f2.dispose();
			expend_select es=new expend_select();}
		
		if(e.getSource()==licai) {
			f2.dispose();
			licai_money lm=new licai_money();}
			
		if(e.getSource()==count) {
			f2.dispose();
			count_money cm=new count_money();}
	}

	    
	public static void main(String []args) {
		Menu m = new Menu();
	}

	
	
		
	}


	


