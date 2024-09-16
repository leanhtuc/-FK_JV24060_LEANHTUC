package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp ConnectionDB
class ConnectionDB {
private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlythuvien"; // Thay đổi tên database nếu cần
private static final String USER = "root"; // Thay đổi username nếu cần
private static final String PASS = "123456"; // Thay đổi password nếu cần

public static Connection openConnection() {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Kết nối thất bại: " + e.getMessage());
    }
    return conn;
}

public static void closeConnection(Connection conn, CallableStatement callSt) {
    try {
        if (callSt != null) {
            callSt.close();
        }
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException e) {
        System.out.println("Đóng kết nối thất bại: " + e.getMessage());
    }
}
}
