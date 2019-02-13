import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class FrmAddEditItem extends JInternalFrame implements ActionListener
{
	JLabel lblItemId,lblItemName,lblSubCategory,lblCategory,lblDescription,lblPrice;
	JTextField jtfItemid,jtfItemName,jtfPrice,jtfImage,jtfCategory;
	JTextArea jtaDescription;
	JButton btnSave,btnCancel,btnImage;
	JComboBox jcbSubCategory;
	Database db;
	ResultSet rst;
	int id;
	static FrmAddEditItem faei;
	ImageIcon ii;
	Image image,newimage; 
	
	FrmAddEditItem(boolean flag,String query,FrmItem fi)
	{
		super("Add/Edit Item",false,true,false,false);
		
		setLayout(new GridLayout(8,2));
		setBackground(new Color(128,128,255));
		db=new Database();
		faei=this;
		
	    lblItemId=new JLabel("Item Id");
	    lblItemId.setForeground(new Color(0,0,64));
     	lblItemId.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblItemId);
		
	    jtfItemid=new JTextField();
	    jtfItemid.setForeground(new Color(49,49,49));
		jtfItemid.setBackground(new Color(192,192,192));
		jtfItemid.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
	    jtfItemid.setEditable(false);
     	add(jtfItemid);
		
    	lblItemName=new JLabel("Item Name");
    	lblItemName.setForeground(new Color(0,0,64));
     	lblItemName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
    	add(lblItemName);
		
     	jtfItemName=new JTextField();
     	jtfItemName.setForeground(new Color(106,106,106));
     	jtfItemName.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtfItemName);
     	
     	lblSubCategory=new JLabel("Sub Category");
     	lblSubCategory.setForeground(new Color(0,0,64));
     	lblSubCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(lblSubCategory);
     	
		jcbSubCategory=new JComboBox();
		jcbSubCategory.setForeground(new Color(106,106,106));
     	jcbSubCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		try
    	{
    		rst=db.getData("select * from subcategories");
    		while(rst.next())
    		{
    			jcbSubCategory.addItem(rst.getString(2));
    		}
    		db.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
     	add(jcbSubCategory);
     	jcbSubCategory.addFocusListener(new FocusAdapter()
     	{
     		public void focusLost(FocusEvent fe)
     		{
     			String s1=(String)jcbSubCategory.getSelectedItem();
   				int cid=CommonMethods.getCategoryIdFromSubCategoryName(s1);
   				String cname=CommonMethods.getCategoryName(cid);
   				jtfCategory.setText(cname);
     		}
     	});
		
     	lblCategory=new JLabel("Category");
     	lblCategory.setForeground(new Color(0,0,64));
     	lblCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
	    add(lblCategory);
     	
		jtfCategory=new JTextField();
		jtfCategory.setForeground(new Color(106,106,106));
     	jtfCategory.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		jtfCategory.setEditable(false);
     	add(jtfCategory);
     	
     	lblDescription=new JLabel("Description");
     	lblDescription.setForeground(new Color(0,0,64));
     	lblDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(lblDescription);
     	
     	jtaDescription=new JTextArea();
     	jtaDescription.setForeground(new Color(106,106,106));
     	jtaDescription.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtaDescription);
     	
     	lblPrice=new JLabel("Price");
     	lblPrice.setForeground(new Color(0,0,64));
     	lblPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
	    add(lblPrice);
		
		jtfPrice=new JTextField();
		jtfPrice.setForeground(new Color(106,106,106));
     	jtfPrice.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	add(jtfPrice);
     	
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
	    		rst=db.getData("select max(itemid) from item");
	        	rst.next();
	        	id=rst.getInt(1)+1;
	        	jtfItemid.setText(id+"");
     		    btnSave=new JButton("Save");
     		    db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData(query);
	    		rst.next();
	    		id=rst.getInt(1);
	    		jtfItemid.setText(rst.getInt(1)+"");
	    		jtfItemName.setText(rst.getString(2));
	    		int subcatid=rst.getInt(3);
    			jcbSubCategory.setSelectedItem(CommonMethods.getSubCategoryName(subcatid));
	    		int cid=rst.getInt(4);
	    		jtfCategory.setText(CommonMethods.getCategoryName(cid));
	    		jtaDescription.setText(rst.getString(5));
	    		jtfPrice.setText(rst.getInt(6)+"");
	    		jtfImage.setText(rst.getString(7));
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
	   			int itemid=Integer.parseInt(jtfItemid.getText());
	   			String itemname=jtfItemName.getText();
	   			String subCategoryName=(String)jcbSubCategory.getSelectedItem();
	   			int subcategoryid=0;
	   			try
	   			{
	   				rst=db.getData("select subcategoryid from subcategories where subcategoryname='"+subCategoryName+"'");
	   				rst.next();
	   				subcategoryid=rst.getInt(1);
	   				db.close();
	   			}
	   			catch(SQLException e)
	   			{
	   				e.printStackTrace();
	   			}
	   			String categoryname=jtfCategory.getText();
	   			int categoryid=0;
	   			try
	   			{
	   				rst=db.getData("select categoryid from categories where categoryname='"+categoryname+"'");
	   				rst.next();
	   				categoryid=rst.getInt(1);
	   				db.close();
	   			}
	   			catch(SQLException e)
	   			{
	   				e.printStackTrace();
	   			}
	   			
	   			
	   			
	   			String description=jtaDescription.getText();
	   			int price=Integer.parseInt(jtfPrice.getText());
	   			String image=jtfImage.getText();
	   			
	   			if(flag)	
	 				db.setData("insert into item values("+itemid+",'"+itemname+"',"+subcategoryid+","+categoryid+",'"+description+"',"+price+",'"+image+"')");
	   			else
	   				db.setData("update item set itemname='"+itemname+"',subcategoryid="+subcategoryid+",description='"+description+"',categoryid="+categoryid+",price="+price+",image='"+image+"' where itemid="+itemid);	    				

	   			dispose();
	   			fi.reload();
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
				int result = fileChooser.showOpenDialog(faei);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					File f1 = fileChooser.getSelectedFile();
					String name=f1.getName();
					int index=name.indexOf('.');
 			        String ext=name.substring(index);
 			        String name2="item"+id+ext;
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