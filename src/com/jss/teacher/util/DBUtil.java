package com.jss.teacher.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 连接数据库
 */
public class DBUtil {
	public static Connection conn=null;
	public static PreparedStatement pstat=null;
	public static ResultSet rs=null;
    //驱动程序名
	public final static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名1
    private final static String url = "jdbc:mysql://localhost:3306/jss";
    //MySQL配置时的用户名
    private final static  String user = "root";
    //MySQL配置时的密码
    private final static String password = "123";
	static{
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static void openConn(){
		try{
			conn=DriverManager.getConnection(url, user, password);
	}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private static void close(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				conn=null;
				//静默处理
			}
		}
	}
	private static void close(PreparedStatement pstat){
		if(pstat!=null){
			try{
				pstat.close();
			}catch(SQLException e){
				pstat=null;
				//静默处理
			}
		}
	}
	private static void close(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				rs=null;
				//静默处理
			}
		}
	}
	
	public static void closeConn(){
		try{
			close(rs);
		}finally{
			try{
				close(pstat);
			}finally{
				close(conn);
			}
		}
	}
}
