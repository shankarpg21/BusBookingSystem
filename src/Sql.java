import java.sql.Connection;
import java.sql.DriverManager;
public class Sql {
   static Connection conn = null;
   public static Connection getConnection() {
      if (conn != null) return conn;
      String database = "bus_booking";
      String username="admin";
      String password="root";
      return getConnection(database,username,password);
   }
   private static Connection getConnection(String databaseName,String username,String password) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName,username,password);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return conn;
   }
}