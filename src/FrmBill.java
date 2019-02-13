import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Date;
import java.text.SimpleDateFormat;

//setData("create table if not exists bill(billno int auto_increment primary key,
 //customerid int,date date,totalamount decimal(7,2),discountper decimal(4,2),
 //grossamount decimal(7,2),restservicetax decimal(4,2),servicetaxper decimal(4,2),netamount decimal(7,2))");
		

class FrmBill extends JInternalFrame
{
	static FrmBill fb;
	Database db;
	ResultSet rst;
	Date d;
	
	int bill,sno=1;
	
	JTable jtb1;
	JScrollPane jsp;
	String head[]={"S.No.","Item","Price","Quantity","Value(Rs.)"};
	Object data[][];
	JTableHeader header;
	JLabel jlbTotal,jlbDiscount,jlbGrossTotal,jlbRest,jlbStax,jlbNetAmt,jlbBillno,jlbDate,jlbCustomer;
	JTextField jtfTotal,jtfDiscountPercent,jtfDiscountRs,jtfGrossTotal,jtfRestPercent,jtfRestRs,jtfStaxPercent,jtfStaxRs,jtfNetAmt;
	
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JButton btnPrint,btnSave,btnClose;
	float t,dis1,dis2,gt,s1,s2,r1,r2,namt;
	JPanel jptop,jpmid,jpbottom,jpmid2,jp1,jp2,jp3,p1,p2,p3,p4,p5,p6;
	ImageIcon ii;
	Image image,newimage;
	float discount,servicetax,gsttax;
	
