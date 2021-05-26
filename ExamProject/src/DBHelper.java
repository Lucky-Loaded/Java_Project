import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;


//import javax.swing.JComboBox;

public class DBHelper {
	static Connection conn=null;
	static PreparedStatement state = null;
	static ResultSet result = null;
	static MyModel model = null;
	static ArrayList<String> position =new ArrayList<String>();
	
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
	static void fillComboCity(JComboBox<String> combo,String variable,String tableName) {
		conn = getConnection();
		String sql = "select distinct "+ variable+" from " + tableName;
		try {
			state = conn.prepareStatement(sql);
			
			result = state.executeQuery(); //combo.setModel(aModel);
			combo.removeAllItems();
			while(result.next()) {
				
				String item = result.getObject(1).toString() ;
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
static MyModel getAllDataTable() {
	conn = getConnection();
	String sql = "SELECT w.id,  w.name, w.birthday,w.email,w.city,p.name AS POSITION "
			+ "FROM worker w join position P "
			+ "on w.id_position = p.ID";
	try {
		state = conn.prepareStatement(sql);
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
static MyModel getAllDataTableContract() {
	conn = getConnection();
	String sql = "SELECT C.id,  C.type, C.date_time,W.name AS Worker_Name "
			+ "FROM contract C join worker W "
			+ "on C.id_worker = w.ID";
	try {
		state = conn.prepareStatement(sql);
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
static MyModel getAllDataTableInfo() {
	conn = getConnection();
	String sql ="SELECT C.ID ,C.TYPE, C.DATE_TIME, W.NAME, W.CITY, P.NAME FROM CONTRACT C, WORKER W, POSITION P \r\n"
				+"WHERE C.ID_WORKER = W.ID AND W.ID_POSITION = P.ID ";
	try {
		state = conn.prepareStatement(sql);
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
        File file = new File("C:\\Users\\bystr\\git\\Java_Project\\ExamProject\\src\\config.txt");
        Scanner sc = new Scanner(file);
        String connString = "",username = "",password = "";
        while(sc.hasNextLine()) {
            connString = sc.nextLine().trim();
            username = sc.nextLine().trim();
            password = sc.nextLine().trim();
        }

        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(connString, username, password);
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return conn;
}
}//end method
