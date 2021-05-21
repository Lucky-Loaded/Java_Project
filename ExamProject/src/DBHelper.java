import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

//import javax.swing.JComboBox;

public class DBHelper {
	static Connection conn=null;
	static PreparedStatement state = null;
	static ResultSet result = null;
	static MyModel model = null;
	
	
	static void fillCombo(JComboBox<String> combo,String variable,String tableName) {
		conn = getConnection();
		String sql = "select id, "+ variable+" from " + tableName;
		try {
			state = conn.prepareStatement(sql);
			
			result = state.executeQuery(); //combo.setModel(aModel);
			combo.removeAllItems();
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2);
				combo.addItem(item);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
static MyModel getAllData(String tableName) {
		
		conn = getConnection();
		try {
			//String sql = "select * from " + tableName;
			state = conn.prepareStatement("select * from "+ tableName);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
public static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/C:\\Users\\bystr\\Desktop\\SIDB;AUTO_SERVER=TRUE","sa","sa");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
