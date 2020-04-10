package com.gwideal.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.ConnectionFactory;

/**
 * jdbc连接数据库帮助类
 * @author zhou_liang
 *
 */
public class DBHelper {
	
	private static final Logger log = LoggerFactory.getLogger(DBHelper.class);
	
	private final static ResourceBundle res = ResourceBundle.getBundle("jdbc");
	
	public static  Connection getConn(){
		Connection conn=null;
		try{
			Class.forName(res.getString("jdbc.driverClassName"));
			conn=DriverManager.getConnection(res.getString("jdbc.url"),res.getString("jdbc.username"),res.getString("jdbc.password"));
		}catch(Exception e){
			log.error("打开数据库失败", e);
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	public static void insertErrorLog(String zjhm,String method,String info,Connection conn,PreparedStatement ps){
		StringBuffer sql = new StringBuffer("insert into error_log ");
		sql.append("(pid,cred_number,error_method,error_info,error_time) values(?,?,?,?,?)");  
		try {  
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql.toString());  
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, zjhm);
			ps.setString(3, method);
			ps.setString(4, info);
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		    ps.executeUpdate();  
		    conn.commit();  
		} catch (Exception e1) { 
		    e1.printStackTrace();  
		    log.error("身份证"+zjhm+"insertErrorLog数据添加出错："+e1.getMessage());  
		} finally {
			DBHelper.closeAll(null, ps, null);
		} 
	}
	
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close(); rs=null;
			} catch (SQLException e) {
				log.error("closeAll", e);
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();pstmt=null;
			} catch (SQLException e) {
				log.error("closeAll", e);
			}
		}
		if(conn!=null){
			try {
				conn.close(); conn=null;
			} catch (SQLException e) {
				log.error("closeAll", e);
			}
		}
	}
	
	/**
     * 获取组织机构编码长度的类型
     *
     * @return 组织机构编码长度的类型
     */
    public static String getOrgCodeLengthType() {
        return res.getString("orgCodeLengthType");
    }

    public static String getHttp(String url,String method){
    	BufferedReader br = null;
    	try {
    		StringBuffer msg = new StringBuffer();
    		URL http = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)http.openConnection();//或HttpURLConnection connect = (HttpURLConnection) connection.openConnection();
			conn.setRequestMethod(method);// 提交模式
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("connection", "keep-alive");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String inputLine = null;
			while((inputLine = br.readLine()) != null){
				msg.append(inputLine);
			}
			return msg.toString();
		} catch (Exception e) {
			log.error("getHttp："+ e.getMessage());
			return null;
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public static com.rabbitmq.client.Connection getMqCon(){
    	com.rabbitmq.client.Connection conn = null;
    	try {
    		ConnectionFactory factory = new ConnectionFactory(); 
    		factory.setHost(res.getString("mq.host")); 
    		factory.setUsername(res.getString("mq.username"));
    		factory.setPassword(res.getString("mq.password"));
    		factory.setPort(Integer.valueOf(res.getString("mq.port")));
    		conn = factory.newConnection();
		} catch (Exception e) {
			log.error("创建失败rabbitmq连接失败。。。"+e.getMessage());
			System.out.println(e.getMessage());
		}
    	return conn;
    }
    
}
