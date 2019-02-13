import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmAddEditUsers extends JInternalFrame
{
	JLabel jlbUserName,jlbUserId,jlbPassword,jlbUserType;
	JTextField jtfUserName,jtfUserId,jtfPassword; 
	JComboBox jcbUserType;
	JButton btnSave,btnCancel;
	Database db;
	ResultSet rst;
	int id;
	String query1="select count(*) from users";
	String query2="select * from users";
	String usertype[]={"Admin","User"};

      FrmAddEditUsers(boolean flag,String query,FrmManageUsers fmu)
	{
		super("Add/Edit Users",false,true,false,false);
		
		setLayout(new GridLayout(5,2));
		db=new Database();
		
	    jlbUserName=new JLabel("UserName");
    	
		
		jtfUserName=new JTextField();
		
		
		jlbUserId=new JLabel("UserId");
		
		
	    jtfUserId=new JTextField();
	    //jtfUserId.setEditable(false);
        
		
    	jlbPassword=new JLabel("Password");
    	
		jtfPassword=new JTextField();
     		
		jlbUserType=new JLabel("UserType");
	    
     	
     	jcbUserType=new JComboBox(usertype);
		
		
		
		try
		{
		    if(flag)
	    	{
	    		
     		    btnSave=new JButton("Save");
     		  //  db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData("select * from users");
	    		rst.next();
	    		jtfUserName.setText(rst.getString(1));
	    		jtfUserId.setText(rst.getString(2));
	    		jtfPassword.setText(rst.getString(3));
	    		jcbUserType.setSelectedItem(rst.getString(4));
	    		
	    		btnSave=new JButton("Update");
	    	//	db.close();
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
	   			
	   			String s1=jtfUserName.getText();
	   			String s2=jtfUserId.getText();
	   			String s3=jtfPassword.getText();
	   	        String s4=(String)jcbUserType.getSelectedItem();
	   			if(flag)	
	   			db.setData("insert into users values('"+s1+"','"+s2+"','"+s3+"','"+s4+"')");
	   			else
	   				db.setData("update users set  username='"+s1+"',userid='"+s2+"',password='"+s3+"',usertype='"+s4+"' where userid='"+s2+"'");	    				

	   			dispose();
	   		fmu.reload(query1,query2);
	   		}
	   	});
	   	
		
	   	btnCancel=new JButton("Cancel");
	   	btnCancel.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			dispose();
	   		}
	   	});
	   
	   	add(jlbUserName);add(jtfUserName);
	   	add(jlbUserId);add(jtfUserId);
	   	add(jlbPassword);add(jtfPassword);
	   	add(jlbUserType);add(jcbUserType);
		add(btnSave);add(btnCancel);
		
	   	setSize(300,300);
	   	setLocation(CommonMethods.getCenterPoint(getSize()));
	   	setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	   	setResizable(false);
	   	setVisible(true);
	}
} 
