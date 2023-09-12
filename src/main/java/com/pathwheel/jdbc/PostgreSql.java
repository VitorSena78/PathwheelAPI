package com.pathwheel.jdbc;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class PostgreSql {


	private static PGPoolingDataSource source;
	
	public static synchronized void init(String dataSourceName, String serverName, String databaseName, String user, String password, int initialConns, int maxConns) {
		if(source == null) {
			source = new PGPoolingDataSource();
			source.setDataSourceName(dataSourceName);
			source.setServerName(serverName);
			source.setDatabaseName(databaseName);
			source.setUser(user);
			source.setPassword(password);
			source.setInitialConnections(initialConns);
			source.setMaxConnections(maxConns);
		}
	}

	public static synchronized Connection getConnection() throws SQLException{
		try {
			Connection conexao = PostgreSql.conectar();
			if (conexao != null) {
				System.out.println("banco de dados conectado com sucesso!");
				return conexao;
			} else {
				System.out.println("conexão falhou!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static synchronized PGPoolingDataSource getSource() {
		return source;
	}


	private static synchronized Connection conectar() throws SQLException {

		//String url="jdbc:postgresql://localhost/pathwheelapi_Dbtest22";
		//String username="pathwheelapi_postgres";
		//String password="2E]i)]LUf]k5zv&";
		String url="jdbc:postgresql://localhost:5432/Dbtest22";
		String username="postgres";
		String password="postgres";
		return DriverManager.getConnection(url, username, password);
		//return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Dbtest21", "postgres", "postgres");
		//return source.getConnection();
	}
}
