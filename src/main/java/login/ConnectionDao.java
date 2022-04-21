package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ConnectionDao {
	final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	final String DB_NAME = "users";
	final String DB_USER = "root";
	final String DB_PASSWORD = "meichi";

	public Connection con;

	public ConnectionDao() throws Exception {
		setConnection();
	}

	public void setConnection() throws Exception{
		try {
			String url = "jdbc:mysql://localhost/" + DB_NAME + "?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false";
			Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	protected String getStringCurrentTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
		String strTimestamp = sdf.format(timestamp);
		return strTimestamp;
	}
	public void close() throws SQLException{
		if(con != null) {
			con.close();
			con = null;
		}
	}
}
