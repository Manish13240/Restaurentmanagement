import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class FrmItem extends JInternalFrame
{
	static FrmItem fi;
	JTable jtb;
	JScrollPane jsp;
	Object data[][];
	String head[]={"Item Id","Item Name","SubCategory Name","Category Name","Description","Price","Image",};
	JButton btnAdd,btnEdit,btnSearch,btnDelete,btnShowAll;
	JPanel jp;
	Database db;
	ResultSet rst;
	static String query="";
	ImageIcon ii;
	Image image,newimage;
	JTableHeader header;
	
	FrmItem(JDesktopPane jdp)
	{
		super("Item",true,true,true,true);
		db=new Database();
		fi=this;
	   
	    
	    jtb=createJTable();
	    jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(128,0,255));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,0,128));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
	    
		    
	    btnAdd=new JButton("Add");
	    btnAdd.setForeground(new Color(128,0,128));
		btnAdd.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnAdd.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			FrmAddEditItem it=new FrmAddEditItem(true,"",fi);
	   		    jdp.add(it);
	   		    jdp.setComponentZOrder(it,0);
	   		    jdp.setComponentZOrder(fi,1);
	   		}
	   	});
	    	
	    btnEdit=new JButton("Edit");
	    btnEdit.setForeground(new Color(128,0,128));
		btnEdit.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnEdit.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fi,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	int id=(int)jtb.getValueAt(row,0);
					FrmAddEditItem it=new FrmAddEditItem(false,"select * from item where itemid="+id,fi);
					jdp.add(it);
					jdp.setComponentZOrder(it,0);
					jdp.setComponentZOrder(fi,1);		
	   		    }
	   		}
	   	});
	    	
	    btnSearch=new JButton("Search");
	    btnSearch.setForeground(new Color(128,0,128));
		btnSearch.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnSearch.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			query="";
	   			FrmSearchItem search=new FrmSearchItem(fi);
	            jdp.add(search);
	            jdp.setComponentZOrder(search,0);
	   		    jdp.setComponentZOrder(fi,1);
	        }
	  	});
	    
	    btnDelete=new JButton("Delete");
	    btnDelete.setForeground(new Color(128,0,128));
		btnDelete.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
	    btnDelete.addActionListener(new ActionListener()
	   	{
	   		public void actionPerformed(ActionEvent ae)
	   		{
	   			int row=jtb.getSelectedRow();
	   			if(row==-1)
	   				JOptionPane.showInternalMessageDialog(fi,"No Row Selected","Warning",JOptionPane.WARNING_MESSAGE);
	   		    else
	   		    {
	   		    	int itemid=(int)jtb.getValueAt(row,0);
	   		    	db.setData("delete from item where itemid="+itemid);
	   		    	reload();
	   		    }
	   		}
	   	});
	    btnShowAll=new JButton("Show All");
	    btnShowAll.setForeground(new Color(128,0,128));
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
			
		setSize(970,550);
		setLocation(230,30);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public JTable createJTable()
	{
		try
		{
			if(query==null || "".equals(query))
				rst=db.getData("select count(*) from item");
			else
				rst=db.getData("select count(*)"+query);
			rst.next();
		    int n=rst.getInt(1);
		    if(n!=0)
		    {
		    	data=new Object[n][7];
		    	if(query==null || "".equals(query))
		    		rst=db.getData("select * from item ");
		    	else
		    		rst=db.getData("select * " + query );
		    	int i=0;
		        while(rst.next())
		        {
		        	data[i][0]=rst.getInt(1);
		        	data[i][1]=rst.getString(2);
		        	data[i][2]=CommonMethods.getSubCategoryName(rst.getInt(3));
		        	data[i][3]=CommonMethods.getCategoryName(rst.getInt(4));
		        	data[i][4]=rst.getString(5);
		        	data[i][5]=rst.getInt(6);
		        	data[i][6]=rst.getString(7);
		        	i++;
		        }	
		        db.close();
		    }
		    else
		    {
		    	data=new Object[0][7];
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
	    jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(new Color(128,0,255));
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(64,0,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(new Color(128,0,128));
	   	jsp=new JScrollPane(jtb);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
	    add(jsp);
		revalidate();
	}

}