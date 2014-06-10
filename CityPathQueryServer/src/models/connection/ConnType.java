package models.connection;

/**
 *  该接口定了各个类共享的全局常量
 *  每个使用这些常量的类必须实现这个接口
 *  @author FanShiqing
*/
public interface ConnType {
	public static int TYPE_REGISTER = 1;
	public static int TYPE_LOGIN = 2;
	public static int TYPE_LOGOUT = 3;
	public static int TYPE_QUERY = 4;
}
