package com.yicj.shiro.hello;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class DbUtil {
	
	private static Properties loadJdbcProperties() throws IOException {
		InputStream inStream = null ;
		try {
			inStream = DbUtil.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			Properties prop = new Properties() ;
			prop.load(inStream); 
			return prop ;
		} finally {
			if(inStream!=null) {
				inStream.close(); 
			}
		}
	}

    public static Connection getConnection(){
    	try {
    		Properties prop = DbUtil.loadJdbcProperties();
        	String className = prop.getProperty("jdbc.driver") ;
        	String url = prop.getProperty("jdbc.url") ;
        	String user = prop.getProperty("jdbc.user") ;
        	String password = prop.getProperty("jdbc.password") ;
        	Class.forName(className) ;
        	Connection conn = DriverManager.getConnection(url, user, password) ;
            return  conn ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
    }
    
    public static void close(Connection conn, Statement stat, ResultSet rs){
    	close(rs);
    	close(stat);
    	close(conn);
    }
    

    private static void close(Connection conn){
        if (conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void close(Statement stat){
        if (stat !=null){
            try {
            	stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void close(ResultSet rs){
        if (rs !=null){
            try {
            	rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
