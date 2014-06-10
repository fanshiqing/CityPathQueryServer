package models.connection;

import database.DataBase;
import models.user.UserInfo;

public class HandlerThread implements Runnable {
	
	Connection connection;
	public HandlerThread (Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int type = 0;
		while (type >= 0) {
			type = connection.recvTypeFromClient();
			
			
			if (type == ConnType.TYPE_LOGIN) {
				String name = (String) connection.recvObjectFromClient(String.class);
				String passWord = (String) connection.recvObjectFromClient(String.class);
				UserInfo userInfo = null;
				
				Boolean result;
				
				//
				//��ѯ���ݿ⣬ͨ��name��ȡһ��UserInfo
				
				userInfo = DataBase.sqlGetUserInfo(name);
				
				
				//����ƥ��
				if (userInfo == null) {
					userInfo = new UserInfo("not_exists","","","");
				}
				
				connection.sendObjecctToClient(userInfo);
			}
			
			
		}
	}
}
