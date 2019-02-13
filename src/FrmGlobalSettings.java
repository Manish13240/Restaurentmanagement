import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FrmGlobalSettings extends JInternalFrame 
{ 
    JLabel Discbill,gst,servtax;
    JTextField tf1,tf2,tf3;
    JButton btnUpdate;
    JPanel p1,p2;
    Database db;
    ResultSet rst;
    float discount,servicetax,gsttax;
	FrmGlobalSettings()
	{
		super("Global settings",true,true,false,false);
		try {
			db=new Database();
			rst=db.getData("select * from settings");
			rst.next();
			discount=rst.getFloat(1);
			servicetax=rst.getFloat(2);
			gsttax=rst.getFloat(3);
			db.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		Discbill=new JLabel("Discount In Bill %");
		gst=new JLabel("GST %");
		servtax=new JLabel("Service Tax %");
		tf1=new JTextField(discount+"");
		tf2=new JTextField(servicetax+"");
		tf3=new JTextField(gsttax+"");
				
		p1=new JPanel();
		p2=new JPanel();
		p1.setLayout(new GridLayout(3,2,10,10));
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10));
		p1.add(Discbill);p1.add(tf1);
		p1.add(gst);p1.add(tf2);
		p1.add(servtax);p1.add(tf3);
		add(p1);
		
		btnUpdate=new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db=new Database();
				db.setData("update settings set discountper='"+tf1.getText()+"',servicechargesper='"+tf2.getText()+"',gstper='"+tf3.getText()+"' ");
				db.close();
				JOptionPane.showMessageDialog(FrmGlobalSettings.this,"New Record update Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		p2.setBorder(BorderFactory.createEmptyBorder(20, 20,20,20));
		p2.add(btnUpdate);
		add(p2,BorderLayout.SOUTH);
		p1.setBackground(Color.cyan);
		p2.setBackground(Color.cyan);
		
		setSize(400,400);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);	
		
	}

}