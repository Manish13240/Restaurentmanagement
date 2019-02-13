import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmAddEditCustomer extends JInternalFrame
{
	JLabel lblCustomerid,lblName,lblAddress,lblContact,lblEmail;
	JTextField jtfCustomerid,jtfName,jtfContact,jtfEmail; 
	JTextArea jtaAddress;
	JComboBox jcbCity,jcbState,jcbCountry;
	JButton btnSave,btnCancel;
	Database db;
	ResultSet rst;
	int id;
	FrmAddEditCustomer(boolean flag,String query,FrmCustomer fcs)
	{
		super("Add/Edit Customer",false,true,false,false);
		
		setLayout(new GridLayout(6,2));
		db=new Database();
		
	    lblCustomerid=new JLabel("Customer Id");
    	add(lblCustomerid);
		
	    jtfCustomerid=new JTextField();
	    jtfCustomerid.setEditable(false);
     	add(jtfCustomerid);
		
    	lblName=new JLabel("Customer Name");
    	add(lblName);
		
     	jtfName=new JTextField();
     	add(jtfName);
		
     	lblContact=new JLabel("Phone");
	    add(lblContact);
		
		jtfContact=new JTextField();
     	add(jtfContact);
     	
     	lblAddress=new JLabel("Address");
	    add(lblAddress);
     	
		jtaAddress=new JTextArea();
     	add(jtaAddress);
     	
     	lblEmail=new JLabel("Email");
	    add(lblEmail);
		
		jtfEmail=new JTextField();
     	add(jtfEmail);
     	
   
     	
		try
		{
		    if(flag)
	    	{
	    		rst=db.getData("select max(customerid) from customers");
	        	rst.next();
	        	id=rst.getInt(1)+1;
	        	jtfCustomerid.setText(id+"");
     		    btnSave=new JButton("Save");
     		    db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData(query);
	    		rst.next();
	    		jtfCustomerid.setText(rst.getString(1));
	    		jtfName.setText(rst.getString(2));
	    		jtfContact.setText(rst.getString(3));
	    		jtaAddress.setText(rst.getString(4));
	    		jtfEmail.setText(rst.getString(5));
	    		btnSave=new JButton("Update");
	    		db.close();
	    	}
	   	}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
	   	btnSave.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int customerid=Integer.parseInt(jtfCustomerid.getText());
	   			String name=jtfName.getText();
	   			String phone=jtfContact.getText();
	   			String address=jtaAddress.getText();
	   			String email=jtfEmail.getText();
	   			if(flag)	
	   				db.setData("insert into customers values("+customerid+",'"+name+"','"+phone+"','"+address+"','"+email+" ')");
	   			else
	   				db.setData("update customers set customername='"+name+"',contact='"+phone+"',address='"+address+"',email='"+email+"' where customerid="+customerid);	    				

	   			dispose();
	   			fcs.reload();
	   		}
	   	});
	   	add(btnSave);
		
	   	btnCancel=new JButton("Cancel");
	   	btnCancel.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			dispose();
	   		}
	   	});
	   	add(btnCancel);
		
	   	setSize(300,300);
	   	setLocation(CommonMethods.getCenterPoint(getSize()));
	   	setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	   	setResizable(false);
	   	setVisible(true);
	}
} 