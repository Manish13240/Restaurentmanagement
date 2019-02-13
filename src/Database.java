import java.sql.*;
import java.util.Properties;
import java.io.*;
public class Database {
	private Connection conn;
	private Statement stmt;
	private ResultSet rst;
	private int cnt;
	public void connect() throws ClassNotFoundException,SQLException{
		try {
			Properties p1=new Properties();
			FileInputStream fis=new FileInputStream("db/db.txt");
			p1.load(fis);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(p1.getProperty("url"),p1.getProperty("user"), p1.getProperty("password"));
			stmt=conn.createStatement();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public ResultSet getData(String query) {
		try {
			connect();
			rst=stmt.executeQuery(query);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	public int setData(String query) {
		try {
			connect();
			cnt=stmt.executeUpdate(query);
			close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public void close() {
		try {
			if(rst!=null)
				rst.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
