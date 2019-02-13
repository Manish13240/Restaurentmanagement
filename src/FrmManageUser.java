import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.sql.*;

 class FrmManageUsers extends JInternalFrame
{
	static FrmManageUsers fmu;
	JTable jtb;
	JTableHeader header;
	JScrollPane jsp;
	Object data[][];
	String head[]={"UserName","UserId","Password","UserType"};
	JButton btnAdd,btnEdit,btnSearch,btnDelete;
	JPanel jp;
	Database db;
	ResultSet rst;
	String query1="select count(*) from users";
	String query2="select * from users";
	FrmManageUsers(JDesktopPane jdp)
	{
		super("Manage Users",true,true,false,false);
		db=new Database();
		fmu=this;
	   
		jtb=createJTable(query1,query2);
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
	   		
	   		    FrmAddEditUsers faeu=new FrmAddEditUsers(true,"",fmu);
	   		    jdp.add(faeu);
	   		    jdp.setComponentZOrder(faeu,0);
	   		 
	   		   jdp.setComponentZOrder(fmu,1);
	   		   reload(query1,query2);
	   		   
	   		   
	   		}
	   	});
	    	
	    btnEdit=new JButton("Edit");
	    btnEdit.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fmu,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	String userid=(String)jtb.getValueAt(row,1);
	   		    	FrmAddEditUsers faeu=new FrmAddEditUsers(false,"select * from users where userid='"+userid+"'",fmu);
	   		    	jdp.add(faeu);
	   		    	jdp.setComponentZOrder(faeu,0);
	   		    	jdp.setComponentZOrder(fmu,1);
	   		    	
	   		    }
	   		}
	   	});
	    	
	    btnSearch=new JButton("Search");
	    btnSearch.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	         FrmSearchUsers fsu=new FrmSearchUsers(fmu);
	   		    jdp.add(fsu);
	   		    jdp.setComponentZOrder(fsu,0);
	   		 
	   		   jdp.setComponentZOrder(fmu,1);
	   		   reload(query1,query2);
	   		   
	   		}
	   		
	  	});
	    
	    btnDelete=new JButton("Delete");
	    btnDelete.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fmu,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	String user=(String)jtb.getValueAt(row,0);
	   		    	db.setData("delete from users where username='"+user+"'");
	   		    	reload(query1,query2);
	   		    }
	   		}
	   	});
	    	
	    jp=new JPanel();
	    jp.setLayout(new GridLayout(1,4));
	    jp.add(btnAdd);
	    jp.add(btnEdit);
	    jp.add(btnSearch);
	    jp.add(btnDelete);
	    add(jp,BorderLayout.SOUTH);	
			
		setSize(500,300);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public JTable createJTable(String qry1,String qry2)
	{
		try
		{
			rst=db.getData(qry1);
			rst.next();
		    int n=rst.getInt(1);
		    if(n!=0)
		    {
		    	data=new Object[n][4];
		    	rst=db.getData(qry2);
		    	int i=0;
		        while(rst.next())
		        {
		        	data[i][0]=rst.getString(1);
		        	data[i][1]=rst.getString(2);
		        	data[i][2]=rst.getString(3);
		        	data[i][3]=rst.getString(4);
		        	
		        	i++;
		        }	
		        db.close();
		    }
		    else
		    {
		    	data=new Object[0][3];
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
	
	public void reload(String qry1,String qry2)
	{
		remove(jsp);
		jtb=createJTable(qry1,qry2);
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
