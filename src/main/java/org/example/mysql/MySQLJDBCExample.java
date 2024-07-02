package org.example.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class MySQLJDBCExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to the database");

                // 创建表
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS testtable (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(255), " +
                            "age INT, " +
                            "city VARCHAR(255))");
                }

                Random random = new Random();
                String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};

                for (int i = 0; i < 100000; i++) {
                    int operation = random.nextInt(3); // 0: 插入, 1: 查询, 2: 删除
                    String name = names[random.nextInt(names.length)];
                    int age = random.nextInt(100);
                    String city = "City" + random.nextInt(100);

                    switch (operation) {
                        case 0:
                            // 插入
                            String insertSQL = "INSERT INTO testtable (name, age, city) VALUES (?, ?, ?)";
                            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                                pstmt.setString(1, name);
                                pstmt.setInt(2, age);
                                pstmt.setString(3, city);
                                pstmt.executeUpdate();
                                System.out.println("Inserted: " + name);
                            }
                            break;
                        case 1:
                            // 查询
                            String querySQL = "SELECT * FROM testtable WHERE name = ?";
                            try (PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
                                pstmt.setString(1, name);
                                ResultSet rs = pstmt.executeQuery();
                                if (rs.next()) {
                                    System.out.println("Found: " + rs.getString("name"));
                                } else {
                                    System.out.println("No record found with name: " + name);
                                }
                            }
                            break;
                        case 2:
                            // 删除
                            String deleteSQL = "DELETE FROM testtable WHERE name = ?";
                            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                                pstmt.setString(1, name);
                                int rowsAffected = pstmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Deleted: " + name);
                                } else {
                                    System.out.println("No record found to delete with name: " + name);
                                }
                            }
                            break;
                    }
                }
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
