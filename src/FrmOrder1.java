import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmOrder1 extends JInternalFrame
{
	static FrmOrder1 o1;
	Database db;
	ResultSet rst;
	int n,row;
	JPanel jp1,jp2,jp3,jpButton;
	JButton btnBack;
	MyButton btnArray[];
	JLabel jlbTitle,jlbButton;
	//ImageIcon ii;
	//Image image,newimage;
	
	FrmOrder1(JDesktopPane jdp,FrmSales fs,int orderid)
	{
		super("What would you like to have?",false,true,false,false);
		db=new Database();
		o1=this;
		try
		{
			rst=db.getData("select count(*) from categories");
		    rst.next();
		    n=rst.getInt(1);
		    row=(int)Math.ceil((double)n/3);
		    rst.close();
		    db.close();
		
		    jp1=new JPanel();
		    jp1.setLayout(new GridLayout(row,3));
		    jp1.setBackground(Color.ORANGE);
		
		
		    btnArray=new MyButton[n];
		    rst=db.getData("select * from categories");
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
			
			    			
			    int cid=rst.getInt(1);
			    String cname=btnArray[i].getLabel();
		
			btnArray[i].addActionListener(new ActionListener()
		    { 
		        public void actionPerformed(ActionEvent ae)
			    {
			    	setVisible(false);
				    FrmOrder2 o2=new FrmOrder2(jdp,fs,o1,cid,cname,orderid);
	   		        jdp.add(o2);
	   		        jdp.setComponentZOrder(o2,0);
	   		        jdp.setComponentZOrder(o1,1);
	   		
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
			    	fs.setVisible(true);
			    	dispose();
			    	
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
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBackground(Color.ORANGE);
		setSize(900,600);
		setLocation(200,0);
		//setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
/*	public static void main(String args[])
	{
		new FrmOrder1();
	}*/
}