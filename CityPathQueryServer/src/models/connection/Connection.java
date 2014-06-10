package models.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import models.user.UserInfo;

/**
 * 描述：与客户端之间的连接类，用于与客户端建立连接，并用该连接发送所有需要向客户端发送的请求，
 *      在收到回复后返回
 * @author 董剑峰
 *
 */
public class Connection {
	
	private Socket socket;
	
	public static String serverAddr = "127.0.0.1";
	public static int serverPort = 7898;
	
	public Connection() {
		
		//建立连接
		try {
			socket = new Socket(serverAddr, serverPort);
		} catch (UnknownHostException e) {
			System.out.println("unknowhost");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("wrong socket");
			e.printStackTrace();
		}
		
		//获取输入输出流
//		try {
//			in = new DataInputStream(socket.getInputStream());
//			out =new DataOutputStream(socket.getOutputStream());
//		}
//		catch (IOException e){
//			System.out.println("can't get stream");
//			e.printStackTrace();
//		}
	}

	
	//分割线
	
	public int recvTypeFromClient(){
		DataInputStream in;
		try {
			in =new DataInputStream(socket.getInputStream());
			return in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public Object recvObjectFromClient(Class<?> type){
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Object recvObj = in.readObject();
			while (!type.isInstance(recvObj)) {
				System.out.println("warning recv data wrong!!!!!");
				recvObj = in.readObject();
			}
			return in;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	public int sendObjecctToClient(Serializable obj){
		ObjectOutputStream out;
		try {
			out =new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(obj);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return -1;
	}
}
