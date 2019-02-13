import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.jdesktop.swingx.prompt.PromptSupport;
class FrmLogin extends JFrame{
    JTextField jtfLoginid;
    JPasswordField jpfPassword;
    JButton jbtnLogin, jbtnSignup;
    JRadioButton jrbAdmin, jrbEmployee;
    JLabel jlblLoginid, jlblPassword, jlblLoginAs;
    ButtonGroup bg;
    Image imageLogin, newimgLogin, imageSignup, newimgSignup;
    ImageIcon iiLogin, iiSignup;
    JPanel jpnlButtonGroup;
    ResultSet rst;
    String strLoginid,strPassword;
    FrmLogin(){
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	jtfLoginid=new JTextField();
    	PromptSupport.setPrompt("Enter User ID", jtfLoginid);
    	PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, jtfLoginid);
    	PromptSupport.setFontStyle(Font.BOLD, jtfLoginid);
    	
    	jpfPassword=new JPasswordField();
    	PromptSupport.setPrompt("Enter Password", jpfPassword);
    	PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, jpfPassword);
    	PromptSupport.setFontStyle(Font.BOLD, jpfPassword);
    	
    	iiLogin = new ImageIcon("images/LoginBtnIcon.png");
		imageLogin = iiLogin.getImage(); 
		newimgLogin = imageLogin.getScaledInstance(30, 30,  Image.SCALE_SMOOTH); 
		iiLogin = new ImageIcon(newimgLogin);
		jbtnLogin=new JButton("Login",iiLogin);
		jbtnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jbtnLogin.setToolTipText("Login to Enter");
		jbtnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				strLoginid=jtfLoginid.getText();
				strPassword=new String(jpfPassword.getPassword());
				try{
					Database db=new Database();
					rst=db.getData("select * from users where userid='"+strLoginid+"' and password='"+strPassword+"'");
					if(rst.next()){
						String strUserType=rst.getString("usertype");
						db.close();
						
						if(strUserType.equalsIgnoreCase("admin")){
	                   		if(jrbAdmin.isSelected()){
	                   			dispose();	
	                   			new FrmMainAdmin(strLoginid);
	                   		}
	                   		else{
	                   			JOptionPane.showMessageDialog(null,"Invalid user type");
	                   			return;
	                   		}
                       	}
                       	else{
	                   		if(jrbEmployee.isSelected()){
	                   			dispose();	
                       			new FrmMainUser(strLoginid);
	                   		}
	                   		else{
	                   			JOptionPane.showInternalMessageDialog(null,"Invalid user type");
	                   			return;
	                   		}
                       	}
					}
					else{
						db.close();
						JOptionPane.showMessageDialog(null,"Invalid LoginID Or Password");
					}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		
    	iiSignup = new ImageIcon("images/signupIcon.png");
		imageSignup = iiSignup.getImage(); 
		newimgSignup = imageSignup.getScaledInstance(30, 30,  Image.SCALE_SMOOTH); 
		iiSignup = new ImageIcon(newimgSignup);
    	jbtnSignup=new JButton("Signup",iiSignup);
    	jbtnSignup.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	jbtnSignup.setToolTipText("Create New User ID");
    	jbtnSignup.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			dispose();
    			new FrmSignUp();
    		}
    	});
    
    	jrbAdmin=new JRadioButton("Admin",true);
    	jrbAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	jrbEmployee=new JRadioButton("Employee");
    	jrbEmployee.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	bg=new ButtonGroup();
		bg.add(jrbAdmin);bg.add(jrbEmployee);
	
	  	jlblLoginid=new JLabel("Login ID",SwingConstants.CENTER);
    	jlblPassword=new JLabel("Password",SwingConstants.CENTER);
    	jlblLoginAs=new JLabel("LoginAs",SwingConstants.CENTER);
    	
    	jpnlButtonGroup=new JPanel();
    	jpnlButtonGroup.setLayout(new FlowLayout());
    	jpnlButtonGroup.add(jrbAdmin);jpnlButtonGroup.add(jrbEmployee);
    	
    	add(jlblLoginAs);	add(jpnlButtonGroup);
    	add(jlblLoginid);	add(jtfLoginid);
    	add(jlblPassword);	add(jpfPassword);
    	add(jbtnLogin);		add(jbtnSignup);
    	
    	setLayout(new GridLayout(4,2));
    	setResizable(false);
    	setTitle("Login Frame");
    	setIconImage(new ImageIcon("images/LoginIcon.png").getImage());
		setVisible(true);
		setSize(400,200);
		pack();
		setLocationRelativeTo(null);
    }
}