package com.pathwheel.jdbc;

import java.sql.*;

import com.pathwheel.dao.UserDAO;
import com.pathwheel.exception.AuthenticationFailureException;
import com.pathwheel.exception.UserNotFoundException;
import com.pathwheel.model.User;

public class JdbcUserDAO extends JdbcDataAccessObject implements UserDAO {
	
	public JdbcUserDAO(JdbcDataAccessObjectListener listener) {
		super.listener = listener;
	}

	@Override
	public User register(User user, String secret){
		Connection conn = null;
		try
		{
			conn = PostgreSql.getConnection();
			String sql = "INSERT INTO pathwheel.user (full_name, email, user_name, secret) VALUES(?,?,?,?) returning id, TO_CHAR(registration_date,'dd/mm/yyyy hh24:mi:ss') as registration_date;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			System.out.println("jdbcUserDAO: "+sql);

			int i = 1;
			stmt.setObject(i++, user.getFullName(), Types.VARCHAR);
			stmt.setObject(i++, user.getEmail(), Types.VARCHAR);
			stmt.setObject(i++, user.getUserName(), Types.VARCHAR);
			stmt.setObject(i++, secret, Types.VARCHAR);
			log(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user.setId(rs.getLong("id"));
				user.setRegistrationDate(rs.getString("registration_date"));
			}

			stmt.close();
			return user;

		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			closeConnection(conn);
		}

	}

	@Override
	public User authenticate(String login, String secret) throws UserNotFoundException, AuthenticationFailureException {
		Connection conn = null;
		try
		{
		    conn = PostgreSql.getConnection();
		    String sql = "select id, user_name from pathwheel.user where exclusion_date is null and (user_name = ? or email = ?)";
		    PreparedStatement stmt = conn.prepareStatement(sql);

			System.out.println("jdbcUserDAO: "+sql);

		    int i = 1;
		    stmt.setObject(i++, login, java.sql.Types.VARCHAR);
		    stmt.setObject(i++, login, java.sql.Types.VARCHAR);		    
		    log(stmt.toString());


		    ResultSet rs = stmt.executeQuery();
			System.out.println("jdbcUserDAO2 rs");


		    if (!rs.isBeforeFirst()){
			    throw new UserNotFoundException();
			}
		    
		    sql = "select id, user_name, full_name, email, registration_date from pathwheel.user where exclusion_date is null and (user_name = ? or email = ?) and secret = ?";
		    stmt = conn.prepareStatement(sql);
		    i = 1;
		    stmt.setObject(i++, login, java.sql.Types.VARCHAR);
		    stmt.setObject(i++, login, java.sql.Types.VARCHAR);
		    stmt.setObject(i++, secret, java.sql.Types.VARCHAR);
		    log(stmt.toString());
		    rs = stmt.executeQuery();
		    
		    if (!rs.isBeforeFirst())    
			    throw new AuthenticationFailureException();
		    
		    User user = new User();
		    while (rs.next()) {
				user = User.parse(rs);
			}
		    
		    stmt.execute();
	        stmt.close();
	        return user;	        
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
		   closeConnection(conn);
		}
	}

}
