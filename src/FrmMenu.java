import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.tree.*;
import javax.swing.event.*;
class FrmMenu extends JInternalFrame{
	ResultSet rstcat,rstsubcat,rstitem;
	Database dbcat,dbsubcat,dbitem;
	JTree tree;
	JTextArea jta;
	DefaultMutableTreeNode topmenu, category, subcategory, item;
	String iteminfo="";
	public FrmMenu() {
		super("Menu",true,true,true,true);
		
		setLayout(new GridLayout(1,2));
    	dbcat=new Database();
    	dbsubcat=new Database();
    	dbitem=new Database();
	   	jta=new JTextArea();
    	
    	DefaultMutableTreeNode topmenu = new DefaultMutableTreeNode("FOOD MENU");
    	createNodes(topmenu);
    	tree=new JTree(topmenu);
    	JScrollPane treeView = new JScrollPane(tree);
        add(treeView);
  		
  		tree.addTreeSelectionListener(new TreeSelectionListener(){
        	public void valueChanged(TreeSelectionEvent e){
    			DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
    			/* if nothing is selected */ 
        		if (node == null){
        			JOptionPane.showMessageDialog(null,"Menu not available");
        			return;
        		}

    			/* retrieve the node that was selected */ 
        		else if (node.isLeaf()){
        			try{
        				iteminfo=(String)node.getUserObject();
        				rstitem=dbitem.getData("select * from item where itemname='"+iteminfo+"'");
        				if(rstitem.next()) {
        					int price=Integer.parseInt(rstitem.getString(6));
        					jta.setText("PRICE = "+price+"\n"+rstitem.getString(5));
        				}
   	    				dbitem.close();
        			}
        			catch(SQLException e1){
        				e1.printStackTrace();
        			}		
    			}
        		/* React to the node selection. */
			}
        });
        jta.setEditable(false);
        add(new JScrollPane(jta));
              
        setSize(200,200);
        setVisible(true);
        
    }
    
    private JTree createNodes(DefaultMutableTreeNode topmenu){
    	DefaultMutableTreeNode category = null;
    	DefaultMutableTreeNode subcategory = null;
    	DefaultMutableTreeNode item = null;
    	try{
			rstcat=dbcat.getData("select count(*) from categories");
			rstcat.next();
		    int c=rstcat.getInt(1);
		    if(c!=0){
		    	rstcat=dbcat.getData("select * from categories");
		    	while(rstcat.next()){
    				category = new DefaultMutableTreeNode(rstcat.getString(2));
    				topmenu.add(category);
    				int cid=rstcat.getInt(1);
    				
    				//add subcategory
    				rstsubcat=dbsubcat.getData("select count(*) from subcategories where categoryid="+cid);
					rstsubcat.next();
		    		int s=rstsubcat.getInt(1);
		    		if(s!=0){
		    			rstsubcat=dbsubcat.getData("select * from subcategories where categoryid="+cid);
		    			while(rstsubcat.next()){
    						subcategory = new DefaultMutableTreeNode(rstsubcat.getString(2));
    						category.add(subcategory);
    						int subcatid=rstsubcat.getInt(1);
    						//add item
    						rstitem=dbitem.getData("select count(*) from item where subcategoryid="+subcatid);
							rstitem.next();
							int itm=rstitem.getInt(1);
		    				if(itm!=0){
		    					rstitem=dbitem.getData("select itemname from item where subcategoryid="+subcatid);
		    					while(rstitem.next()){
    								item = new DefaultMutableTreeNode(rstitem.getString(1));
    								subcategory.add(item);
    							}	
							}
		   					dbitem.close();
	    					//end of item
			    		}	
					}
		   			dbsubcat.close();		    
					//end of subcategory
			    }	
			}
		   	dbcat.close();		    
		}	
	   	catch(SQLException e){
	    	e.printStackTrace();
	    	System.out.println("categoryException");
	    }
    	tree = new JTree(topmenu);
    	return tree;
    }
}

    
