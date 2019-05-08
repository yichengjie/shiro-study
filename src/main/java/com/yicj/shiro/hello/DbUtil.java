package com.yicj.shiro.hello;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {

    public static Connection getConnection(){
        return  null ;
    }

    public static void close(Connection conn){
        if (conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
