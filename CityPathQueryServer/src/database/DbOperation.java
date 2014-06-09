package database;


import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.tools.JavaCompiler;
import javax.naming.*;


public class DbOperation {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rst;
	private ConnectionPool pool;
	
	/**
	 * Constructor, sql is used to initialize preparedStatement 
	 * @param sql
	 */
	public DbOperation(String sql) { 
		pool = ConnectionPool.getInstance();
		conn = pool.getConnection();	
		try {
			pstmt = conn.prepareStatement(sql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn() {
		pool.release(conn);
//		pool.closePool();
	}
	
	
	public void excuteQuery () {
		try {
			if (pstmt != null) 
				rst = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int excuteUpdate () {
		int status = 0;
		try {
			if (pstmt != null) 
				status = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public void setString(int i, String s) {
		try {
			if (s != null) {
				pstmt.setString(i, s);
			}
			else {
				pstmt.setNull(i, Types.CHAR);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBoolean(int i, boolean flag) {
		try {
			pstmt.setBoolean(i, flag);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInt(int i, int value) {
		try {
			pstmt.setInt(i, value);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void setLong(int i, Long value) {
		try {
			pstmt.setLong(i, value);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public void setFloat(int i, float value) {
		try {
			pstmt.setFloat(i, value);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public void setTimestamp(int i, Timestamp timestamp) {
		try {
			if (timestamp != null) {
				pstmt.setTimestamp(i, timestamp);
			}
			else {
				pstmt.setNull(i, Types.TIMESTAMP);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getBoolean(int i) throws Exception {
		return rst.getBoolean(i);
	}
	public boolean getBoolean(String s) throws Exception {
		return rst.getBoolean(s);
	}
	public int getInt (int i) throws Exception {
		return rst.getInt(i);
	}
	public int getInt (String s) throws Exception {
		return rst.getInt(s);
	}
	public long getLong (int i) throws Exception {
		return rst.getLong(i);
	}
	public long getLong (String s) throws Exception {
		return rst.getLong(s);
	}
	public String getString (int i) throws Exception {
		return rst.getString(i);
	}
	public String getString (String s) throws Exception {
		return rst.getString(s);
	}
	public Timestamp getTimeStamp (int i) throws Exception {
		return rst.getTimestamp(i);
	}
	
	public Timestamp getTimeStamp (String s) throws Exception {
		return rst.getTimestamp(s);
	}
	
	public float getFloat(int i) throws Exception {
		return rst.getFloat(i);
	}
	public boolean next(){
		try {
			return rst.next();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


}
