import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmAddEditTable extends JInternalFrame
{
	JLabel lblTableId,lblTableName,lblDescription;
	JTextField jtfTableid,jtfTableName,jtfDescripton;
	JTextArea jtaDescription;
	JButton btnSave,btnCancel;
	Database db;
	ResultSet rst;
	int id;
	FrmAddEditTable(boolean flag,String query,FrmTable ft)
	{
		super("Add/Edit Table",false,true,false,false);
		
		setLayout(new GridLayout(4,2));
		db=new Database();
		
	    lblTableId=new JLabel("Table Id");
    	add(lblTableId);
		
	    jtfTableid=new JTextField();
	    jtfTableid.setEditable(false);
     	add(jtfTableid);
		
    	lblTableName=new JLabel("Table Name");
    	add(lblTableName);
		
     	jtfTableName=new JTextField();
     	add(jtfTableName);
		
     	lblDescription=new JLabel("Description");
	    add(lblDescription);
     	
		jtaDescription=new JTextArea();
     	add(jtaDescription);
     
     	try
		{
		    if(flag)
	    	{
	    		rst=db.getData("select max(tableid) from tables");
	        	rst.next();
	        	id=rst.getInt(1)+1;
	        	jtfTableid.setText(id+"");
     		    btnSave=new JButton("Save");
     		    db.close();
	    	}
	    	else
	    	{
	    		rst=db.getData(query);
	    		rst.next();
	    		jtfTableid.setText(rst.getInt(1)+"");
	    		jtfTableName.setText(rst.getString(2));
	    		jtaDescription.setText(rst.getString(3));
	    		btnSave=new JButton("Update");
	    		db.close();
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
	   			int tableid=Integer.parseInt(jtfTableid.getText());
	   			String tablename=jtfTableName.getText();
	   			String description=jtaDescription.getText();
	   			if(flag)	
	   				db.setData("insert into tables values("+tableid+",'"+tablename+"','"+description+"')");
	   			else
	   				db.setData("update tables set tablename='"+tablename+"',description='"+description+"' where tableid="+tableid);	    				

	   			dispose();
	   			ft.reload();
	   		}
	   	});
	   	add(btnSave);
		
	   	btnCancel=new JButton("Cancel");
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
} 
