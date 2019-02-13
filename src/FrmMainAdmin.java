import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.*;
import java.util.*;
class FrmMainAdmin extends JFrame{
	static FrmMainAdmin fma;
	private static String user;
    JPanel panel_Top,panel_Bottom;
	JMenuBar jmb;
	JMenu jmFile,jmMasters,jmTransaction,jmReport;
	JMenuItem jmiLogOff,jmiGlobalSetting,jmiChangePassword,jmiManageUsers,jmireport,jmiExit;
	JMenuItem jmiItemCategory,jmiItemSubCategory,jmiItem,jmiCustomers,jmiTableMaster;
	JMenuItem jmiSales;
	JMenuItem jmiCompleteMenu;
	JToolBar jtb;
	JButton btnLogOff,btnGlobalSetting,btnChangePassword,btnManageUsers,btnExit;
	JButton btnFood,btnCustomers,btnTableMaster;
	JButton btnSales;
	JButton btnCompleteMenu;
    Insets ins;
	ImageIcon ii1;
	Image im1;
	Image im2;
	JButton jb1,jb2;
	Dimension d1,d2;
	Toolkit tk;
	JDesktopPane jdp;
	JToolBar toolbar;
	FrmMainAdmin(String user){
		jdp=new JDesktopPane();
		fma=this;
		
		panel_Top=new JPanel();
		panel_Top.setLayout(new BorderLayout());
     	panel_Top.add(createJToolBar(),BorderLayout.PAGE_START);
		
		panel_Bottom=new JPanel();
		panel_Bottom.setPreferredSize(new Dimension(10,35));
		panel_Bottom.setBackground(Color.gray);
		
		jdp=new JDesktopPane();
		jdp.setBackground(Color.WHITE);
     	jdp.setAutoscrolls(true);
     	jdp.setBorder(BorderFactory.createLoweredBevelBorder());
     	jdp.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		getContentPane().add(panel_Top,BorderLayout.PAGE_START);
     	getContentPane().add(jdp,BorderLayout.CENTER);
     	getContentPane().add(panel_Bottom,BorderLayout.PAGE_END);
		this.user=user;

		//create menubar
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
        jmiLogOff.addActionListener(new ActionListener(){
	   		public void actionPerformed(ActionEvent ae){
	   			dispose();
	   			new FrmLogin();
	    	}
	    });
        //menuitem action
        jmiGlobalSetting=new JMenuItem("GlobalSetting",new ImageIcon("images/glblsett.png"));
        jmiGlobalSetting.setMnemonic('G');
        jmiGlobalSetting.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        jmiGlobalSetting.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		jdp.add(new FrmGlobalSettings());
        	}
        });
        
        ii1=new ImageIcon("images/chngpass.png");
        im1=ii1.getImage();
        im2=im1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
        ii1=new ImageIcon(im2);
        jmiChangePassword=new JMenuItem("ChangePassword",ii1);
        jmiChangePassword.setMnemonic('C');
        jmiChangePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        jmiChangePassword.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		jdp.add(new FrmChangePassword(user));
        	}
        });
        
        ii1=new ImageIcon("images/manageusr.png");
        im1=ii1.getImage();
        im2=im1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
        ii1=new ImageIcon(im2);
        jmiManageUsers=new JMenuItem("ManageUsers",ii1);
        jmiManageUsers.setMnemonic('U');
        jmiManageUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
        jmiManageUsers.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmManageUsers(jdp));
        	}
	    });
        
        ii1=new ImageIcon("images/manageusr.png");
        im1=ii1.getImage();
        im2=im1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
        ii1=new ImageIcon(im2);
        jmireport=new JMenuItem("Report",ii1);
        jmireport.setMnemonic('R');
        jmireport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        jmireport.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmReport());
        	}
	    });
        
        jmiExit=new JMenuItem("Exit",new ImageIcon("images/exit.png"));
        jmiExit.setMnemonic('E');
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
	 	jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		    	if(answer==JOptionPane.YES_OPTION)
		     		System.exit(1);
			}
		});
	 
       ii1=new ImageIcon("images/food.jpg");
       im1=ii1.getImage();
       im2=im1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
       ii1=new ImageIcon(im2);
       jmiItemCategory=new JMenuItem("Item Category",ii1); 
       jmiItemCategory.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
    		   jdp.add(new FrmItemCategory(jdp,fma));
    	   }
       });
	  
       jmiItemSubCategory=new JMenuItem("Item SubCategory",ii1);
       jmiItemSubCategory.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
    		   jdp.add(new FrmItemSubCategory(jdp));
    	   }
       });
	 
       jmiItem=new JMenuItem("Items",ii1);
       jmiItem.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
    		   jdp.add(new FrmItem(jdp));
    	   }
       });
	 
       jmiCustomers=new JMenuItem("Customers",new ImageIcon("images/customer.png"));
       jmiCustomers.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmCustomer(jdp));
    	   }
	   });
	 
       ii1=new ImageIcon("images/table.jpg");
       im1=ii1.getImage();
       im2=im1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
       ii1=new ImageIcon(im2);
	   	 
       jmiTableMaster=new JMenuItem("TableMaster",ii1);
       jmiTableMaster.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmTable(jdp));
    	   }
	   });
     
       jmiSales=new JMenuItem("Sales",new ImageIcon("images/sales.png"));
       jmiSales.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmWelcome(jdp));
    	   }
	   });
     
       jmiCompleteMenu=new JMenuItem("Show Menu");
       jmiCompleteMenu.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmMenu());
    	   }
	   });
     
       //addmenu items
       jmFile.add(jmiLogOff);jmFile.add(jmiGlobalSetting);
       jmFile.add(jmiChangePassword);
       jmFile.add(jmiManageUsers);
       jmFile.add(jmireport);
       jmFile.add(jmiExit);
    
       jmMasters.add(jmiItemCategory);
       jmMasters.add(jmiItemSubCategory);
       jmMasters.add(jmiItem);
       jmMasters.add(jmiCustomers);
       jmMasters.add(jmiTableMaster);
    
       jmTransaction.add(jmiSales);
    
       jmReport.add(jmiCompleteMenu);
     
       //add menu
       jmb.add(jmFile) ;jmb.add(jmMasters);
       jmb.add(jmTransaction);jmb.add(jmReport);
    
       //addmenu bar
       setJMenuBar(jmb);
    
       setBackground(Color.RED);
       setSize(400,400);
    
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setTitle("Welcome:"+ user);
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       setVisible(true);
	}
    JToolBar createJToolBar(){
    	jtb=new JToolBar(JToolBar.HORIZONTAL);
    	ins=new Insets(5,5,0,0);
    	jtb.setMargin(ins);
    	jtb.setBackground(Color.LIGHT_GRAY);
		
    	//add button on toolbar
		btnLogOff=new JButton(new ImageIcon("images/LOGGOFF.png"));
		btnLogOff.setToolTipText("Click for Logout");
		btnLogOff.addActionListener(new ActionListener(){
	   		public void actionPerformed(ActionEvent ae){
	   			dispose();
	   			new FrmLogin();
	    	}
	    });
		
		btnGlobalSetting=new JButton(new ImageIcon("images/glblsett.png"));
		btnGlobalSetting.setToolTipText("Click for GlobalSettings");
		btnGlobalSetting.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		jdp.add(new FrmGlobalSettings());
        	}
        });
		
		ii1=new ImageIcon("images/chngpass.png");
		im1=ii1.getImage();
		im2=im1.getScaledInstance(15,16,Image.SCALE_SMOOTH);
		ii1=new ImageIcon(im2);  
		btnChangePassword=new JButton(ii1);
		btnChangePassword.setToolTipText("Click for ChangePassword");
		btnChangePassword.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		jdp.add(new FrmChangePassword(user));
        	}
        });
		
		btnManageUsers=new JButton(new ImageIcon("images/manageusr.png"));
		btnManageUsers.setToolTipText("Click for MangeUsers");
		btnManageUsers.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmManageUsers(jdp));
        	}
	    });
        
		
		btnExit=new JButton(new ImageIcon("images/exit.png"));
		btnExit.setToolTipText("Click for Exit");
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		    	if(answer==JOptionPane.YES_OPTION)
		     		System.exit(1);
			}
		});
		
		
		ii1=new ImageIcon("images/food.jpg");
		im1=ii1.getImage();
		im2=im1.getScaledInstance(15,16,Image.SCALE_SMOOTH);
		ii1=new ImageIcon(im2);
		btnFood=new JButton(ii1);
		btnCustomers=new JButton(new ImageIcon("images/customer.png"));
		
		ii1=new ImageIcon("images/table.jpg");
		im1=ii1.getImage();
		im2=im1.getScaledInstance(15,16,Image.SCALE_SMOOTH);
		ii1=new ImageIcon(im2);
		btnTableMaster=new JButton(ii1);
		
		btnSales=new JButton(new ImageIcon("images/sales.png"));
		
		jtb.add(btnLogOff); jtb.add(btnGlobalSetting);jtb.add(btnChangePassword);
		jtb.add(btnManageUsers); jtb.add(btnExit);jtb.addSeparator();
		jtb.add(btnFood);
		jtb.add(btnCustomers); jtb.add(btnTableMaster);jtb.addSeparator();
		jtb.add(btnSales);jtb.addSeparator();
		return jtb;
	}
}