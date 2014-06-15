package models.query;

import java.util.Date;

/**
 * 描述：评价
 * @author 董剑峰
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
	 * 从服务器端接收到的Advice数据构造函数
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
