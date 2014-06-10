package database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import models.user.*;

public class DataBase {	
	/**
	 * @param name
	 * @param pwd
	 * @return 0 if the password is correct, 2 if wrong password, and 1 if such user doesn't exist in database.
	 */
	public static int checkUserPwd (String name, String pwd) {
		int status = 0;
		UserInfo user = sqlGetUserInfo(name);
		if (user != null) {
			if (user.getPassword().equals(pwd)) 
				status = 0;
			else {
				status = 2;
			}
		}
		else 
			status = 1;
		return status;
	}

	
	public static boolean sqlInsertUserInfo (UserInfo user) {	
		DbOperation dbconn = null;
		String sqlString = "insert into User (name, pwd, email, homeCity) values (?, ?, ?, ?)";
		try {
			dbconn = new DbOperation(sqlString);
			dbconn.setString(1, user.getName());
			dbconn.setString(2, user.getPassword());
			dbconn.setString(3, user.getEmail());
			dbconn.setString(4, user.getHomeCity());
			dbconn.excuteUpdate();
			dbconn.closeConn();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			try  {
				dbconn.closeConn();
			}catch (Exception eb) {
				// TODO: handle exception
			}
			return false;
		}
	}
	
	public static UserInfo sqlGetUserInfo(String name) {
		UserInfo user = null;
		DbOperation dbconn = null;
		String sqlString = "select * from User where name = ?";
		try {
			dbconn = new DbOperation(sqlString);
			
			dbconn.setString(1, name);
			dbconn.excuteQuery();
			if (dbconn.next()) {
				user = new UserInfo(dbconn.getString(1), dbconn.getString(2), dbconn.getString(3), dbconn.getString(4));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try  {
				dbconn.closeConn();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return user;
	}
	
	public static boolean sqlUpdateUserInfo(UserInfo user) {
		DbOperation dbconn = null;
		String name = user.getName();
		String pwd = user.getPassword();
		String email = user.getEmail();
		String homeCity = user.getHomeCity();
		String sqlString = "update User set pwd = ?, email = ?, homeCity = ?  where name = ?";
		try {
			dbconn = new DbOperation(sqlString);
			
			dbconn.setString(1, pwd);
			dbconn.setString(2, email);
			dbconn.setString(3, homeCity);
			dbconn.setString(4, name);
			dbconn.excuteUpdate();
			dbconn.closeConn();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			try  {
				dbconn.closeConn();
			}catch (Exception eb) {
				// TODO: handle exception
			}
			return false;
		}		
	}
	

	
	public static Iterator<UserInfo> sqlGetUserInfoList () {
		DbOperation dbconn = null;
		ArrayList<UserInfo> arrayList = new ArrayList<UserInfo>();
		String sqlString = "select * from User";
		UserInfo user = null;
		try {
			dbconn = new DbOperation(sqlString);
			dbconn.excuteQuery();
			while (dbconn.next()) {
				user = new UserInfo(dbconn.getString(1), dbconn.getString(2), dbconn.getString(3), dbconn.getString(4));
				arrayList.add(user);
			}
			dbconn.closeConn();
			return arrayList.iterator();
		}catch (Exception e) {
			e.printStackTrace();
			try  {
				dbconn.closeConn();
			}catch (Exception eb) {
				// TODO: handle exception
			}
			return null;
		}
	}
	
	/*
	 * just for test
	 */
	public static void main (String[] args) {
		UserInfo userInfo = new UserInfo("l6yk", "b", "c", "d");
		sqlInsertUserInfo(userInfo);
		ConnectionPool.getInstance().closePool();
	}
}

