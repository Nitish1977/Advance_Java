import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) throws Exception{

        String url ="jdbc:mysql://localhost:3306/scott";
        String username = "root";
        String password = "tiger";

        String sql = "Select ename from emp where deptno = 10 and job like 'clerk' and comm is null";

        Connection con = DriverManager.getConnection(url,username, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        String name = rs.getString(1);
        System.out.println(name);
        con.close();




    }

}
