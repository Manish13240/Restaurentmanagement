import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

// customers(customerid int auto_increment primary key,customername varchar(20) not null,contact varchar(15) unique key,address varchar(50),email varchar(50))");
		


class FrmCustomerDetail extends JInternalFrame
{
	JButton btnShow,btnSave;
	JTextField jtfMobile,jtfName,jtfAddress,jtfEmail;
	JLabel jlbMobile,jlbName,jlbAddress,jlbEmail;
	JPanel jp;
	Database db;
	ResultSet rst;
	boolean flag=false;
	String mobile="";
	int cid;
	ImageIcon ii;
	Image image,newimage;
	
	FrmCustomerDetail(FrmSales fs)
	{
	 	super("Add Customer Details",true,true,true,true);
	 	setLayout(null);
	 	db=new Database();
	 	setBackground(new Color(128,0,64));
	 	
	 	jlbMobile=new JLabel("Mobile no.");
	 	jlbMobile.setHorizontalAlignment(JLabel.LEFT);
		jlbMobile.setForeground(new Color(255,255,255));
		jlbMobile.setFont(new Font(Font.DIALOG,Font.ITALIC+Font.BOLD,22));
	 	jlbMobile.setBounds(25,5,140,30);
	 	add(jlbMobile);
	 	
	 	jtfMobile=new JTextField();
	 	jtfMobile.setBounds(200,5,150,30);
	 	jtfMobile.setForeground(new Color(255,0,128));
		jtfMobile.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
	 	add(jtfMobile);
	 	
	 	jlbName=new JLabel("NAME");
	 	jlbName.setHorizontalAlignment(JLabel.LEFT);
		jlbName.setForeground(new Color(255,255,255));
		jlbName.setFont(new Font(Font.DIALOG,Font.ITALIC+Font.BOLD,22));
	 	
        jlbAddress=new JLabel("ADDRESS");
        jlbAddress.setHorizontalAlignment(JLabel.LEFT);
		jlbAddress.setForeground(new Color(255,255,255));
		jlbAddress.setFont(new Font(Font.DIALOG,Font.ITALIC+Font.BOLD,22));
        
        jlbEmail=new JLabel("EMAIL ID");
        jlbEmail.setHorizontalAlignment(JLabel.LEFT);
		jlbEmail.setForeground(new Color(255,255,255));
		jlbEmail.setFont(new Font(Font.DIALOG,Font.ITALIC+Font.BOLD,22));
        
        jtfName=new JTextField();
        jtfName.setForeground(new Color(255,0,128));
		jtfName.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
        
        jtfAddress=new JTextField();
        jtfAddress.setForeground(new Color(255,0,128));
		jtfAddress.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
        
        jtfEmail=new JTextField();
        jtfEmail.setForeground(new Color(255,0,128));
		jtfEmail.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		
	 	
	 	btnShow=new JButton("Show");
	 	btnShow.setForeground(new Color(255,0,128));
		btnShow.setBackground(Color.white);
		btnShow.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,23));
	 	ii=new ImageIcon("images/tick.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(40,35,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnShow.setIcon(ii);
		btnShow.setIconTextGap(10);
	 	btnShow.setBounds(125,50,150,45);
	 	add(btnShow);
	 	btnShow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				 mobile=jtfMobile.getText();
				 try
				 {
					rst=db.getData("select * from customers where contact='"+mobile+"'");
					if(rst.next())
					{
						flag=true;
						cid=rst.getInt(1);
						jtfName.setText(rst.getString(2));
						jtfAddress.setText(rst.getString(4));
						jtfEmail.setText(rst.getString(5));
					}
					db.close();
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				if(flag==false)
				{
					JOptionPane.showMessageDialog(null,"No record found! \n  Please fill up details of the customer");
					jtfName.requestFocus();
					try
					{
						rst=db.getData("select max(customerid) from customers");
						rst.next();
						cid=rst.getInt(1) +1;
					}
					catch(SQLException e)
					{
						JOptionPane.showMessageDialog(null,e);
					}
				}
	   		
			}
		});
	 	
	 	
        
        jp=new JPanel();
        jp.setLayout(new GridLayout(3,3));
        jp.setBackground(new Color(128,0,64));
        jp.add(jlbName);
	    jp.add(jtfName);
	    jp.add(jlbAddress);
	    jp.add(jtfAddress);
	    jp.add(jlbEmail);
	    jp.add(jtfEmail);
	    jp.setBounds(25,100,280,200);
	    add(jp);
	    
	    
	    btnSave=new JButton("Save/Update");
	    btnSave.setForeground(Color.black);
		btnSave.setBackground(new Color(192,192,192));
		btnSave.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,23));
	 	ii=new ImageIcon("images/signupIcon.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnSave.setIcon(ii);
		btnSave.setIconTextGap(10);
	 	btnSave.setBounds(80,320,210,50);
	 	add(btnSave);
	    btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				
				String s1=jtfName.getText();
				String s2=jtfAddress.getText();
				String s3=jtfEmail.getText();
				
				
				if(mobile=="" || s1.isEmpty() || s2.isEmpty() || s3.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"CAN'T SAVE EMPTY RECORD!!","ERROR",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(flag==true)
				{
					db.setData("update customers set customername='"+s1+"',address='"+s2+"',email='"+s3+"' where contact='"+mobile+"'");
				}
				else
				{
					db.setData("insert into customers(customername,contact,address,email) values('"+s1+"','"+mobile+"','"+s2+"','"+s3+"')");
				}
				fs.jtfCustomer.setText(s1);
				db.setData("update ordermaster set customerid="+cid+" where orderid="+fs.orderid+"");
				db.close();
				dispose();
			}
		});
	 	
	 	
	 	
	 	
	 	setSize(400,430);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	 	
	 }
}