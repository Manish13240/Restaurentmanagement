import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class FrmAddEditItemCategory extends JInternalFrame implements ActionListener
{
	JLabel lblCategoryid,lblCategoryName,lblDescription;
	JTextField jtfCategoryid,jtfCategoryName,jtfImage;
	JTextArea jtaDescription; 
	JButton btnSave,btnCancel,btnImage;
	Database db;
	ResultSet rst;
	int id;
	static FrmAddEditItemCategory fadc;
	ImageIcon ii;
	Image image,newimage;
	
	FrmAddEditItemCategory(boolean flag,String query,FrmItemCategory fc,FrmMainAdmin fma)
	{
		super("Add/Edit Item Category",false,true,false,false);
		setLayout(new GridLayout(5,2));
		setBackground(new Color(128,128,255));
		db=new Database();
		fadc=this;
		
	    lblCategoryid=new JLabel("Category Id");
	    lblCategoryid.setForeground(new Color(0,0,64));
     	lblCategoryid.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblCategoryid);
		
	    jtfCategoryid=new JTextField();
	    jtfCategoryid.setForeground(new Color(49,49,49));
		jtfCategoryid.setBackground(new Color(192,192,192));
		jtfCategoryid.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
	    jtfCategoryid.setEditable(false);
     	add(jtfCategoryid);
		
    	lblCategoryName=new JLabel("Category Name");
    	lblCategoryName.setForeground(new Color(0,0,64));
     	lblCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblCategoryName);
		
     	jtfCategoryName=new JTextField();
     	jtfCategoryName.setForeground(new Color(106,106,106));
     	jtfCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtfCategoryName);
     	
     	lblDescription=new JLabel("Description");
     	lblDescription.setForeground(new Color(0,0,64));
     	lblDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblDescription);
		
		jtaDescription=new JTextArea();
		jtaDescription.setForeground(new Color(106,106,106));
     	jtaDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		add(jtaDescription);
		
		btnImage=new JButton("Upload Image");
		btnImage.setForeground(new Color(214,214,214));
		btnImage.setBackground(new Color(0,0,0));
		btnImage.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		add(btnImage);
		btnImage.addActionListener(this);
		
		jtfImage=new JTextField();
		jtfImage.setForeground(new Color(106,106,106));
     	jtfImage.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtfImage);
		
		try
		{
		    if(flag)
	    	{
	    		rst=db.getData("select max(categoryid) from categories");
	        	rst.next();
	        	id=rst.getInt(1)+1;
	        	jtfCategoryid.setText(id+"");
     		    btnSave=new JButton("Save");
     		    db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData(query);
	    		rst.next();
	    		id=rst.getInt(1);
	    		jtfCategoryid.setText(rst.getInt(1)+"");
	    		jtfCategoryName.setText(rst.getString(2));
	    		jtaDescription.setText(rst.getString(3));
	    		jtfImage.setText(rst.getString(4));
	    		btnSave=new JButton("Update");
	    		db.close();
	    	}
	   	}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
	    
	    
	    
	    btnSave.setForeground(Color.green);
		btnSave.setBackground(new Color(255,255,255));
		btnSave.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		ii=new ImageIcon("images/save2.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnSave.setIcon(ii);
	    
	    
	   	btnSave.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int Categoryid=Integer.parseInt(jtfCategoryid.getText());
	   			String Categoryname=jtfCategoryName.getText();
	   			String Description=jtaDescription.getText();
	   			String image=jtfImage.getText();
	   			
	   			if(flag)	
	   				db.setData("insert into categories values("+Categoryid+",'"+Categoryname+"','"+Description+"','"+image+"')");
	   			else
	   				db.setData("update categories set categoryname='"+Categoryname+"' ,description='"+Description+"',image='"+image+"' where categoryid="+Categoryid+"");	    				

	   			dispose();
	   			fc.reload();
	   		}
	   	});
	   	add(btnSave);
		
	   	btnCancel=new JButton("Cancel");
	   	btnCancel.setForeground(Color.red);
		btnCancel.setBackground(new Color(255,255,255));
		btnCancel.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		ii=new ImageIcon("images/Cancel.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnCancel.setIcon(ii);
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
	
	public void actionPerformed(ActionEvent e)
		{
			try
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fadc);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					File f1 = fileChooser.getSelectedFile();
					String name=f1.getName();
					int index=name.indexOf('.');
 			        String ext=name.substring(index);
 			        String name2="category"+id+ext;
 			        File f2=new File("itemimages/",name2);
 			        FileInputStream fin=new FileInputStream(f1);
 			        FileOutputStream fout=new FileOutputStream(f2);
 			        int a;
 			        while((a=fin.read())!=-1)
 			        {
 				     fout.write(a);
 			        }
 			        fin.close();
		            fout.close();
		            jtfImage.setText(name2);
				}   
               
				
				
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
} 