import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public  void f() throws SQLException {
              Connection conn = null;
                       try {

                             Context ctx = new InitialContext();
                             DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/J2EE");
                             //3、通过DataSource取得一个连接
                             conn = ds.getConnection();

                             Statement stmt=conn.createStatement();
                             ResultSet rs=stmt.executeQuery("SELECT * FROM USER");
                             while(rs.next()){
                                 System.out.println(rs.getString("name"));
                             }
                         //4、操作数据库
                         } catch (NamingException e) {
                             System.out.println(e.getMessage());
                         } catch (SQLException e) {
                             e.printStackTrace();
                         } finally {
                             //5、关闭数据库，关闭的时候是将连接放回到连接池之中
                             conn.close();
                         }

    }
}
