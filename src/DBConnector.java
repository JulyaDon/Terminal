import java.sql.*;

/**
 * класс, который отвечает за соединение значений с датчика с базой данных
 */
public class DBConnector {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/Octopus";
    private static final String user = "mysql";
    private static final String password = "mysql";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement stmt;
    //private static ResultSet rs;

    /**
     * передаем значения с датчиков в базу
     */
    public void DBInsert(Sensor sensor){
        String query = "INSERT INTO Sensors"
        +"(sensor_type, sensor_number, sensor_data, terminal_address) VALUES"
                +"(?, ?, ?, ?)";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.prepareStatement(query);
            stmt.setInt(1,sensor.GetType());
            stmt.setInt(2,sensor.GetNumber());
            stmt.setInt(3,sensor.GetData());
            stmt.setInt(4,1);

            // executing SELECT query
            stmt.executeUpdate(query);

            // executing SELECT query
            //rs = stmt.executeQuery(query);
/*
            while (rs.next()) {
                String count = rs.getString(1);
                System.out.println("Total number of books in the table : " + count);
            }
*/
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void DBDelete(){

    }

    public void DBUpdate(){

    }

    public void DBSelect(){

    }
}


