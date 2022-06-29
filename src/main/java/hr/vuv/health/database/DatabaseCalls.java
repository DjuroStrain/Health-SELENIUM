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

    /*
    * Dohvati broj korisnika
    * */
    public int dbConnectionDohvatiBrojKorisnika() throws ClassNotFoundException{
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

    /*
     * Dohvati broj doktora
     * */
    public int dbConnectionDohvatiBrojDoktora() throws ClassNotFoundException{
        int nBrojDoktora = 0;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            String SQL = "SELECT COUNT(*) as BrojKorisnika FROM MainService.Doctors;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next())
            {
                nBrojDoktora = rs.getInt("BrojKorisnika");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nBrojDoktora;
    }

    /*
    * Dohvati posljednjeg korisnika u tablici AspNetUsers
    * */
    public String dbConnectionDohvatiPosljednjeRegistriranogKorisnika() throws ClassNotFoundException{
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

    /*
    * Dodaj doktora u tablicu (AspNetUsers + AspNetUsersRoles + MainService.Doctors)
    * */
    public String dbConnectionDodajDoktora() throws ClassNotFoundException {
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
                                "@p0=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p1=0,@p2=N'bead116e-5ce7-4dc1-84d3-d5a5a81b883c',@p3='2022-06-08 20:26:52.0780000',@p4=N'HealthHubUser',@p5=N'durobelacic@gmail.com',@p6=0,@p7=N'Duro',@p8=N'Belacic',\n" +
                                "@p9=1,@p10=NULL,@p11=N'DUROBELACIC@GMAIL.COM',@p12=N'DUROBELACIC',@p13=N'AQAAAAEAACcQAAAAEHlbsC8ioBvpHVOqnfwEp0xd5/1kYxkmLRmuZDgbTDWe9IbAU3WwTWfuFeqjVlz0JA==',@p14=N'+385916031921',@p15=0,\n" +
                                "@p16=N'4DVNN6UFOOAI5HOAY62U4UCGU6LOO7OR',@p17=0,@p18=N'durobelacic'\n" +
                                "\n" +
                                "\n" +
                                "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AspNetUserRoles] ([RoleId], [UserId])  \n" +
                                "\n" +
                                "VALUES (@p0, @p1);  ',N'@p0 nvarchar(450),@p1 nvarchar(450)',\n" +
                                "\n" +
                                "@p0=N'435feff1-bad2-4dcc-8f0d-79848bcf92e7',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046'\n" +
                                "\n" +
                                "INSERT INTO MainService.Doctors ([Id]\n" +
                                "  ,[FirstName]\n" +
                                "      ,[LastName]\n" +
                                "      ,[Title]\n" +
                                "      ,[AddressId]\n" +
                                "      ,[Email]\n" +
                                "      ,[SpecializationId])\n" +
                                "\n" +
                                "VALUES ('e9fbae22-ea0c-4f52-9618-2717e8f53046', 'Duro', 'Belacic', 'Doctor', 1, 'durobelacic@gmail.com', 1);\n" +
                                "\n" +
                                "exec sp_executesql N'SET NOCOUNT ON;  DECLARE @inserted0 TABLE ([Id] int, [_Position] [int]);  MERGE [MainService].[WorkHours] USING \n" +
                                "\n" +
                                "(  VALUES (@p0, @p1, @p2, @p3, @p4, 0),  (@p5, @p6, @p7, @p8, @p9, 1),  (@p10, @p11, @p12, @p13, @p14, 2),  (@p15, @p16, @p17, @p18, @p19, 3),  (@p20, @p21, @p22, @p23, @p24, 4),  (@p25, @p26, @p27, @p28, @p29, 5),  (@p30, @p31, @p32, @p33, @p34, 6)) \n" +
                                "\n" +
                                "AS i ([Day], [DayOff], [DoctorId], [TimeFrom], [TimeTo], _Position) ON 1=0  WHEN NOT MATCHED THEN  INSERT ([Day], [DayOff], [DoctorId], [TimeFrom], [TimeTo])  VALUES (i.[Day], i.[DayOff], i.[DoctorId], i.[TimeFrom], i.[TimeTo])  \n" +
                                "\n" +
                                "OUTPUT INSERTED.[Id], i._Position  INTO @inserted0;    \n" +
                                "\n" +
                                "SELECT [i].[Id] FROM @inserted0 i  ORDER BY [i].[_Position];    \n" +
                                "\n" +
                                "',N'@p0 int,@p1 bit,@p2 nvarchar(450),@p3 time(7),@p4 time(7),@p5 int,@p6 bit,@p7 nvarchar(450),@p8 time(7),@p9 time(7),@p10 int,@p11 bit,@p12 nvarchar(450),@p13 time(7),@p14 time(7),@p15 int,@p16 bit,@p17 nvarchar(450),@p18 time(7),@p19 time(7),@p20 int,\n" +
                                "@p21 bit,@p22 nvarchar(450),@p23 time(7),@p24 time(7),@p25 int,@p26 bit,@p27 nvarchar(450),@p28 time(7),@p29 time(7),@p30 int,@p31 bit,@p32 nvarchar(450),@p33 time(7),@p34 time(7)',\n" +
                                "\n" +
                                "@p0=0,@p1=1,@p2=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p3='00:00:00',@p4='00:00:00',@p5=1,@p6=1,@p7=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p8='00:00:00',@p9='00:00:00',@p10=2,@p11=1,@p12=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p13='00:00:00',\n" +
                                "@p14='00:00:00',@p15=3,@p16=1,@p17=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p18='00:00:00',@p19='00:00:00',@p20=4,@p21=1,@p22=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p23='00:00:00',@p24='00:00:00',@p25=5,@p26=1,@p27=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',\n" +
                                "@p28='00:00:00',@p29='00:00:00',@p30=6,@p31=1,@p32=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p33='00:00:00',@p34='00:00:00'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sId = rs.getString("Id");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return sId;
    }

    /*
     * Dodaj doktora u tablicu bez adrese i specijalizacije (AspNetUsers + AspNetUsersRoles + MainService.Doctors)
     * */
    public String dbConnectionDodajDoktoraBezAdreseISpecijalizacije() throws ClassNotFoundException {
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
                            "@p0=N'e8fbae22-ea0c-4f52-9618-2717e8f53046',@p1=0,@p2=N'bead116e-5ce7-4dc1-84d3-d5a5a81b883c',@p3='2022-06-08 20:26:52.0780000',@p4=N'HealthHubUser',@p5=N'durobelacic2@gmail.com',@p6=0,@p7=N'Duro2',@p8=N'Belacic2',\n" +
                            "@p9=1,@p10=NULL,@p11=N'DUROBELACIC2@GMAIL.COM',@p12=N'DUROBELACIC2',@p13=N'AQAAAAEAACcQAAAAEHlbsC8ioBvpHVOqnfwEp0xd5/1kYxkmLRmuZDgbTDWe9IbAU3WwTWfuFeqjVlz0JA==',@p14=N'+385916031921',@p15=0,\n" +
                            "@p16=N'4DVNN6UFOOAI5HOAY62U4UCGU6LOO7OR',@p17=0,@p18=N'durobelacic2'\n" +
                            "\n" +
                            "\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AspNetUserRoles] ([RoleId], [UserId])  \n" +
                            "\n" +
                            "VALUES (@p0, @p1);  ',N'@p0 nvarchar(450),@p1 nvarchar(450)',\n" +
                            "\n" +
                            "@p0=N'435feff1-bad2-4dcc-8f0d-79848bcf92e7',@p1=N'e8fbae22-ea0c-4f52-9618-2717e8f53046'\n" +
                            "\n" +
                            "INSERT INTO MainService.Doctors ([Id]\n" +
                            "  ,[FirstName]\n" +
                            "      ,[LastName]\n" +
                            "      ,[Title]\n" +
                            "      ,[AddressId]\n" +
                            "      ,[Email]\n" +
                            "      ,[SpecializationId])\n" +
                            "\n" +
                            "VALUES ('e8fbae22-ea0c-4f52-9618-2717e8f53046', 'Duro2', 'Belacic2', 'NULLf', NULL, 'durobelacic2@gmail.com', NULL);";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sId = rs.getString("Id");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return sId;
    }

    /*
    * Obrisi doktora po ID-u (AspNetUsers + MainService.Doctors)
    * */
    public void dbConnectionObrisiDoktoraPoId(String sId) throws ClassNotFoundException{

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DELETE FROM AspNetUsers WHERE Id='"+sId+"';\n" +
                            "\n" +
                    "DELETE FROM MainService.Doctors WHERE Id='"+sId+"';";

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    * Obrisi doktora po emailu (AspNetUsers + MainService.Doctors)
    * */
    public void dbConnectionObrisiDoktoraPoEmailu(String sEmail) throws ClassNotFoundException{

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

    public void dbConnectionObrisiKorisnikaPoKorisnickomImenu(String sUserName) throws ClassNotFoundException{

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DELETE FROM AspNetUsers WHERE UserName='"+sUserName+"'";

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    * Dohvati broj usluga (MainService.Services)
    * */
    public int dbConnectionDohvatiBrojUslugaDoktora(String sDoctorID) throws ClassNotFoundException {
        int nBrojUsluga = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT COUNT(*) as BrojUsluga FROM MainService.Services WHERE DoctorId='"+sDoctorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                nBrojUsluga = rs.getInt("BrojUsluga");
            }

        }catch (SQLException e){
                e.printStackTrace();
        }

        return nBrojUsluga;
    }

    /*
    * Vrati ID zadnje dodane usluge
    * */
    public int dbConnectionVratiIDZadnjeDodaneUsluge() throws ClassNotFoundException {
        int nZadnjiID = 0;

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT TOP 1 Id FROM MainService.Services";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                nZadnjiID = rs.getInt("Id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return nZadnjiID;
    }

    /*
    * Obrisi uslugu po id
    * */
    public void dbConnectionObrisiUsluguPoID() throws ClassNotFoundException {
        int nId = dbConnectionVratiIDZadnjeDodaneUsluge();
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM MainService.Services WHERE Id="+nId;

            stmt.execute(SQL);
        }catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    public void dbConnectionObrisiUsluguPoParametarID(int nID) throws ClassNotFoundException {
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM MainService.Services WHERE Id="+nID;

            stmt.execute(SQL);
        }catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    /*
    * Dodaj uslugu djelatnosti
    * */
    public int dbConnectionDodajUslugu() throws ClassNotFoundException{
        int nIdUsluge = 0;

        try(Connection con = getConnection(); Statement stmt = con.createStatement()) {

            String SQL =
                        "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [MainService].[Services] ([Description], [DoctorId], [Name], [Price], [SpecializationId]) \n" +
                                "\n" +
                                "VALUES (@p0, @p1, @p2, @p3, @p4);  \n" +
                                "\n" +
                                "SELECT [Id]  FROM [MainService].[Services]  WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();    \n" +
                                "\n" +
                                "',N'@p0 nvarchar(4000),@p1 nvarchar(450),@p2 nvarchar(4000),@p3 decimal(18,2),@p4 int',\n" +
                                "\n" +
                                "@p0=N'Osnovni pregled',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p2=N'Pregled',@p3=52.00,@p4=1\n";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                nIdUsluge = rs.getInt("Id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return nIdUsluge;
    }


    /*
    * Vrati radno vrijeme ordinacije za određeni dan
    * */
    public String dbConnectionVratiRadnoVrijemeOrdinacijeZaOdredeniDan(int nDanID, String sDoktorID) throws ClassNotFoundException{
        String sRadnoVrijeme = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT CONCAT(TimeFrom,'-',TimeTo) AS RadnoVrijeme FROM MainService.WorkHours WHERE Day = "+nDanID+" AND DoctorId = '"+sDoktorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                sRadnoVrijeme = rs.getString("RadnoVrijeme");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return sRadnoVrijeme;
    }
}
