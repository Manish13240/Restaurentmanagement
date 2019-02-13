import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class FrmMainFrame extends JFrame{
	JMenuBar mbar;
	JMenu file;
	JMenuItem f1,f2;
	JDesktopPane jdp;
	JToolBar toolbar;
	JPanel statusbar;
	JButton b1,b2;
	static JLabel l1;
	static ChildFrame1 cf1;
	static ChildFrame2 cf2;
	FrmMainFrame(){
		//Desktop Pane
		jdp=new MyJDesktopPane();
		//toolbar
		toolbar=new JToolBar();
		b1=new JButton("Frame 1");
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				showFrame1();
			}	
		});

		b2=new JButton("Frame 2");
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				showFrame2();
			}	
		});
		toolbar.add(b1);
		toolbar.addSeparator();
		toolbar.add(b2);
		
		//statusbar
		statusbar=new JPanel();
		statusbar.setLayout(new GridLayout(1,1));
		statusbar.setBackground(Color.LIGHT_GRAY);
		l1=new JLabel(".");
		statusbar.add(l1);		
		
		//menubar
		f1=new JMenuItem("Frame 1");
		f1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				showFrame1();
			}	
		});

		f2=new JMenuItem("Frame 2");
		f2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				showFrame2();
			}	
		});
		
		file=new JMenu("File");
		file.add(f1);
		file.add(f2);

		mbar=new JMenuBar();
		mbar.add(file);
		
		//frame
		add(toolbar,BorderLayout.NORTH);
		add(jdp,BorderLayout.CENTER);
		add(statusbar,BorderLayout.SOUTH);
		setJMenuBar(mbar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	void showFrame1()
	{	l1.setText("Frame 1 started");
		if(cf1==null){
					cf1=new ChildFrame1();
					jdp.add(cf1);
				}
				if(cf2!=null){	
					jdp.setComponentZOrder(cf1,0);//top
					jdp.setComponentZOrder(cf2,1);//bottom
				}
	}
	void showFrame2()
	{	l1.setText("Frame 2 started");
				if(cf2==null){
					cf2=new ChildFrame2();
					jdp.add(cf2);
				}
				if(cf1!=null){
					jdp.setComponentZOrder(cf2,0);//top
					jdp.setComponentZOrder(cf1,1);//bottom
				}		
	}
	public static void main(String args[])
	{
		new FrmMainFrame();
	}
}
class ChildFrame1 extends JInternalFrame{
	JLabel l1;
	JTextField tf1;
	ChildFrame1(){
		super("Frame 1",true,true,true,true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameClosed(InternalFrameEvent we){
				FrmMainFrame.cf1=null;
				FrmMainFrame.l1.setText(".");
			}
		});
		l1=new JLabel("Enter name");
		tf1=new JTextField(20);
		setLayout(new FlowLayout());
		setBackground(Color.cyan);
		add(l1);
		add(tf1);
		setSize(200,200);
		setVisible(true);
	}
}
class ChildFrame2 extends JInternalFrame{
	JLabel l1;
	JTextField tf1;
	ChildFrame2(){
		super("Frame 2",true,true,true,true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameClosed(InternalFrameEvent we){
				FrmMainFrame.cf2=null;
				FrmMainFrame.l1.setText(".");
			}
		});
		l1=new JLabel("Enter name");
		tf1=new JTextField(20);
		setLayout(new FlowLayout());
		setBackground(Color.yellow);
		add(l1);
		add(tf1);
		setSize(200,200);
		setVisible(true);
	}
}
class MyJDesktopPane extends JDesktopPane{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon ii=new ImageIcon("images/background.jpg");
		Dimension d=getSize();
		g.drawImage(ii.getImage(),0,0,d.width,d.height,null);
	}	
}




