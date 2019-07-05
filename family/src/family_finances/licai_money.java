package family_finances;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
public class licai_money implements ActionListener,ItemListener{
	JFrame f8=new JFrame();
	Container ctp=f8.getContentPane();
	JButton b1=new JButton();
    JButton b2=new JButton();
    JLabel lb=new JLabel("本金:",JLabel.CENTER);
    JLabel lb1=new JLabel("利率:",JLabel.CENTER);
   	JLabel lb2=new JLabel("利息:",JLabel.CENTER);
    JLabel lb3=new JLabel("本利和:",JLabel.CENTER);
    JLabel lb4=new JLabel("理财:",JLabel.CENTER);
    JTextField tf=new JTextField();
    JTextField tf2=new JTextField(); 
    JTextField tf3=new JTextField();
    String name[]= {"","3个月*1.43%","6个月*1.69%","1年*1.95%","2年*2.73%","3年*3.575%","5年*3.575%"};
    JComboBox cbx=new JComboBox();
    int c;
	public licai_money() {
     ctp.setLayout(null);
		
    	f8.setTitle("理财");
		
        
        lb.setBounds(30,80,60,20);
        lb1.setBounds(30,110,60,20);
    	lb2.setBounds(30,140,60,20);
    	lb3.setBounds(30,170,60,20);
    	lb4.setFont(new Font("微软雅黑",Font.BOLD,20));
        lb4.setBounds(70,10,150,80);
        
    	ctp.add(lb);
        ctp.add(lb1);
        ctp.add(lb2);
        ctp.add(lb3);
        ctp.add(lb4);
        
        tf.setBounds(100,80,100,20);
    	tf2.setBounds(100,140,100,20);
    	tf3.setBounds(100,170,100,20);
    	ctp.add(tf);
        ctp.add(tf2);
        ctp.add(tf3);
    	
        b1.setText("计算");
    	b2.setText("取消");
    	ctp.add(b1);
        ctp.add(b2);
        b1.setBounds(50,210,60,30);
    	b2.setBounds(160,210,60,30);
        
    	cbx.setEditable(false);
		for(int j=0;j<name.length;j++)
		    cbx.addItem(name[j]);
		ctp.add(cbx);
		cbx.setBounds(100,110,100,20);
		cbx.addItemListener(this);
    	
		f8.setResizable(false);//窗口不可缩放
	    f8.setVisible(true);//设置窗口可见
	    
	    f8.setLocationRelativeTo(null);//窗口居中
		f8.setSize(300,300);
		tf2.setEditable(false);
		tf3.setEditable(false);
		b2.addActionListener(new ActionListener() {
			
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
				
				f8.dispose();
				Menu m = new Menu();
			}
		});
		cbx.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String str=(String)e.getItem();
				System.out.println(str);
				for(int i=0;i<name.length;i++) 
				if(str.equals(name[i]))
					c=cbx.getSelectedIndex();
				b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						double t ;
						String str1;
						str1=tf.getText();
						if(str1.equals(null)==false&&str1.equals("")==false)
							t=Double.valueOf(str1);
						else {
							t=0.0;
						}
						if(c==1)
						{
							double a,b;
							a=(double) (t*((1.43/100)/12)*3);
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
						if(c==2)
						{
							double a,b;
							a=(double) (t*((1.69/100)/12)*6);
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
						if(c==3)
						{
							double a,b;
							a=(double) (t*(1.95/100));
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
						if(c==4)
						{
							double a,b;
							a=(double) (t*(2.73/100)*2);
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
						if(c==5)
						{
							double a,b;
							a=(double) (t*(3.575/100)*3);
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
						if(c==6)
						{
							double a,b;
							a=(double) (t*(3.575/100)*5);
							b=a+t;
							tf2.setText(String.valueOf(a));
							tf3.setText(String.valueOf(b));
							
						}
					}
				});
				
			}
		});
		
	}
	public static void main(String []args) {
		licai_money lm=new licai_money();
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}