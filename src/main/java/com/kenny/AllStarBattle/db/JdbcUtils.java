package com.kenny.AllStarBattle.db;

import java.sql.*;
import java.util.List;

public class JdbcUtils {
    //自己数据库的用户名
    private static final String USER_NAME = "root";

    /**
     * Notation：
     * add your password in following line
     */
    //自己数据库的密码
    private static final String PASSWORD = "HUSkenny6587&";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Notation：
     * modify following URL
     */

    //自己的URL（更改“?allow...”之前的为数据库名称即可）
    private static final String URL = "jdbc:mysql://localhost:3306/cs1004?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf-8";


    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public JdbcUtils() {
        try {
            Class.forName(DRIVER);
            System.out.println("DB connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库的连接   obtain connection to database
     *
     * @return Connection 数据库连接对象  object of DB connection
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Integer count(String countSql, List<String> params) throws SQLException {
        pstmt = connection.prepareStatement(countSql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        int total = 0;
        if (resultSet.next()) {
            total = resultSet.getInt(1);
        }
        return total;
    }

    public int insert(String sql, List<String> params) throws SQLException {
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
        }
        return pstmt.executeUpdate();
    }

}
