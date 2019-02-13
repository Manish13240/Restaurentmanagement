import java.awt.*;
import java.sql.*;
public class CommonMethods {
	static void createAllTables() {
		try {
			Database db=new Database();
			db.setData("create table if not exists users(username varchar(20),userid varchar(20) primary key,password varchar(20) not null,usertype varchar(10))");
			db.setData("create table if not exists tables(tableid int auto_increment primary key,tablename varchar(20) not null,description varchar(50))");
			db.setData("create table if not exists customers(customerid int auto_increment primary key,customername varchar(20) not null,contact varchar(15) unique key,address varchar(50),email varchar(50))");
			db.setData("create table if not exists categories(categoryid int auto_increment primary key,categoryname varchar(20) not null,description varchar(50),image varchar(20))");
			db.setData("create table if not exists subcategories(subcategoryid int auto_increment primary key,subcategoryname varchar(20) not null,categoryid int,description varchar(50),image varchar(20))");
			db.setData("create table if not exists item(itemid int auto_increment primary key,itemname varchar(20) not null,subcategoryid int,categoryid int,description varchar(50),price int,image varchar(20))");
			db.setData("create table if not exists ordermaster(orderid int auto_increment primary key,tableid int unique key,customerid int,ordertype varchar(10))");
			db.setData("create table if not exists orderdetail(orderid int,itemid int,itemname varchar(20),price int,qty int,value int)");
			db.setData("create table if not exists bill(billno int auto_increment primary key,customerid int,date date,totalamount decimal(10,6),discountpercent float,grossamount decimal(10,6),resttaxpercent float,servicetaxpercent float,netamount decimal(10,6))");
			db.setData("create table if not exists settings(discountper numeric(5,2), servicechargesper numeric(5,2), gstper numeric(5,2))");
			ResultSet rst=db.getData("select count(*) from settings");
			rst.next();
			int cnt=rst.getInt(1);
			if(cnt==0)
				db.setData("insert into settings values(5.0,10.0,5.0)");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	static Point getCenterPoint(Dimension frame){
		Dimension desktop=Toolkit.getDefaultToolkit().getScreenSize();	
		return new Point((desktop.width-frame.width)/2,(desktop.height-frame.height)/2);
	}
	static int getCategoryId(String categoryName){
		int categoryId=0;
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select categoryid from categories where categoryname='"+categoryName+"'");
			rst.next();
			categoryId=rst.getInt(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return categoryId;
	}
	static String getCategoryName(int categoryId){
		String categoryName="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select categoryname from categories where categoryid="+categoryId);
			rst.next();
			categoryName=rst.getString(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return categoryName;
	}
	static int getSubCategoryId(String subCategoryName){
		int subCategoryId=0;
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select subcategoryid from subcategories where subcategoryname='"+subCategoryName+"'");
			rst.next();
			subCategoryId=rst.getInt(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return subCategoryId;
	}
	static String getSubCategoryName(int subCategoryId){
		String subCategoryName="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select subcategoryname from subcategories where subcategoryid="+subCategoryId);
			rst.next();
			subCategoryName=rst.getString(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return subCategoryName;
	}
	static int getCategoryIdFromSubCategoryName(String subCategoryName){
		int categoryId=0;
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select categoryid from subcategories where subcategoryname='"+subCategoryName+"'");
			rst.next();
			categoryId=rst.getInt(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return categoryId;
	}
	static String getCategoryImage(int categoryId){
		String categoryImage="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select image from categories where categoryid="+categoryId);
			rst.next();
			categoryImage=rst.getString(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return categoryImage;
	}
	static String getImageFromName(String name){
		String image="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select count(*) from categories where categoryname='"+name+"'");
			rst.next();
			int i=rst.getInt(1);
			rst.close();
			db.close();
			if(i!=0){
			    rst=db.getData("select image from categories where categoryname='"+name+"'");
			    rst.next();
				image=rst.getString(1);
				rst.close();
				db.close();
				return image;
			}
			rst=db.getData("select count(*) from subcategories where subcategoryname='"+name+"'");
			rst.next();
		    i=rst.getInt(1);
		    rst.close();
			db.close();
		    
			if(i!=0){
			    rst=db.getData("select image from subcategories where subcategoryname='"+name+"'");
			    rst.next();
				image=rst.getString(1);
				rst.close();
				db.close();
				return image;
			}
			else {
				rst=db.getData("select image from item where itemname='"+name+"'");
			    rst.next();
				image=rst.getString(1);
				rst.close();
				db.close();
				return image;	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return image;	
	}
	static int getTableId(String tableName){
		int Id=0;
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select tableid from tables where tablename='"+tableName+"'");
			rst.next();
			Id=rst.getInt(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return Id;
	}
	static String getItemName(int itemId){
		String iName="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select itemname from item where itemid="+itemId);
			rst.next();
			iName=rst.getString(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return iName;
	}
	static String getCustomerName(int cId){
		String cName="";
		try{
			Database db=new Database();
			ResultSet rst=db.getData("select customername from customers where customerid="+cId);
			rst.next();
			cName=rst.getString(1);
			db.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cName;
	}
}
