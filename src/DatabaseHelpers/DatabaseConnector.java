package DatabaseHelpers;
import java.sql.*;

/**
 * Created by juane on 24/10/2016.
 */
public class DatabaseConnector {
    private Connection conn;

    public DatabaseConnector(){
        conn = getConnection();
    }

    private Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.
                    getConnection("jdbc:mysql://127.0.0.1:3306/gerenciadordeambientes", "root", "abc123123");
            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;
    }

    public void ExecuteStatement(String Sql) throws SQLException{
        Connection conn = getConnection();

        PreparedStatement s = conn.prepareStatement(Sql);
        s.execute();
    }

    public ResultSet ExecuteSelectStatement(String Sql) throws SQLException{
        Connection conn = getConnection();

        PreparedStatement s = conn.prepareStatement(Sql);
        ResultSet set = s.executeQuery();

        return set;
    }

    public Object ExecuteInsertStatement(String sql) throws SQLException{
        Object id = null;
        Connection conn = getConnection();

        PreparedStatement s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int affectedRows = s.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Ocorreu um erro ao executar a criação da sala no banco de dados");
        }

        try (ResultSet generatedKeys = s.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Ocorreu um erro ao adquirir o id da sala.");
            }
        }

        CloseConnection();

        return id;
    }

    public void CloseConnection() throws SQLException{
        conn.close();
    }
}
