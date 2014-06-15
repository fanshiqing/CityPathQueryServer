package models.query;

import java.util.Date;

/**
 * ����������
 * @author ������
 *
 */
public class Advice {
	private String userName;
	private int adviceID;
	private int pathUnitID;
	private String time;
	private int likedTimes;
	private AdviceContent content;
	
	
	/**
	 * �ӷ������˽��յ���Advice���ݹ��캯��
	 * @param userName
	 * @param pathUnitID
	 * @param text
	 * @param adviceID
	 * @param time
	 */
	public Advice(String userName, int pathUnitID, String text, int adviceID, String time) {
		this.userName = userName;
		this.pathUnitID = pathUnitID;
		this.content = new AdviceContent(text);
		
		this.adviceID = adviceID;
		this.time = time;
	
	}
	
	
	public Advice(String userName, int pathUnitID, String text) {
		this.userName = userName;
		this.pathUnitID = pathUnitID;
		this.content = new AdviceContent(text);
	}
	

	public String getTextContent() {
		return content.getText();
	}
	
	public AdviceContent getContent() {
		return content;
	}
	
	public String getUserName() {
		return userName;
	}
	
	
	
}
