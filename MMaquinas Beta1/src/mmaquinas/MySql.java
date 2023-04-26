package mmaquinas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MySql {

	public static String url = "jdbc:mysql://localhost:3306/mmaquinas?characterEncoding=latin1&useConfigs=maxPerformance";
	public static String username = "root";
	public static String password = "";
	public static Connection connection;
	
	public static boolean isConnected() {
		return connection != null;
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void connect() {
		try {
			if (!isConnected()) {
				connection = DriverManager.getConnection(url, username, password);
				Bukkit.getConsoleSender().sendMessage("§bMySql - §aConectado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§bMySql - §cErro ao Conectar!");
		}
	}
	
	public static void disconnect() {
		try {
			if(isConnected()) {
				connection.close();
				Bukkit.getConsoleSender().sendMessage("§bMySql - §aDesconectado!");
			}
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§bMySql - §cErro ao Desconectar!");
		}
	}
	
}