	FrmBill(JDesktopPane jdp,FrmSales fs)
	{
		super("BILL",false,true,false,false);
		db=new Database();
		d=new Date();
		fb=this;
		try
		{
			rst=db.getData("select max(billno) from bill");
			rst.next();
			bill=rst.getInt(1) +1;
			db.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try {
			db=new Database();
			rst=db.getData("select * from settings");
			rst.next();
			discount = rst.getFloat(1);
			servicetax=rst.getFloat(2);
			gsttax=rst.getFloat(3);
			db.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		jlbBillno=new JLabel("Bill No. : "+bill);
		jlbBillno.setHorizontalAlignment(JLabel.CENTER);
		jlbBillno.setForeground(Color.white);
		jlbBillno.setFont(new Font(Font.SERIF,Font.BOLD,25));
		
		
		jlbDate=new JLabel("Date/Time: "+d);
		jlbDate.setHorizontalAlignment(JLabel.CENTER);
		jlbDate.setForeground(Color.white);
		jlbDate.setFont(new Font(Font.SERIF,Font.ITALIC,20));
		
		
		
	    
		jlbCustomer=new JLabel("CUSTOMER: "+fs.jtfCustomer.getText());
		jlbCustomer.setHorizontalAlignment(JLabel.CENTER);
		jlbCustomer.setForeground(new Color(192,192,192));
		jlbCustomer.setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		
		int n=fs.jtb.getRowCount();
		data=new Object[n][5];
		
		for(int i=0;i<n;i++)
		{
			data[i][0]=sno;
			
			for(int j=1;j<5;j++)
			{
				data[i][j]=fs.data[i][j];
			}
			sno++;
		}
		jtb1=new JTable(data,head);
		jtb1.setEnabled(false);
		
	    jtb1.setRowHeight(25);
	    jtb1.setRowMargin(10);
	    Dimension d1=new Dimension(10,10);
	    jtb1.setIntercellSpacing(d1);
	    jtb1.setGridColor(Color.black);
	    jtb1.setShowGrid(true);
	    jtb1.setForeground(new Color(0,0,255));
		jtb1.setBackground(new Color(192,192,192));
		jtb1.setFont(new Font(Font.SERIF,Font.BOLD,18));
		header=jtb1.getTableHeader();
		header.setForeground(Color.black);
		header.setBackground(new Color(128,128,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,22));
	   	jsp=new JScrollPane(jtb1);
	   	jsp.getViewport().setBackground(new Color(255,255,255));
	    
		
		jlbTotal=new JLabel("Total :");
		jlbTotal.setHorizontalAlignment(JLabel.CENTER);
		jlbTotal.setForeground(new Color(0,0,0));
		jlbTotal.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		
		jtfTotal=new JTextField(12);
		jtfTotal.setText(fs.jtfTotal.getText());
		jtfTotal.setEditable(false);
		jtfTotal.setHorizontalAlignment(JTextField.CENTER);
		jtfTotal.setForeground(new Color(0,0,0));
		jtfTotal.setBackground(new Color(0,128,255));
		jtfTotal.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		
		
		jlbDiscount=new JLabel("Discount :");
		jlbDiscount.setHorizontalAlignment(JLabel.CENTER);
		jlbDiscount.setForeground(new Color(0,0,0));
		jlbDiscount.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		jtfDiscountPercent=new JTextField(5);
		jtfDiscountPercent.setText(discount+"");
		jtfDiscountPercent.setEditable(true);
		jtfDiscountPercent.setHorizontalAlignment(JTextField.CENTER);
		jtfDiscountPercent.setForeground(new Color(0,0,0));
		jtfDiscountPercent.setBackground(new Color(128,128,255));
		jtfDiscountPercent.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		jtfDiscountRs=new JTextField(9);
		//jtfDiscountPercent.setText();
		jtfDiscountRs.setEditable(false);
		jtfDiscountRs.setHorizontalAlignment(JTextField.CENTER);
		jtfDiscountRs.setForeground(new Color(0,0,0));
		jtfDiscountRs.setBackground(new Color(0,128,255));
		jtfDiscountRs.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		
		jlbGrossTotal=new JLabel("GROSS Total:");
		jlbGrossTotal.setHorizontalAlignment(JLabel.CENTER);
		jlbGrossTotal.setForeground(new Color(0,0,0));
		jlbGrossTotal.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		jtfGrossTotal=new JTextField(12);
		//jtfGrossTotal.setText();
		jtfGrossTotal.setEditable(false);
		jtfGrossTotal.setHorizontalAlignment(JTextField.CENTER);
		jtfGrossTotal.setForeground(new Color(0,0,0));
		jtfGrossTotal.setBackground(new Color(0,128,255));
		jtfGrossTotal.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		
		jlbStax=new JLabel("Service TAX:");
		jlbStax.setHorizontalAlignment(JLabel.CENTER);
		jlbStax.setForeground(new Color(0,0,0));
		jlbStax.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		
		jtfStaxPercent=new JTextField(5);
		jtfStaxPercent.setText(gsttax+"");
		jtfStaxPercent.setEditable(false);
		jtfStaxPercent.setHorizontalAlignment(JTextField.CENTER);
		jtfStaxPercent.setForeground(new Color(0,0,0));
		jtfStaxPercent.setBackground(new Color(0,128,255));
		jtfStaxPercent.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		
		jtfStaxRs=new JTextField(9);
		//jtfStaxRs.setText();
		jtfStaxRs.setEditable(false);
		jtfStaxRs.setHorizontalAlignment(JTextField.CENTER);
		jtfStaxRs.setForeground(new Color(0,0,0));
		jtfStaxRs.setBackground(new Color(0,128,255));
		jtfStaxRs.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		jlbRest=new JLabel("Restaurant Service Tax:");
		jlbRest.setHorizontalAlignment(JLabel.CENTER);
		jlbRest.setForeground(new Color(0,0,0));
		jlbRest.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		
		jtfRestPercent=new JTextField(5);
		if(fs.otype=="Dine")
		jtfRestPercent.setText(servicetax+"");
		else
		jtfRestPercent.setText("0");
		jtfRestPercent.setEditable(false);
		jtfRestPercent.setHorizontalAlignment(JTextField.CENTER);
		jtfRestPercent.setForeground(new Color(0,0,0));
		jtfRestPercent.setBackground(new Color(0,128,255));
		jtfRestPercent.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		
		jtfRestRs=new JTextField(9);
		//jtfRestRs.setText();
		jtfRestRs.setEditable(false);
		jtfRestRs.setHorizontalAlignment(JTextField.CENTER);
		jtfRestRs.setForeground(new Color(0,0,0));
		jtfRestRs.setBackground(new Color(0,128,255));
		jtfRestRs.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		jlbNetAmt=new JLabel("Net AMOUNT:");
		jlbNetAmt.setHorizontalAlignment(JLabel.CENTER);
		jlbNetAmt.setForeground(new Color(0,0,0));
		jlbNetAmt.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		jtfNetAmt=new JTextField(12);
		//jtfNetAmt.setText();
		jtfNetAmt.setEditable(false);
		jtfNetAmt.setHorizontalAlignment(JTextField.CENTER);
		jtfNetAmt.setForeground(new Color(0,0,0));
		jtfNetAmt.setBackground(new Color(0,128,255));
		jtfNetAmt.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		
		l1=new JLabel("Rs.");
		l1.setHorizontalAlignment(JLabel.LEFT);
		l1.setForeground(new Color(0,0,0));
		l1.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l2=new JLabel("%  ");
		l2.setHorizontalAlignment(JLabel.LEFT);
		l2.setForeground(new Color(0,0,0));
		l2.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l3=new JLabel("Rs.");
		l3.setHorizontalAlignment(JLabel.LEFT);
		l3.setForeground(new Color(0,0,0));
		l3.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l4=new JLabel("Rs.");
		l4.setHorizontalAlignment(JLabel.LEFT);
		l4.setForeground(new Color(0,0,0));
		l4.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l5=new JLabel("%  ");
		l5.setHorizontalAlignment(JLabel.LEFT);
		l5.setForeground(new Color(0,0,0));
		l5.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l6=new JLabel("Rs.");
		l6.setHorizontalAlignment(JLabel.LEFT);
		l6.setForeground(new Color(0,0,0));
		l6.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l7=new JLabel("%  ");
		l7.setHorizontalAlignment(JLabel.LEFT);
		l7.setForeground(new Color(0,0,0));
		l7.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l8=new JLabel("Rs.");
		l8.setHorizontalAlignment(JLabel.LEFT);
		l8.setForeground(new Color(0,0,0));
		l8.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		l9=new JLabel("Rs.");
		l9.setHorizontalAlignment(JLabel.LEFT);
		l9.setForeground(new Color(0,0,0));
		l9.setFont(new Font(Font.DIALOG,Font.BOLD,20));
		
		
		
		btnPrint=new JButton("PRINT Bill");
		btnPrint.setForeground(new Color(0,0,128));
		btnPrint.setBackground(new Color(255,255,255));
		btnPrint.setFont(new Font(Font.SERIF,Font.BOLD,25));
		ii=new ImageIcon("images/print2.jpg");
		image=ii.getImage();
		newimage=image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnPrint.setIcon(ii);
		btnPrint.setEnabled(false);
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
	   		
			}
		});
		
