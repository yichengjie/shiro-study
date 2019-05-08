package com.yicj.shiro.hello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {



    public UserInfo queryUserByName(String username) throws SQLException {
        Connection conn = null ;
        try{
            conn = DbUtil.getConnection() ;
            String sql = "select password from members2 where username=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                UserInfo u = new UserInfo() ;
                String password = rs.getString("password") ;
                u.setUsername(username);
                u.setPassword(password);
                return u ;
            }
        }finally {
            DbUtil.close(conn);
        }


        return null ;

    }
}
