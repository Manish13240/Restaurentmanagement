import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;


public class FrmAddEditItemSubCategory extends JInternalFrame implements ActionListener
{
	JLabel lblSubCategoryId,lblSubCategoryName,lblDescription,lblCategory;
	JTextField jtfSubCategoryId,jtfSubCategoryName,jtfImage;
	JComboBox jcbCategory;
	JTextArea jtaDescription;
	//JComboBox jcbCategoryId;
	JButton btnSave,btnCancel,btnImage;
	Database db;
	ResultSet rst;
	int id;
	static FrmAddEditItemSubCategory faesc;
	ImageIcon ii;
	Image image,newimage; 
	
	FrmAddEditItemSubCategory(boolean flag,String query,FrmItemSubCategory fisbc)
	{
		super("Add/Edit Item SubCategory",false,true,false,false);
		setLayout(new GridLayout(6,2));
		setBackground(new Color(128,128,255));
		db=new Database();
		faesc=this;
		
	    lblSubCategoryId=new JLabel("SubCategory Id");
	    lblSubCategoryId.setForeground(new Color(0,0,64));
     	lblSubCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblSubCategoryId);
		
		jtfSubCategoryId=new JTextField();
		jtfSubCategoryId.setForeground(new Color(49,49,49));
		jtfSubCategoryId.setBackground(new Color(192,192,192));
		jtfSubCategoryId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
	    jtfSubCategoryId.setEditable(false);
     	add(jtfSubCategoryId);
		
    	lblSubCategoryName=new JLabel("SubCategory Name");
    	lblSubCategoryName.setForeground(new Color(0,0,64));
     	lblSubCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblSubCategoryName);
		
     	jtfSubCategoryName=new JTextField();
     	jtfSubCategoryName.setForeground(new Color(106,106,106));
     	jtfSubCategoryName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtfSubCategoryName);
     	
     	lblDescription=new JLabel("Description");
     	lblDescription.setForeground(new Color(0,0,64));
     	lblDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblDescription);
		
     	jtaDescription=new JTextArea();
     	jtaDescription.setForeground(new Color(106,106,106));
     	jtaDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtaDescription);
     	
     	lblCategory=new JLabel("Category");
     	lblCategory.setForeground(new Color(0,0,64));
     	lblCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblCategory);
    	
    	jcbCategory=new JComboBox();
    	jcbCategory.setForeground(new Color(106,106,106));
     	jcbCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	try
    	{
    		rst=db.getData("select * from categories");
    		while(rst.next())
    		{
    			jcbCategory.addItem(rst.getString(2));
    		}
    		db.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
     	add(jcbCategory);
     	
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
	    		rst=db.getData("select max(subcategoryid) from subcategories");
	        	rst.next();
	        	id=rst.getInt(1)+1;
	        	jtfSubCategoryId.setText(id+"");
     		    btnSave=new JButton("Save");
     		    db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData(query);
	    		rst.next();
	    		id=rst.getInt(1);
	    		jtfSubCategoryId.setText(rst.getInt(1)+"");
	    		jtfSubCategoryName.setText(rst.getString(2));
	    		jtaDescription.setText(rst.getString(4));
	    		jtfImage.setText(rst.getString(5));
	    		int cid=rst.getInt(3);
	    		String cname="";
	    		try
	   			{
	   				rst=db.getData("select categoryname from categories where categoryid="+cid);
					rst.next();
					cname=rst.getString(1);
	   			}
	   			catch(SQLException e)
	   			{
	   				e.printStackTrace();
	   			}
	   			jcbCategory.setSelectedItem(cname);
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
	   			int subcategoryid=Integer.parseInt(jtfSubCategoryId.getText());
	   			String subcategoryname=jtfSubCategoryName.getText();
	   			String description=jtaDescription.getText();
	   			String categoryName=(String)jcbCategory.getSelectedItem();
	   			String image=jtfImage.getText();
	   			
	   			int categoryid=0;
	   			try
	   			{
	   				rst=db.getData("select categoryid from categories where categoryname='"+categoryName+"'");
					rst.next();
					categoryid=rst.getInt(1);
	   			}
	   			catch(SQLException e)
	   			{
	   				e.printStackTrace();
	   			}
	   			
	   			if(flag)	
	   				db.setData("insert into subcategories values("+subcategoryid+",'"+subcategoryname+"',"+categoryid+",'"+description+"','"+image+"')");
	   			else
	   				db.setData("update subcategories set subcategoryname='"+subcategoryname+"',description='"+description+"' , categoryid="+categoryid+" ,image='"+image+"' where subcategoryid="+subcategoryid+"");	    				

	   			dispose();
	   			fisbc.reload();
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
		
	   	setSize(330,300);
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
				int result = fileChooser.showOpenDialog(faesc);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					File f1 = fileChooser.getSelectedFile();
					String name=f1.getName();
					int index=name.indexOf('.');
 			        String ext=name.substring(index);
 			        String name2="subcategory"+id+ext;
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