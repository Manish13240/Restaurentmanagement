import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;


public class FrmItemCategory extends JInternalFrame
{
	JTable jtb;
	JScrollPane jsp;
	Object data[][];
	String head[]={"Category Id","Category Name","Description","Image"};
    JPanel jp;
    JButton btnAdd,btnEdit,btnDelete;
	Database db;
	ResultSet rst;
	static FrmItemCategory fc;
	static String query="";
	ImageIcon ii;
	Image image,newimage;
	JTableHeader header;


	FrmItemCategory(JDesktopPane jdp,FrmMainAdmin fma)
	{
	    super("Item Categories",true,true,true,true);
	    db=new Database();
	    fc=this;
	    
	    jtb=createJTable();
	    jtb.setRowHeight(40);
	    jtb.setRowMargin(30);
	    Dimension d1=new Dimension(10,10);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(255,0,0));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD,20));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,0));
		header.setFont(new Font(Font.SERIF,Font.BOLD,24));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(255,128,128));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
	    
	    
	    btnAdd=new JButton("ADD");
	    btnAdd.setForeground(new Color(128,0,0));
		//btnAdd.setBackground(new Color(192,192,192));
		btnAdd.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnAdd.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			FrmAddEditItemCategory it=new FrmAddEditItemCategory(true,"",fc,fma);
	   		    jdp.add(it);
	   		    jdp.setComponentZOrder(it,0);
	   		    jdp.setComponentZOrder(fc,1);
	   		
	   		}
	   	});
	    
	    btnEdit=new JButton("EDIT");
	    btnEdit.setForeground(new Color(128,0,0));
		//btnEdit.setBackground(new Color(192,192,192));
		btnEdit.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnEdit.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fc,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	int s1=(Integer)jtb.getValueAt(row,0);
					FrmAddEditItemCategory it=new FrmAddEditItemCategory(false,"select * from categories where categoryid="+s1+"",fc,fma);
					jdp.add(it);
					jdp.setComponentZOrder(it,0);
					jdp.setComponentZOrder(fc,1);		
	   		    }
	   		}
	   	});
	    
	    btnDelete=new JButton("DELETE");
	    btnDelete.setForeground(new Color(128,0,0));
		//btnDelete.setBackground(new Color(192,192,192));
		btnDelete.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	     btnDelete.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			try {
	   				int row=jtb.getSelectedRow();
	   				if(row==-1)
	   					JOptionPane.showInternalMessageDialog(fc,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   				else
	   				{
	   					int itemid=(Integer)jtb.getValueAt(row,0);
	   					rst=db.getData("select image from categories where categoryid="+itemid);
	   					rst.next();
	   					File f=new 	File("itemimages/"+rst.getString(1));
	   					f.delete();
	   					db.close();
	   					
	   					rst=db.getData("select image from subcategories where categoryid="+itemid);
	   					while(rst.next()) {
	   						f=new 	File("itemimages/"+rst.getString(1));	
	   						f.delete();
	   					}
	   					db.close();
	   					rst=db.getData("select image from item where categoryid="+itemid);
	   					while(rst.next()) {
	   						f=new 	File("itemimages/"+rst.getString(1));	
	   						f.delete();
	   					}
	   					db.close();
	   					
	   					db.setData("delete from item where categoryid="+itemid+"");
	   					db.setData("delete from subcategories where categoryid="+itemid+"");
	   					db.setData("delete from categories where categoryid="+itemid+"");
	   					reload();
	   				}
	   			}
	   			catch(SQLException e) {
	   				e.printStackTrace();
	   			}
	   		}
	   	});
	    
	    jp=new JPanel();
	    jp.setLayout(new GridLayout(1,3));
	    jp.add(btnAdd);
	    jp.add(btnEdit);
	    jp.add(btnDelete);
	    add(jp,BorderLayout.SOUTH);	
	    
	    
	    setSize(700,450);
		setLocation(350,30);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public JTable createJTable()
	{
		try
		{
			rst=db.getData("select count(*) from categories");
			rst.next();
		    int n=rst.getInt(1);
		    if(n!=0)
		    {
		    	data=new Object[n][4];
		    	rst=db.getData("select * from categories");
		    	int i=0;
		        while(rst.next())
		        {
		        	data[i][0]=rst.getInt(1);
		        	data[i][1]=rst.getString(2);
		        	data[i][2]=rst.getString(3);
		        	data[i][3]=rst.getString(4);
		        	
		        	i++;
		        }	
		        db.close();
		    }
		    else
		    {
		    	data=new Object[0][4];
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
	    jtb.setRowHeight(40);
	    jtb.setRowMargin(30);
	    Dimension d1=new Dimension(10,10);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(255,0,0));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD,20));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,0));
		header.setFont(new Font(Font.SERIF,Font.BOLD,24));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(255,128,128));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
		revalidate();
	}

}