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

    public int dohvatiBrojKorisnika() throws ClassNotFoundException{
        int nBrojKorisnika = 0;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            String SQL = "SELECT COUNT(*) as BrojKorisnika FROM AspNetUsers;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next())
            {
                nBrojKorisnika = rs.getInt("BrojKorisnika");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nBrojKorisnika;
    }

    public String dohvatiPosljednjeRegistriranogKorisnika() throws ClassNotFoundException{
        String sKorisnik = "";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            String SQL = "SELECT TOP 1 * FROM AspNetUsers;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next())
            {
                sKorisnik = rs.getString("UserName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  sKorisnik;
    }

    public String dodajDoktora() throws ClassNotFoundException {
        String sId = "";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AspNetUsers] ([Id], [AccessFailedCount], [ConcurrencyStamp], [DateOfBirth], [Discriminator], [Email], [EmailConfirmed], [FirstName], [LastName], [LockoutEnabled], \n" +
                            "[LockoutEnd], [NormalizedEmail], [NormalizedUserName], [PasswordHash], [PhoneNumber], [PhoneNumberConfirmed], [SecurityStamp], [TwoFactorEnabled], [UserName])  \n" +
                            "\n" +
                            "VALUES (@p0, @p1, @p2, @p3, @p4, @p5, @p6, @p7, @p8, @p9, @p10, @p11, @p12, @p13, @p14, @p15, @p16, @p17, @p18);  \n" +
                            "\n" +
                            "SELECT [Id] as Id FROM [AspNetUsers]\n" +
                            "\n" +
                            "',N'@p0 nvarchar(450),@p1 int,@p2 nvarchar(4000),@p3 datetime2(7),@p4 nvarchar(4000),@p5 nvarchar(256),@p6 bit,@p7 nvarchar(4000),@p8 nvarchar(4000),@p9 bit,@p10 datetimeoffset(7),@p11 nvarchar(256),\n" +
                            "@p12 nvarchar(256),@p13 nvarchar(4000),@p14 nvarchar(4000),@p15 bit,@p16 nvarchar(4000),@p17 bit,@p18 nvarchar(256)',\n" +
                            "\n" +
                            "@p0=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p1=0,@p2=N'bead116e-5ce7-4dc1-84d3-d5a5a81b883c',@p3='2022-06-08 20:26:52.0780000',@p4=N'HealthHubUser',@p5=N'durobelacic@gmail.com',@p6=0,@p7=N'Đuro',@p8=N'Belačić',\n" +
                            "@p9=1,@p10=NULL,@p11=N'DUROBELACIC@GMAIL.COM',@p12=N'DUROBELACIC',@p13=N'AQAAAAEAACcQAAAAEHlbsC8ioBvpHVOqnfwEp0xd5/1kYxkmLRmuZDgbTDWe9IbAU3WwTWfuFeqjVlz0JA==',@p14=N'+385916031921',@p15=0,\n" +
                            "@p16=N'4DVNN6UFOOAI5HOAY62U4UCGU6LOO7OR',@p17=0,@p18=N'durobelacic'\n" +
                            "\n" +
                            "\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AspNetUserRoles] ([RoleId], [UserId])  \n" +
                            "\n" +
                            "VALUES (@p0, @p1);  ',N'@p0 nvarchar(450),@p1 nvarchar(450)',\n" +
                            "\n" +
                            "@p0=N'837ce43d-7135-4f92-810e-8ef490846e93',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046'" +
                            "\n" +
                            "INSERT INTO MainService.Doctors ([Id]\n" +
                            "\t  ,[FirstName]\n" +
                            "      ,[LastName]\n" +
                            "      ,[Title]\n" +
                            "      ,[AddressId]\n" +
                            "      ,[Email]\n" +
                            "      ,[SpecializationId])\n" +
                            "\n" +
                            "VALUES ('e9fbae22-ea0c-4f52-9618-2717e8f53046', 'Đuro', 'Belačić', 'Doctor', 1, 'durobelacic@gmail.com', 1);";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sId = rs.getString("Id");
            }

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return sId;
    }

    public void obrisiDoktoraPoId(String sId) throws ClassNotFoundException{

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DELETE FROM AspNetUsers WHERE Id="+sId+";\n" +
                            "\n" +
                            "DELETE FROM MainService.Doctors WHERE Id="+sId+";";

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void obrisiDoktora(String sEmail) throws ClassNotFoundException{

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DELETE FROM AspNetUsers WHERE Email="+sEmail+";\n" +
                            "\n" +
                    "DELETE FROM MainService.Doctors WHERE Email ="+sEmail+";";

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
