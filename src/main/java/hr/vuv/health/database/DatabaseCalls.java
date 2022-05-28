package hr.vuv.health.database;

import java.sql.*;

public class DatabaseCalls {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String serverName = "DARK\\SQLEXPRESS";
        String dbName = "UserServiceDb";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://" +serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

        return DriverManager.getConnection(connectionUrl);
    }

    public int dohvatiBrojKorisnika() {
        int nBrojKorisnika = 0;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            String SQL = "SELECT COUNT(*) as BrojKorisnika FROM AspNetUsers;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next())
            {
                nBrojKorisnika = rs.getInt("BrojKorisnika");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return nBrojKorisnika;
    }

    public String dohvatiPosljednjeRegistriranogKorisnika() {
        String sKorisnik = "";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            String SQL = "SELECT TOP 1 * FROM AspNetUsers;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next())
            {
                sKorisnik = rs.getString("UserName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  sKorisnik;
    }

}
