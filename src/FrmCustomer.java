import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.sql.*;

public class FrmCustomer extends JInternalFrame
{
	static FrmCustomer fcs;
	JTable jtb;
	JTableHeader header;
	JScrollPane jsp;
	Object data[][];
	String head[]={"Customer Id","Customer Name","Contact no.","Address","Email"};
	JButton btnAdd,btnEdit,btnSearch,btnDelete,btnShowAll;
	JPanel jp;
	Database db;
	ResultSet rst;
	String query="";
	FrmCustomer(JDesktopPane jdp)
	{
		super("Customer",true,true,true,true);
		db=new Database();
		fcs=this;
	  
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
	    btnAdd.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			FrmAddEditCustomer customer=new FrmAddEditCustomer(true,"",fcs);
	   		    jdp.add(customer);
	   		    jdp.setComponentZOrder(customer,0);
	   		    jdp.setComponentZOrder(fcs,1);
	   		}
	   	});
	    	
	    btnEdit=new JButton("Edit");
	    btnEdit.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fcs,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	String s1=(String)jtb.getValueAt(row,0);
					FrmAddEditCustomer fae=new FrmAddEditCustomer(false,"select * from customers where customerid='"+s1+"'",fcs);
					jdp.add(fae);
					jdp.setComponentZOrder(fae,0);
					jdp.setComponentZOrder(fcs,1);		
	   		    }
	   		}
	   	});
	    	
	    btnSearch=new JButton("Search");
	    btnSearch.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			query="";
	   			FrmSearchCustomer fc=new FrmSearchCustomer(fcs);
	            jdp.add(fc);
	            jdp.setComponentZOrder(fc,0);
	   		    jdp.setComponentZOrder(fcs,1);
	  		}
	  	});
	    
	    btnDelete=new JButton("Delete");
	    btnDelete.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fcs,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	String customerid=(String)jtb.getValueAt(row,0);
	   		    	db.setData("delete from Customers where customerid="+customerid);
	   		    	reload();
	   		    }
	   		}
	   	});
	    btnShowAll=new JButton("Show All");
	    btnShowAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
	   			   query="";
	   			   reload();
			}
		});
	    jp=new JPanel();
	    jp.setLayout(new GridLayout(1,4));
	    jp.add(btnAdd);
	    jp.add(btnEdit);
	    jp.add(btnSearch);
	    jp.add(btnDelete);
	    jp.add(btnShowAll);
	    add(jp,BorderLayout.SOUTH);	
			
		setSize(500,400);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public JTable createJTable()
	{
		try
		{
			if(query==null || "".equals(query))
				rst=db.getData("select count(*) from customers");
			else
				rst=db.getData("select count(*)"+query);
			rst.next();
		    int n=rst.getInt(1);
		    if(n!=0)
		    {
		    	data=new Object[n][5];
		    	if(query==null || "".equals(query))
		    		rst=db.getData("select * from customers");
		    	else
		    		rst=db.getData("select * " + query);
		    	int i=0;
		        while(rst.next())
		        {
		        	data[i][0]=rst.getString(1);
		        	data[i][1]=rst.getString(2);
		        	data[i][2]=rst.getString(3);
		        	data[i][3]=rst.getString(4);
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
	    	jtb=new JTable(data,head);
	    	return jtb;	
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