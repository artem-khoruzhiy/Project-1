import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Артем on 18.01.2017.
 */
public class ConnToDB {
    private Connection connection;
    private Statement statement;
    private final String URL = "jdbc:mysql://localhost:3306/repeat_words";

    ConnToDB(User user){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user.getUSER(), user.getPASSWORD());
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException e){
            System.out.println("Problems with driver");
        }
        catch (SQLException e){
            System.out.println("Problems with connection or statement. Check your name and password."
                            + "\nAnd launch the program once again.");
            System.exit(-1);
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
}
