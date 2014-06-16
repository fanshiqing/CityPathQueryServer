package models.database;

/**
 * 这个.java文件�?��修改编码格式为UTF-8
 */

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {

    private Vector<Connection> pool;

    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/project";
 //   private String url = "jdbc:mysql://172.26.102.85:3306/project";
    
    private String username = "root";

    private String password = "fsq0924";

    /**
     * 连接池的大小�?
     */
    private int poolSize = 10;

    private static ConnectionPool instance = null;

    /**
     * 私有的构造方法，禁止外部创建本类的对象，要想获得本类的对象，通过getIstance方法�?
     * 使用了设计模式中的Singleton�?
     */
    private ConnectionPool() {
        init();
    }

    /**
     * 连接池初始化方法，读取属性文件的内容 建立连接池中的初始连�?
     */
    private void init() {
        pool = new Vector<Connection>(poolSize);
    //    readConfig();
        addConnection();
    }

    /**
     * 返回连接到连接池�?
     */
    public synchronized void release(Connection conn) {
    	System.out.println("release connection successfully" +  "\n");
        pool.add(conn);
    }

    /**
     * 关闭连接池中的所有数据库连接
     */
    public synchronized void closePool() {
      //  for (int i = 0; i < pool.size(); i++) {
    	int i = 0;
        while (!pool.isEmpty()) {
            try {
            	((Connection) pool.get(0)).close();

           //     ((Connection) pool.get(0)).close();
            	if (((Connection) pool.get(0)).isClosed())
                System.out.println("close connection successfully " + i++ + "\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.removeElementAt(0);
        }
    }

    /**
     * 返回当前连接池的�?个对�?
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * 返回连接池中的一个数据库连接
     */
    public synchronized Connection getConnection() { 
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

    /**
     * 在连接池中创建初始设置的的数据库连接
     */
    private void addConnection() {
        Connection conn = null;
        for (int i = 0; i < poolSize; i++) {

            try {
                Class.forName(driverClassName);
                conn = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(conn);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 读取设置连接池的属�?�文�?
     */
    
    private void readConfig() {
        try {
            String path = System.getProperty("user.dir") + "\\DBpool.properties";
            FileInputStream is = new FileInputStream(path);
            Properties props = new Properties();
            props.load(is);
            this.driverClassName = props.getProperty("driverClassName");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.url = props.getProperty("url");
            this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("读取属�?�文件出�?. ");        
        }
    }
   
}
