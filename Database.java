
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private String user ="root";
    private String pass="";
    private String url ="jdbc:mysql://localhost:3307/demo";
    private Statement statement;

    public Database() throws SQLException
    {
        Connection connection= DriverManager.getConnection(url,user,pass);
        statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        System.out.println("Connection Established");
    }

    public Statement getStatement() {
        return statement;
    }

}
