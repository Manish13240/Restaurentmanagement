import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

//item(itemid int auto_increment primary key,itemname varchar(20) not null,subcategoryid int,
// categoryid int,description varchar(50),price int,image varchar(20))");

class FrmDetail extends JInternalFrame
{
	JLabel jlbId,jlbName,jlbPrice,jlbQuantity,jlbDes;
	JTextField JtfId,JtfName,JtfPrice,JtfDes,JtfQuantity;
	
	JButton JbtAdd,JbtBack;
	ImageIcon ii;
	Image image,newimage;
	int p,v,q;
	String s1;
	
	Database db;
	ResultSet rst;
	FrmDetail(JDesktopPane jdp,FrmSales fs,FrmOrder1 o1,FrmOrder2 o2,FrmOrder3 o3,int Itemid,int orderid)
	{
		super("YOU HAVE SELECTED...",false,true,false,false);
		setLayout(new GridLayout(6,2));
		setBackground(new Color(255,13,134));
		
		jlbId=new JLabel("Item Id");
		jlbId.setHorizontalAlignment(JLabel.CENTER);
		jlbId.setForeground(new Color(255,255,255));
		jlbId.setFont(new Font(Font.SERIF,Font.BOLD,23));
		
		jlbName=new JLabel("Item Name");
		jlbName.setHorizontalAlignment(JLabel.CENTER);
		jlbName.setForeground(new Color(255,255,255));
		jlbName.setFont(new Font(Font.SERIF,Font.BOLD,23));
		
		jlbPrice=new JLabel("Price");
		jlbPrice.setHorizontalAlignment(JLabel.CENTER);
		jlbPrice.setForeground(new Color(255,255,255));
		jlbPrice.setFont(new Font(Font.SERIF,Font.BOLD,23));
		
		jlbQuantity=new JLabel("Enter the Quantity:");
		jlbQuantity.setHorizontalAlignment(JLabel.CENTER);
		jlbQuantity.setForeground(new Color(0,0,0));
		jlbQuantity.setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		jlbDes=new JLabel("Description");
		jlbDes.setHorizontalAlignment(JLabel.CENTER);
		jlbDes.setForeground(new Color(255,255,255));
		jlbDes.setFont(new Font(Font.SERIF,Font.BOLD,23));
		
		
		JtfId=new JTextField(""+Itemid);
		JtfId.setEditable(false);
		
		try
		{
			db=new Database();
			rst=db.getData("select * from item where itemid="+Itemid);
			rst.next();
			
			s1=rst.getString(2);
			JtfName=new JTextField(s1);
			JtfName.setEditable(false);
			
			p=rst.getInt(6);
	    	JtfPrice=new JTextField(""+p);
	    	JtfPrice.setEditable(false);
	    	
	    	JtfQuantity=new JTextField("1");
	    	JtfQuantity.setEditable(true);
	    	
			JtfDes=new JTextField(rst.getString(5));
	        JtfDes.setEditable(false);
	        
	        rst.close();
			db.close();
		}
		catch(SQLException e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
		
		
		JtfId.setHorizontalAlignment(JTextField.CENTER);
		JtfId.setForeground(new Color(255,0,128));
		JtfId.setBackground(new Color(192,192,192));
		JtfId.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		
		JtfName.setHorizontalAlignment(JTextField.CENTER);
		JtfName.setForeground(new Color(255,0,128));
		JtfName.setBackground(new Color(192,192,192));
		JtfName.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		
		JtfPrice.setHorizontalAlignment(JTextField.CENTER);
		JtfPrice.setForeground(new Color(255,0,128));
		JtfPrice.setBackground(new Color(192,192,192));
		JtfPrice.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		
		JtfQuantity.setHorizontalAlignment(JTextField.CENTER);
		JtfQuantity.setForeground(new Color(255,0,128));
		JtfQuantity.setBackground(Color.white);
		JtfQuantity.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		
		
		JtfDes.setHorizontalAlignment(JTextField.CENTER);
		JtfDes.setForeground(new Color(255,0,128));
		JtfDes.setBackground(new Color(192,192,192));
		JtfDes.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		
		
				
		JbtAdd=new JButton("Add");
		JbtAdd.setForeground(Color.blue);
		JbtAdd.setBackground(new Color(255,255,255));
		JbtAdd.setFont(new Font(Font.SERIF,Font.ITALIC,25));
		ii=new ImageIcon("images/LoginBtnIcon.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		JbtAdd.setIcon(ii);
		
        JbtBack=new JButton("Cancel");
        JbtBack.setForeground(Color.red);
		JbtBack.setBackground(new Color(255,255,255));
		JbtBack.setFont(new Font(Font.SERIF,Font.ITALIC,25));
		ii=new ImageIcon("images/Cancel.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		JbtBack.setIcon(ii);
			
	
        add(jlbId);add(JtfId);
        add(jlbName); add(JtfName);
        add(jlbDes);add(JtfDes);
        add(jlbPrice); add(JtfPrice);
        add(jlbQuantity);add(JtfQuantity);
        add(JbtAdd);add(JbtBack);
        
        
        
        JbtAdd.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent ae)
        	{
        		//orderdetail(orderid int,itemid int,itemname varchar(20),price int,qty int,value int)
	            try
	            {
	            	q=Integer.parseInt(JtfQuantity.getText());
			        v=p*q;
        			db.setData("insert into orderdetail values("+orderid+","+Itemid+",'"+s1+"',"+p+","+q+","+v+")");
        			fs.reload();
        			//JOptionPane.showMessageDialog(null,"ITEM ADDED Successfully!","Add Item",JOptionPane.INFORMATION_MESSAGE);
				    o2.dispose();
        		    o3.dispose();
        		    o1.setVisible(true);
				    dispose();
	            }
	            catch(NumberFormatException e)
		        {
			        JOptionPane.showMessageDialog(null,"Please enter valid quantity!");
		        }    
	                
	                
				    
        	}
        }); 
        
        
        JbtBack.addActionListener(new ActionListener()
		    { 
		        public void actionPerformed(ActionEvent ae)
			    {
			    	dispose();
			    	
			    }
		    });
        
        
        setSize(400,400);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
        setVisible(true);  
              	
	}
}









