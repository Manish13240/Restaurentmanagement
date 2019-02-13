import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.*;

class FrmMain extends JFrame
{
	JMenuBar jmb;
	JMenu jmFile,jmMasters,jmTransaction,jmReport;
	JMenuItem jmiLogOff,jmiGlobalSetting,jmiChangePassword,jmiManageUsers,jmiExit;
	JMenuItem jmiFood,jmiCustomers,jmiTableMaster;
	JMenuItem jmiSales;
	JMenuItem jmiCompleteMenu;
	JMenuItem jmiFoodPrice;
	JToolBar jtb;
	JButton btnLogOff,btnGlobalSetting,btnChangePassword,btnManageUsers,btnExit;
	JButton btnFood,btnCustomers,btnTableMaster;
	JButton btnCompleteMenu,btnFoodPrice;
Insets ins;
	
	
	JButton jb1,jb2;
	Dimension d1,d2;
	Toolkit tk;
	FrmMain()
	{
    JToolBar toolbar = new JToolBar();
    toolbar.setRollover(true);
    
    //created menubar
    JMenuBar  jmb=new JMenuBar();
    
    //create menu
         jmFile=new JMenu("File");
         jmFile.setMnemonic('F');
	    
     
         jmMasters=new JMenu("Masters");
         jmMasters.setMnemonic('M');
	     
         jmTransaction=new JMenu("Transaction");
         jmTransaction.setMnemonic('T');
         
         jmReport=new JMenu("Report");
         jmReport.setMnemonic('R');
    
    //create menu itmes     
     jmiLogOff=new JMenuItem("LogOff",new ImageIcon("images/LOGGOFF.png"));
     jmiLogOff.setMnemonic('L');
	 jmiLogOff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
      jmiLogOff.addActionListener(new ActionListener()
	   {
	   	public void actionPerformed(ActionEvent ae)
	   	{
	   	
	   	new FrmLogin();
	   }
	   });
     
     jmiGlobalSetting=new JMenuItem("GlobalSetting",new ImageIcon("images/glblsett.png"));
     jmiGlobalSetting.setMnemonic('G');
	 jmiGlobalSetting.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
     
     jmiChangePassword=new JMenuItem("ChangePassword",new ImageIcon("images/chngpass.png"));
     jmiChangePassword.setMnemonic('C');
	 jmiChangePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
      
     jmiManageUsers=new JMenuItem("ManageUsers",new ImageIcon("images/manageusr.png"));
     jmiManageUsers.setMnemonic('U');
	 jmiManageUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
     
     jmiExit=new JMenuItem("Exit",new ImageIcon("images/exit.png"));
     jmiExit.setMnemonic('E');
	 jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
       
       ImageIcon ii1=new ImageIcon("images/food.jpg");
       Image im1=ii1.getImage();
	   Image	im2=im1.getScaledInstance(40,20,Image.SCALE_SMOOTH);
	   ii1=new ImageIcon(im2);
	   jmiFood=new JMenuItem("Food",ii1);
	   
	   jmiFood.addActionListener(new ActionListener()
	   {
	   	public void actionPerformed(ActionEvent ae)
	   	{
	   	
	   	//new FrmFood();
	   }
	   });
	   
	   jmiCustomers=new JMenuItem("Customers",new ImageIcon("images/customer.png"));
     
        ii1=new ImageIcon("images/table.jpg");
        im1=ii1.getImage();
	   	im2=im1.getScaledInstance(40,20,Image.SCALE_SMOOTH);
	   	 ii1=new ImageIcon(im2);
	   	 
     jmiTableMaster=new JMenuItem("TableMaster",ii1);
     
     jmiSales=new JMenuItem("Sales",new ImageIcon("images/sales.png"));
     jmiCompleteMenu=new JMenuItem("CompleteMenu");
     jmiFoodPrice=new JMenuItem("FoodPrice");
    
    //addmenu items
    jmFile.add(jmiLogOff);jmFile.add(jmiGlobalSetting);
    jmFile.add(jmiChangePassword);
    jmFile.add(jmiManageUsers);
    jmFile.add(jmiExit);
    
    jmMasters.add(jmiFood);jmMasters.add(jmiCustomers);
    jmMasters.add(jmiTableMaster);
    
    jmTransaction.add(jmiSales);
    
    jmReport.add(jmiCompleteMenu);
    jmReport.add(jmiFoodPrice);
    
    //add menu
    jmb.add(jmFile) ;jmb.add(jmMasters);
    jmb.add(jmTransaction);jmb.add(jmReport);
    
    //addmenu bar
    setJMenuBar(jmb);
    
    //addtoolbar
        jtb=new JToolBar(JToolBar.HORIZONTAL);
        ins=new Insets(5,5,0,0);
		jtb.setMargin(ins);
		jtb.setBackground(Color.LIGHT_GRAY);
		
		//add button on toolbar
		
		btnLogOff=new JButton(new ImageIcon("images/LOGGOFF.png"));
		btnLogOff.setToolTipText("Click for Logout");
		
		btnGlobalSetting=new JButton(new ImageIcon("images/glblsett.png"));
		btnGlobalSetting.setToolTipText("Click for GlobalSettings");
		
		btnChangePassword=new JButton(new ImageIcon("images/chngpass.png"));
		btnChangePassword.setToolTipText("Click for ChangePassword");
		
		btnManageUsers=new JButton(new ImageIcon("images/manageusr.png"));
		btnManageUsers.setToolTipText("Click for MangeUsers");
		
		btnExit=new JButton(new ImageIcon("images/exit.png"));
		btnExit.setToolTipText("Click for Exit");
		
		btnLogOff=new JButton(new ImageIcon("images/LOGGOFF.png"));
		btnLogOff.setToolTipText("Click for Logout");
		
		  ii1=new ImageIcon("images/food.jpg");
        im1=ii1.getImage();
	   im2=im1.getScaledInstance(40,20,Image.SCALE_SMOOTH);
	   ii1=new ImageIcon(im2);
		btnFood=new JButton(ii1);
		btnCustomers=new JButton(new ImageIcon("images/customer.png"));
		
		ii1=new ImageIcon("images/table.jpg");
        im1=ii1.getImage();
	   	im2=im1.getScaledInstance(40,20,Image.SCALE_SMOOTH);
	   	 ii1=new ImageIcon(im2);
		btnTableMaster=new JButton(ii1);
		
		jtb.add(btnLogOff);jtb.add(btnGlobalSetting);
		jtb.add(btnChangePassword);
		jtb.add(btnManageUsers);
		jtb.add(btnExit);jtb.add(btnFood);
		jtb.add(btnCustomers);jtb.add(btnTableMaster);
		
        add(jtb,BorderLayout.NORTH);
        setBackground(Color.RED);
        setSize(400,400);
    
    //add toolkit
    
    tk=Toolkit.getDefaultToolkit();
    d1=tk.getScreenSize();
    d2=getSize();
    int x=(d1.width-d2.width)/2;
    int y=(d1.height-d2.height)/2;
    setLocation(x,y);
    setTitle("mainFrame");
    setVisible(true);
    }
    
	public static void main(String args[])
	{
		new FrmMain();
	}
}