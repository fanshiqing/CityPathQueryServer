package models.user;

import java.io.Serializable;

//
//



/**
 * ����������ඨ�����û���Ϣ�����ݽṹ<br>
 * userID��name�ڴ���ʱȷ�����޷��޸ģ�����ṹ�ṩget��set����
 * @author ������
 *
 */
public class UserInfo implements Serializable {
	private int ID;
	private String name;
	private String password;
	private String email;
	private String homeCity;
	
	
	
	/**
	 * ��С��ʼ�������������û���Ϣʱ��������������Ϣ
	 * @param userID
	 * @param name
	 * @param password
	 */
	public UserInfo(int userID,String name,String password){
		
		this.email = "";
		this.homeCity = "";
	}
	
	
	/**
	 * һ���ʼ������
	 * @param userID
	 * @param name
	 * @param password
	 * @param email ��û��ֵʱ��������ַ���""
	 * @param homeCity ��û��ֵʱ��������ַ���""
	 */
	public UserInfo(String name,String password,String email,String homeCity){
		this.name = name;
		this.password = password;
		this.email = email;
		this.homeCity = homeCity;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
}
