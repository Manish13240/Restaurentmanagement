import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class FrmItemSubCategory extends JInternalFrame
{
	static FrmItemSubCategory fisbc;
	JTable jtb;
	JScrollPane jsp;
	Object data[][];
	String head[]={"SubCategory Id","SubCategory Name","Description","Category","Image"};
	JButton btnAdd,btnEdit,btnSearch,btnDelete,btnShowAll;
	JPanel jp;
	Database db;
	ResultSet rst;
	static String query="";
	ImageIcon ii;
	Image image,newimage;
	JTableHeader header;

	
	
	FrmItemSubCategory(JDesktopPane jdp)
	{
		super("Item SubCategories",true,true,true,true);
		db=new Database();
		fisbc=this;
	    
	    
	    jtb=createJTable();
	    jtb.setRowHeight(30);
	    jtb.setRowMargin(10);
	    Dimension d1=new Dimension(10,10);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(0,128,64));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD,18));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(0,64,64));
		header.setFont(new Font(Font.SERIF,Font.BOLD,22));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,255,0));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
	    
		    
	    btnAdd=new JButton("Add");
	    btnAdd.setForeground(new Color(0,128,0));
		//btnAdd.setBackground(new Color(192,192,192));
		btnAdd.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnAdd.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			FrmAddEditItemSubCategory faesbc=new FrmAddEditItemSubCategory(true,"",fisbc);
	   		    jdp.add(faesbc);
	   		    jdp.setComponentZOrder(faesbc,0);
	   		    jdp.setComponentZOrder(fisbc,1);
	   		}
	   	});
	    	
	    btnEdit=new JButton("Edit");
	    btnEdit.setForeground(new Color(0,128,0));
		//btnEdit.setBackground(new Color(192,192,192));
		btnEdit.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnEdit.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fisbc,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	int s1=(Integer)jtb.getValueAt(row,0);
					FrmAddEditItemSubCategory faesbc=new FrmAddEditItemSubCategory(false,"select * from subcategories where subcategoryid="+s1+"",fisbc);
					jdp.add(faesbc);
					jdp.setComponentZOrder(faesbc,0);
					jdp.setComponentZOrder(fisbc,1);	
	   		    }
	   		}
	   	});
	    	
	    btnSearch=new JButton("Search");
	    btnSearch.setForeground(new Color(0,128,0));
		//btnSearch.setBackground(new Color(192,192,192));
		btnSearch.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnSearch.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			query="";
	   			FrmSearchItemSubCategory search=new FrmSearchItemSubCategory(fisbc);
	            jdp.add(search);
	            jdp.setComponentZOrder(search,0);
	   		    jdp.setComponentZOrder(fisbc,1);
	        }
	  	});
	    
	    btnDelete=new JButton("Delete");
	    btnDelete.setForeground(new Color(0,128,0));
		//btnDelete.setBackground(new Color(192,192,192));
		btnDelete.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnDelete.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			try {
	   				int row=jtb.getSelectedRow();
	   				if(row==-1)
	   					JOptionPane.showInternalMessageDialog(fisbc,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   				else
	   				{
	   					int itemid=(Integer)jtb.getValueAt(row,0);
	   					rst=db.getData("select image from subcategories where subcategoryid="+itemid);
	   					rst.next();
	   					File f=new 	File("itemimages/"+rst.getString(1));
	   					f.delete();
	   					db.close();
					
	   					rst=db.getData("select image from item where subcategoryid="+itemid);
	   					while(rst.next()) {
	   						f=new File("itemimages/"+rst.getString(1));	
	   						f.delete();
	   					}
	   					db.close();
	   					
	   					db.setData("delete from item where subcategoryid="+itemid);
	   					db.setData("delete from subcategories where subcategoryid="+itemid);
	   					reload();
	   				}
	   			}
	   			catch(SQLException e) {
	   				e.printStackTrace();
	   			}
	   		}
	   	});
	    btnShowAll=new JButton("Show All");
	    btnShowAll.setForeground(new Color(0,128,0));
		//btnShowAll.setBackground(new Color(192,192,192));
		btnShowAll.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnShowAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
	   			   query="";
	   			   reload();
			}
		});
	    jp=new JPanel();
	    jp.setLayout(new GridLayout(1,5));
	    jp.add(btnAdd);
	    jp.add(btnEdit);
	    jp.add(btnSearch);
	    jp.add(btnShowAll);
	    jp.add(btnDelete);
	    add(jp,BorderLayout.SOUTH);	
			
		setSize(900,550);
		setLocation(270,30);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public JTable createJTable()
	{
		try
		{
			if(query==null || "".equals(query))
				rst=db.getData("select count(*) from subcategories");
			else
			{
				//System.out.println("select * " + query);
				rst=db.getData("select count(*)"+query);
			}
			rst.next();
		    int n=rst.getInt(1);
		    if(n!=0)
		    {
		    	data=new Object[n][5];
		    	if(query==null || "".equals(query))
		    		rst=db.getData("select * from subcategories");
		    	else
		    	{
		    		
		    		rst=db.getData("select * " + query);
		    		
		    	}
		    	int i=0;
		        while(rst.next())
		        {
		        	data[i][0]=rst.getInt(1);
		        	data[i][1]=rst.getString(2);
		        	data[i][2]=rst.getString(4);
		        	int categoryid=rst.getInt(3);
		        	try
	   				{
	   					Database db2=new Database();
	   					ResultSet rst2=db2.getData("select categoryname from categories where categoryid="+categoryid);
						rst2.next();
						data[i][3]=rst2.getString(1);
						db2.close();
	   				}
	   				catch(SQLException e)
	   				{
	   					e.printStackTrace();
	   				}
	   				data[i][4]=rst.getString(5);
	   			   	i++;
		        }	
		        db.close();
		    }
		    else
		    {
		    	data=new Object[0][5];
		    }
		    db.close();		    
		}	
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }	
	    	
	    	return new JTable(data,head);	
	}
	
	public void reload()
	{
		remove(jsp);
		jtb=createJTable();
	    jtb.setRowHeight(30);
	    jtb.setRowMargin(10);
	    Dimension d1=new Dimension(10,10);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(0,128,64));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD,18));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(0,64,64));
		header.setFont(new Font(Font.SERIF,Font.BOLD,22));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,255,0));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
		revalidate();
	}

}