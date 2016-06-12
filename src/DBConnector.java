import java.sql.*;

/**
 * класс, который отвечает за соединение значений с датчика с базой данных
 */
public class DBConnector {
    final int SENSOR_MOISTURE = 1;
    final int SENSOR_PHOTO = 2;
    final int SENSOR_TEMPERATURE = 3;

    // JDBC URL, username and password of MySQL server
    private static String DB_ADDRESS = "jdbc:mysql://localhost:3306/DBdemo";
    private static String DB_USER = "mysql";
    private static String DB_PASS = "mysql";
    private static String DB_CLASS = "com.mysql.jdbc.Driver";


    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement stmt;
    //private static ResultSet rs;

    /**
     * передаем значения с датчиков в базу
     */

    public static void DB_INSERT(Sensor mySensor, int terminal_id){

        String querry = "INSERT INTO Sensors (type,number,data,terminal_id) VALUES (?,?,?,?)";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {

            connection = DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PASS);
            stmt = connection.prepareStatement(querry);
            stmt.setInt(1,mySensor.GetType());
            stmt.setInt(2,mySensor.GetNumber());
            stmt.setInt(3,mySensor.GetData());
            stmt.setInt(4,terminal_id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void DB_SELECT(){
        int ID;

        String preStatement = "SELECT * FROM Sensors";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PASS);
            stmt = connection.prepareStatement(preStatement);

            resultSet = stmt.executeQuery();

            while (resultSet.next()){
                System.out.println(
                        " id: " + resultSet.getInt(1) +
                                " \t type: " + resultSet.getInt(2) +
                                " \t number: " + resultSet.getInt(3) +
                                " \t data: " + resultSet.getInt(4) +
                                " \t terminal_id: " + resultSet.getInt(5) );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    private static void DB_UPDATE(int data, int id){

        String querry = "UPDATE Sensors SET data=? WHERE id=?";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {

            connection = DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PASS);
            stmt = connection.prepareStatement(querry);
            stmt.setInt(1,data);
            stmt.setInt(2,id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}


