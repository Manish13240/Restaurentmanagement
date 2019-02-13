import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmOrder2 extends JInternalFrame
{
	static FrmOrder2 o2;
	Database db;
	ResultSet rst;
	int n,row;
	JPanel jp1,jp2,jp3,jpButton;
	JButton btnBack;
	MyButton btnArray[];
	JLabel jlbTitle,jlbButton;
	
	FrmOrder2(JDesktopPane jdp,FrmSales fs,FrmOrder1 o1,int cid,String cname,int orderid)
	{
		super(cname,false,true,false,false);
		db=new Database();
		o2=this;
		try
		{
			rst=db.getData("select count(*) from subcategories where categoryid="+cid+"");
		    rst.next();
		    n=rst.getInt(1);
		    row=(int)Math.ceil((double)n/3);
		    rst.close();
		    db.close();
		
		    jp1=new JPanel();
		    jp1.setLayout(new GridLayout(row,3));
		    jp1.setBackground(Color.ORANGE);
		
		    btnArray=new MyButton[n];
		    rst=db.getData("select * from subcategories where categoryid="+cid+"");
		    int i=0;
		    while(i<n)
		    {
			    rst.next();
			
			    MyButton.setImageName(rst.getString(4));
			    btnArray[i]=new MyButton();
			    btnArray[i].setLayout(new BorderLayout());
			    btnArray[i].setLabel(rst.getString(2));
		    	btnArray[i].setBackground(Color.BLACK);
		    	
		        jlbButton=new JLabel(btnArray[i].getLabel());
		        jlbButton.setForeground(Color.ORANGE);
		        jlbButton.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		
		        jpButton=new JPanel();
		        jpButton.setBackground(Color.BLACK);
		        jpButton.add(jlbButton);


		        btnArray[i].add(jpButton,BorderLayout.SOUTH);
		    		    
			    jp1.add(btnArray[i]);
			
		
			int subid=rst.getInt(1);
			String subname=btnArray[i].getLabel();
			btnArray[i].addActionListener(new ActionListener()
		    { 
		        public void actionPerformed(ActionEvent ae)
			    {
			    	setVisible(false);
				    FrmOrder3 o3=new FrmOrder3(jdp,fs,o1,o2,subid,subname,orderid);
	   		        jdp.add(o3);
	   		        jdp.setComponentZOrder(o3,0);
	   		        jdp.setComponentZOrder(fs,1);
	   		
			    }
		    });
			
			i++;
		    }
		    rst.close();
		    db.close();
		
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
		btnBack=new JButton("BACK");
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font(Font.MONOSPACED,Font.BOLD,23));
		
		btnBack.addActionListener(new ActionListener()
		    { 
		        public void actionPerformed(ActionEvent ae)
			    {
			    	dispose();
			    	o1.setVisible(true);
			    }
		    });
		
		jp2=new JPanel();
		jp2.setBackground(Color.ORANGE);
		jp2.add(btnBack);
		
		
		jlbTitle=new JLabel("ORDER NOW!");
		jlbTitle.setSize(80,30);
		jlbTitle.setForeground(Color.BLACK);
	    jlbTitle.setFont(new Font(Font.MONOSPACED,Font.BOLD,27));
		
		jp3=new JPanel();
		jp3.setBackground(Color.ORANGE);
		jp3.add(jlbTitle);
		
		
		add(jp1);
		add(jp2,BorderLayout.SOUTH);
		add(jp3,BorderLayout.NORTH);
		
		
		//setUndecorated(true);
		setBackground(Color.ORANGE);
		setSize(900,600);
		setLocation(200,0);
		//setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/*public static void main(String args[])
	{
		new FrmOrder2(1);
	}*/
}