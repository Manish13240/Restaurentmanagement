import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;
import java.awt.*;

class FrmMainUser extends JFrame{
	JMenuBar jmb;
	JPanel panel_Top,panel_Bottom;
	JMenu jmFile,jmMasters,jmTransaction,jmReport;
	JMenuItem jmiLogOff,jmiChangePassword,jmiExit;
	JMenuItem jmiCustomers;
	JMenuItem jmiSales;
	JMenuItem jmiMenu;
	JToolBar jtb;
	JButton btnLogOff,btnChangePassword,btnExit;
	JButton btnCustomers,btnSales,btnMenu;
    Insets ins;
	ImageIcon ii1;
	Image im1;
	Image im2;
	JButton jb1,jb2;
	Dimension d1,d2;
	Toolkit tk;
	JDesktopPane jdp;
	JToolBar toolbar;
	private static String user;
	FrmMainUser(String user){
		jdp=new JDesktopPane();
		
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
        jmiLogOff.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
	            dispose();
	   			new FrmLogin();
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
     
	 	jmiCustomers=new JMenuItem("Customers",new ImageIcon("images/customer.png"));
	 	jmiCustomers.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmCustomer(jdp));
	 		}
	    });
	      
	 	jmiSales=new JMenuItem("Sales",new ImageIcon("images/sales.png"));
	 	jmiSales.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent ae){
	 			jdp.add(new FrmWelcome(jdp));
	 		}
	    });
	 	
	 	jmiMenu=new JMenuItem("Show Menu");
		jmiMenu.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent ae){
		 	        jdp.add(new FrmMenu());
		    }
		});
	 	
		//addmenu items
	 	jmFile.add(jmiLogOff);
	 	jmFile.add(jmiChangePassword);
	 	jmFile.add(jmiExit);
    
	 	jmMasters.add(jmiCustomers);
     
	 	jmTransaction.add(jmiSales);
    
	 	jmReport.add(jmiMenu);
     
	 	//add menu
	 	jmb.add(jmFile);jmb.add(jmMasters);
	 	jmb.add(jmTransaction);jmb.add(jmReport);
    
	 	//addmenu bar
	 	setJMenuBar(jmb);
     
	 	//add toolkit
	 	setSize(400,400);
	 	setLocationRelativeTo(null);setTitle("welcome:" +user);
	 	setVisible(true);
    }
    
     //addtoolbar
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
	
	 	btnExit=new JButton(new ImageIcon("images/exit.png"));
	 	btnExit.setToolTipText("Click for Exit");
	 	btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int answer=JOptionPane.showConfirmDialog(null,"Are You Sure To Exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		    	if(answer==JOptionPane.YES_OPTION)
		     		System.exit(1);
			}
		});
	
	 	btnCustomers=new JButton(new ImageIcon("images/customer.png"));
	 	btnCustomers.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent ae){
	 	        jdp.add(new FrmCustomer(jdp));
	 		}
	    });
		
	 	btnSales=new JButton(new ImageIcon("images/sales.png"));
	 	btnSales.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent ae){
	 			jdp.add(new FrmWelcome(jdp));
	 		}
	    });
		jtb.add(btnLogOff);jtb.add(btnChangePassword);
		jtb.add(btnExit);jtb.addSeparator();
		jtb.add(btnCustomers);jtb.addSeparator();
		jtb.add(btnSales);jtb.addSeparator();
		return jtb;
	}
}