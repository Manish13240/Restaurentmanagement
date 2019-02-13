import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmChangePassword extends JInternalFrame implements ActionListener
{ 
    JLabel lblUser,lblOldPass,lblNewPass,lblReType;
    JTextField jtfUser;
    JPasswordField jpfOldPass,jpfNewPass,jpfReType;
    JButton btnUpdate;
    private String user;
    ResultSet rst;
	FrmChangePassword(String user)
	{
	  	super("Change Password",false,true,false,false);
		this.user=user;
		
		setLayout(null);
		
		lblUser=new JLabel("User Name");
		lblUser.setBounds(0,0,100,30);
		add(lblUser);
		
		jtfUser=new JTextField();
		jtfUser.setBounds(100,2,190,28);
		jtfUser.setText(user);
		jtfUser.setEditable(false);
		add(jtfUser);
		
		
		lblOldPass=new JLabel("Old Password");
		lblOldPass.setBounds(0,35,100,30);
		add(lblOldPass);
		
		jpfOldPass=new JPasswordField();
		jpfOldPass.requestFocus();
		jpfOldPass.setBounds(100,35,190,30);
		add(jpfOldPass);
		
		lblNewPass=new JLabel("New Password");
		lblNewPass.setBounds(0,70,100,30);
		add(lblNewPass);
		
		jpfNewPass=new JPasswordField();
		jpfNewPass.setBounds(100,70,190,30);
		add(jpfNewPass);
		
		lblReType=new JLabel("Re Type");
		lblReType.setBounds(0,105,100,30);
		add(lblReType);
		
		jpfReType=new JPasswordField();
		jpfReType.setBounds(100,105,190,30);
		add(jpfReType);
		
		btnUpdate=new JButton("Update");
		btnUpdate.setBounds(70,137,140,30);
		btnUpdate.addActionListener(this);
		add(btnUpdate);
		
		
		setSize(310,208);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);	
	}
	
    
    public void actionPerformed(ActionEvent ae)
			{
				boolean flag1=false,flag2=false,flag3=false;
				String oldpass=new String(jpfOldPass.getPassword());
				String newpass=new String(jpfNewPass.getPassword());
				String retype=new String(jpfReType.getPassword());
				
				Database db=new Database();
				rst=db.getData("select * from users where userid='"+user+"'");
				String pass="";
				try
				{
					rst.next();
					pass=rst.getString(3);
					db.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				if(oldpass.isEmpty() || newpass.isEmpty() || retype.isEmpty())
					JOptionPane.showInternalMessageDialog(this,"All fields are mandatory to fill","Error",JOptionPane.ERROR_MESSAGE);
				else if(!(oldpass.equals(pass)))
					JOptionPane.showInternalMessageDialog(this,"Old Password is invalid","Error",JOptionPane.ERROR_MESSAGE);
				else if(!(newpass.length()>=8 && newpass.length()<=15))
				    JOptionPane.showInternalMessageDialog(this,"Password Should be 8 to 15 Characters Long","Error",JOptionPane.ERROR_MESSAGE);
	     		else if(!newpass.equals(retype))
		    		JOptionPane.showInternalMessageDialog(this,"Password and Re Type mismatch","Error",JOptionPane.ERROR_MESSAGE);
			    else
	     		{
		    		for(int i=0;i<newpass.length();i++)
			    	{
				    	char ch=newpass.charAt(i);
		     			if((ch>='A' && ch<='Z') || (ch>='a' && ch<='z'))
		     				flag1=true;
			    		else if(ch>='0' && ch<='9')
			    			flag2=true;
			    		else
			    			flag3=true;
		    		}
		    		if(flag1==true && flag2==true && flag3==true)
		       		{
						    db.setData("update users set password='"+newpass+"' where userid='"+user+"'");
						    String s="Password Updated Sucessfully";
				    		s+=" and Password is ";
				    		s+=newpass;
				    		JOptionPane.showInternalMessageDialog(this,s,"Update",JOptionPane.PLAIN_MESSAGE);
				            dispose();
				    /*		int ans=JOptionPane.showInternalConfirmDialog(this,"Are you want to login again","Conformation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				    	    if(ans==JOptionPane.YES_OPTION)
				    	    {
				    	    	dispose();
				    	    	new Login();
				    	    }*/ 	        
			    	}	
				else
					JOptionPane.showInternalMessageDialog(this,"Password Must Contains atleast one alpha,one numbers and one symbols","Error",JOptionPane.ERROR_MESSAGE);
		    	}
				jpfOldPass.requestFocus();	
	    	}
    

}