import java.awt.*;
import javax.swing.*;
import java.sql.*;
class FrmFood extends JFrame
{
	JButton jbPizza,jbPasta,jbNoodles,jbSoup,jbBarger,jbDosa;
	ImageIcon ii1;
	Image im1,im2;
	Toolkit tk;
	FrmFood()
	{
		
                                    ii1=new ImageIcon("images/3.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbPizza=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                     jbPizza.setToolTipText("Pizza");
	                                 jbPizza.setBounds(10,10,100,100);
	                                 
	                                 ii1=new ImageIcon("images/11.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbSoup=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                     jbSoup.setToolTipText("Soup");
	                                 jbSoup.setBounds(40,40,100,100);
	                                 
	                                ii1=new ImageIcon("images/13.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbPasta=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                    jbPasta.setToolTipText("Pasta");
	                                jbPasta.setBounds(10,10,100,100);
	                                
	                                 ii1=new ImageIcon("images/15.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbNoodles=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                    jbNoodles.setToolTipText("Noodles");
	                                jbNoodles.setBounds(10,10,100,100);
	                                
	                                 ii1=new ImageIcon("images/4.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbBarger=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                    jbBarger.setToolTipText("Barger");
	                                jbBarger.setBounds(10,10,100,100);
	                                
	                                ii1=new ImageIcon("images/9.jpg");
		                            im1=ii1.getImage();
		                            im2=im1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
	                                ii1=new ImageIcon(im2);
	                                jbDosa=new JButton(ii1);
	                                //jbPizza.setBorderPainted(false);
	                                //jbPizza.setContentAreaFilled(false);
	                                //jbPizza.setFocusPainted(false);
                                    jbDosa.setToolTipText("Barger");
	                                jbDosa.setBounds(10,10,100,100);
	                                setSize(300,300);
	                                tk=Toolkit.getDefaultToolkit();
		                            Dimension d1=tk.getScreenSize();
		                            Dimension d2=getSize();
		                            int x=(d1.width-d2.width)/2;
		                            int y=(d1.height-d2.height)/2;
	    
	setLocation(x,y);
	add(jbPizza);add(jbSoup);add(jbPasta);add(jbNoodles);add(jbBarger);add(jbDosa);
	setLayout(new GridLayout(2,2));
	setTitle("Food");
//	setUndecorated(true);
	
	setVisible(true);	
	}
	public static void main(String args[])
	{
		new FrmFood();
	}
}