		btnSave=new JButton("SAVE Bill");
		btnSave.setForeground(new Color(0,0,128));
		btnSave.setBackground(new Color(255,255,255));
		btnSave.setFont(new Font(Font.SERIF,Font.BOLD,25));
		ii=new ImageIcon("images/save.png");
		image=ii.getImage();
		newimage=image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnSave.setIcon(ii);
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					rst=db.getData("select customerid from ordermaster where orderid="+fs.orderid);
					rst.next();
					int cid=rst.getInt(1);
					db.close();
					
					SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
					String d0=sdf.format(d);
					
					db.setData("insert into bill values("+bill+","+cid+",'"+d0+"','"+t+"','"+dis1+"','"+gt+"','"+r1+"','"+s1+"','"+namt+"')");
				    db.setData("delete from orderdetail where orderid="+fs.orderid);
				    db.setData("delete from ordermaster where orderid="+fs.orderid);
				    
				    JOptionPane.showMessageDialog(null,"BILL IS SAVED Successfully!","Save Bill",JOptionPane.INFORMATION_MESSAGE);
				    
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
	   		
			}
		});
		
		
		btnClose=new JButton("CLOSE");
		btnClose.setForeground(new Color(0,0,0));
		btnClose.setBackground(new Color(255,255,255));
		btnClose.setFont(new Font(Font.DIALOG,Font.BOLD,25));
		ii=new ImageIcon("images/close.jpg");
		image=ii.getImage();
		newimage=image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
		ii=new ImageIcon(newimage);
		btnClose.setIcon(ii);
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				fs.dispose();
				dispose();
				jdp.add(new FrmWelcome(jdp));
				
	   		
			}
		});
		
		
		jtfDiscountPercent.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent fe)
			{
				calculator();
				btnSave.setEnabled(true);
				btnPrint.setEnabled(true);
			}
			
			public void focusGained(FocusEvent fe){}
		});
		
		
	
		
		
		jp1=new JPanel();
		jp1.setBackground(new Color(0,0,128));
        jp1.add(btnPrint);
        
        
        
        jp2=new JPanel();
		jp2.setBackground(new Color(0,0,128));
        jp2.add(btnSave);
        
        
        jp3=new JPanel();
		jp3.setBackground(new Color(0,0,128));
        jp3.add(btnClose);
        
		
		p1=new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p1.add(l1);
        p1.add(jtfTotal);
        
        
		p2=new JPanel();
		p2.setBackground(Color.white);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p2.add(l2);
        p2.add(jtfDiscountPercent);
        p2.add(l3);
        p2.add(jtfDiscountRs);
        
        p3=new JPanel();
		p3.setBackground(Color.white);
		p3.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p3.add(l4);
        p3.add(jtfGrossTotal);
        
        p4=new JPanel();
		p4.setBackground(Color.white);
		p4.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p4.add(l5);
        p4.add(jtfStaxPercent);
        p4.add(l6);
        p4.add(jtfStaxRs);
        
        p5=new JPanel();
		p5.setBackground(Color.white);
		p5.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p5.add(l7);
        p5.add(jtfRestPercent);
        p5.add(l8);
        p5.add(jtfRestRs);
        
        p6=new JPanel();
		p6.setBackground(Color.white);
		p6.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        p6.add(l9);
        p6.add(jtfNetAmt);
		
		jpmid2=new JPanel();
		jpmid2.setBackground(new Color(64,193,255));
		jpmid2.setLayout(new GridLayout(6,2));
		jpmid2.add(jlbTotal);       jpmid2.add(p1);
		jpmid2.add(jlbDiscount);    jpmid2.add(p2);
		jpmid2.add(jlbGrossTotal);  jpmid2.add(p3);
		jpmid2.add(jlbStax);        jpmid2.add(p4);
		jpmid2.add(jlbRest);        jpmid2.add(p5);
		jpmid2.add(jlbNetAmt);      jpmid2.add(p6);
		
		
		
		
		jptop=new JPanel();
		jptop.setBackground(new Color(0,0,64));
		jptop.setLayout(new GridLayout(2,2));
		jptop.add(jlbBillno);
		jptop.add(jlbDate);
		jptop.add(jlbCustomer);
		
		
		
		jpmid=new JPanel();
		jpmid.setBackground(Color.white);
		jpmid.setLayout(new BorderLayout());
		jpmid.add(jsp,BorderLayout.CENTER);
		jpmid.add(jpmid2,BorderLayout.SOUTH);
		
		jpbottom=new JPanel();
		jpbottom.setBackground(new Color(0,128,255));
		jpbottom.setLayout(new GridLayout(1,3));
		jpbottom.add(jp1);
		jpbottom.add(jp2);
		jpbottom.add(jp3);
		
		
		add(jptop,BorderLayout.NORTH);
		add(jpmid,BorderLayout.CENTER);
		add(jpbottom,BorderLayout.SOUTH);
		
		
		
		setSize(800,600);
		setLocation(300,0);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void calculator()
	{
		try
		{
			t=Float.parseFloat(jtfTotal.getText());
		    dis1=Float.parseFloat(jtfDiscountPercent.getText());
		    dis2=(dis1/100)* t;
		    jtfDiscountRs.setText(""+dis2);
		    
		    gt=t-dis2;
		    jtfGrossTotal.setText(""+gt);
		    
		    s1=Float.parseFloat(jtfStaxPercent.getText());
			s2=(s1/100)*gt;
			jtfStaxRs.setText(""+s2);
			
			r1=Float.parseFloat(jtfRestPercent.getText());
			r2=(r1/100)*gt;
			jtfRestRs.setText(""+r2);
			
			namt=gt+s2+r2;	
			jtfNetAmt.setText(""+namt);
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
