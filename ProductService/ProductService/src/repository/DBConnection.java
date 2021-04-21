package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  public Connection getConnection() {
    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");

      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productDB", "root", "classified23");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}