package hr.vuv.health.database;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatabaseCalls {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String serverName = "DARK\\SQLEXPRESS";
        String dbName = "UserServiceDb";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://" +serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

        return DriverManager.getConnection(connectionUrl, "durobelacic", "AlphaWerewolf0851");
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
                                "@p0=N'16cf241c-8981-4e68-9e8b-5790cba4fbdc',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046'\n" +
                                "\n" +
                                "INSERT INTO MainService.Doctors ([Id]\n" +
                                "  ,[FirstName]\n" +
                                "      ,[LastName]\n" +
                                "      ,[Title]\n" +
                                "      ,[AddressId]\n" +
                                "      ,[Email]\n" +
                                "      ,[SpecializationId])\n" +
                                "\n" +
                                "VALUES ('e9fbae22-ea0c-4f52-9618-2717e8f53046', 'Duro', 'Belacic', 'dr. mr.', 1033, 'durobelacic@gmail.com', 4);\n" +
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
        System.out.println("sID: "+sId);
        return sId;
    }

    /*
     * Dodaj doktora u tablicu (AspNetUsers + AspNetUsersRoles + MainService.Doctors)
     * */
    public String dbConnectionDodajDoktoraSaRadnimVremenom() throws ClassNotFoundException {
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
                            "@p0=N'16cf241c-8981-4e68-9e8b-5790cba4fbdc',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046'\n" +
                            "\n" +
                            "INSERT INTO MainService.Doctors ([Id]\n" +
                            "  ,[FirstName]\n" +
                            "      ,[LastName]\n" +
                            "      ,[Title]\n" +
                            "      ,[AddressId]\n" +
                            "      ,[Email]\n" +
                            "      ,[SpecializationId])\n" +
                            "\n" +
                            "VALUES ('e9fbae22-ea0c-4f52-9618-2717e8f53046', 'Duro', 'Belacic', 'dr. mr.', 1033, 'durobelacic@gmail.com', 4);\n" +
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
                            "@p0=0,@p1=1,@p2=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p3='00:00:00',@p4='00:00:00',@p5=1,@p6=0,@p7=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p8='08:00:00',@p9='14:00:00',@p10=2,@p11=0,@p12=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p13='08:00:00',\n" +
                            "@p14='14:00:00',@p15=3,@p16=0,@p17=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p18='14:00:00',@p19='20:00:00',@p20=4,@p21=0,@p22=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p23='14:00:00',@p24='20:00:00',@p25=5,@p26=0,@p27=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',\n" +
                            "@p28='08:00:00',@p29='14:00:00',@p30=6,@p31=1,@p32=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p33='00:00:00',@p34='00:00:00'";

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
                            "@p0=N'e8fbae22-ea0c-4f52-9618-2717e8f53046',@p1=0,@p2=N'bead116e-5ce7-4dc1-84d3-d5a5a81b883c',@p3='2022-06-08 20:26:52.0780000',@p4=N'HealthHubUser',@p5=N'ivoivic@gmail.com',@p6=0,@p7=N'Ivo',@p8=N'Ivic',\n" +
                            "@p9=1,@p10=NULL,@p11=N'IVOIVIC@GMAIL.COM',@p12=N'IVOIVIC',@p13=N'AQAAAAEAACcQAAAAEHlbsC8ioBvpHVOqnfwEp0xd5/1kYxkmLRmuZDgbTDWe9IbAU3WwTWfuFeqjVlz0JA==',@p14=N'+385916031921',@p15=0,\n" +
                            "@p16=N'4DVNN6UFOOAI5HOAY62U4UCGU6LOO7OR',@p17=0,@p18=N'ivoivic'\n" +
                            "\n" +
                            "\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AspNetUserRoles] ([RoleId], [UserId])  \n" +
                            "\n" +
                            "VALUES (@p0, @p1);  ',N'@p0 nvarchar(450),@p1 nvarchar(450)',\n" +
                            "\n" +
                            "@p0=N'16cf241c-8981-4e68-9e8b-5790cba4fbdc',@p1=N'e8fbae22-ea0c-4f52-9618-2717e8f53046'\n" +
                            "\n" +
                            "INSERT INTO MainService.Doctors ([Id]\n" +
                            "  ,[FirstName]\n" +
                            "      ,[LastName]\n" +
                            "      ,[Title]\n" +
                            "      ,[AddressId]\n" +
                            "      ,[Email]\n" +
                            "      ,[SpecializationId])\n" +
                            "\n" +
                            "VALUES ('e8fbae22-ea0c-4f52-9618-2717e8f53046', 'Ivo', 'Ivic', NULL, NULL, 'ivoivic@gmail.com', NULL);";

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
                    "DELETE FROM AspNetUsers WHERE Email='"+sEmail+"';\n" +
                            "\n" +
                    "DELETE FROM MainService.Doctors WHERE Email ='"+sEmail+"';";

            stmt.execute(SQL);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
     * Vrati punu naziv doktora
     * */
    public String dbConnectionVratiPuniNazivDoktora(String sDoktorID) throws ClassNotFoundException{
        String sNaziv = "";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "SELECT CONCAT(Title, ' ',FirstName,' ',LastName) as NazivDoktora FROM MainService.Doctors WHERE MainService.Doctors.Id ='"+sDoktorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sNaziv = rs.getString("NazivDoktora");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return sNaziv;
    }

    /*
     * Vrati specijalzaciju doktora
     * */
    public String dbConnectionVratiSpecijalizacijuDoktora(String sDoktorID) throws ClassNotFoundException{
        String sSpecijalizacija = "";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "SELECT MainService.Specializations.Name AS Specijalizacija FROM MainService.Specializations INNER JOIN MainService.Doctors ON Doctors.SpecializationId = Specializations.Id WHERE Doctors.Id ='"+sDoktorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sSpecijalizacija = rs.getString("Specijalizacija");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return sSpecijalizacija;
    }

    /*
     * Vrati email doktora
     * */
    public String dbConnectionVratiEmailDoktora(String sDoktorID) throws ClassNotFoundException{
        String sEmail = "";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "SELECT MainService.Doctors.Email FROM MainService.Doctors WHERE Id='"+sDoktorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sEmail = rs.getString("Email");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return sEmail;
    }

    /*
    * Vrati punu adresu za doktora
    * */
    public String dbConnectionVratiPunuAdresuDoktora(String sDoktorID) throws ClassNotFoundException{
        String sAdresa = "";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "SELECT CONCAT(StreetName,' ',StreetNo,', ', PostalCode,', ',City) AS Adresa FROM MainService.Address INNER JOIN MainService.Doctors ON Doctors.AddressId = MainService.Address.Id WHERE MainService.Doctors.Id ='"+sDoktorID+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sAdresa = rs.getString("Adresa");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return sAdresa;
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
                                "@p0=N'Opis pregleda',@p1=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p2=N'Pregled',@p3=52.00,@p4=4\n";

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

    /*
    *  Vrati opis pregleda za termin
    * */

    public String dbConnectionVratiOpisPregledaZaTermin(String sPacijentID, String sDoktorId) throws ClassNotFoundException {
        String sOpisPregleda = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT Title FROM AppointmentService.Appointments WHERE PatientId = '"+sPacijentID+"' AND DoctorId='"+sDoktorId+"';";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sOpisPregleda = rs.getString("Title");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sOpisPregleda;
    }

    public String dbConnectionVratiOpisPregledaZaTerminTabMojtermini(String sPacijentID, String sDoktorId) throws ClassNotFoundException {
        String sOpisPregleda = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT CONCAT(AppointmentService.Appointments.Title,' - ',MainService.Doctors.FirstName,' ',MainService.Doctors.LastName) as OpisPregleda FROM AppointmentService.Appointments INNER JOIN MainService.Doctors on MainService.Doctors.Id = DoctorId WHERE PatientId = '"+sPacijentID+"' AND DoctorId='"+sDoktorId+"';";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sOpisPregleda = rs.getString("OpisPregleda");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sOpisPregleda;
    }

    /*
     *  Vrati opis pregleda za termin
     * */

    public String dbConnectionVratiOpisPregledaZaTermin(String sDoktorId) throws ClassNotFoundException {
        String sOpisPregleda = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT Title FROM AppointmentService.Appointments WHERE DoctorId='"+sDoktorId+"';";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sOpisPregleda = rs.getString("Title");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sOpisPregleda;
    }



    /*
     *  Vrati vrijeme termina
     * */

    public String dbConnectionVratiVrijemeTermina(String sPacijentID, String sDoktorId) throws ClassNotFoundException {
        String sVrijemeTermina = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT CONCAT(FORMAT(TimeFrom,'hh:mm'),' - ',FORMAT(TimeTo,'hh:mm')) as VrijemeTermina FROM AppointmentService.Appointments WHERE PatientId = '"+sPacijentID+"' AND DoctorId='"+sDoktorId+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sVrijemeTermina = rs.getString("VrijemeTermina");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sVrijemeTermina;
    }

    public String dbConnectionVratiVrijemeTermina(String sDoktorId) throws ClassNotFoundException {
        String sVrijemeTermina = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT CONCAT(FORMAT(TimeFrom,'hh:mm'),' - ',FORMAT(TimeTo,'hh:mm')) as VrijemeTermina FROM AppointmentService.Appointments WHERE DoctorId='"+sDoktorId+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                sVrijemeTermina = rs.getString("VrijemeTermina");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sVrijemeTermina;
    }


    /*
     *  Obrisi termin
     * */

    public void dbConnectionObrisiTermin(String sPacijentID, String sDoktorId) throws ClassNotFoundException {
        String sVrijemeTermina = "";

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM AppointmentService.Appointments WHERE PatientId = '"+sPacijentID+"' AND DoctorId='"+sDoktorId+"'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Dodaj termin
    * */
    public void dbConnectionDodajTermin() throws ClassNotFoundException {
        String sVrijemeTermina = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String vrijemeTerminaOd = LocalDate.now().format(formatter)+" 10:00:00";
        String vrijemeTerminaDo = LocalDate.now().format(formatter)+" 11:00:00";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DECLARE @date datetime, @date2 datetime\n" +
                            "SET @date='"+vrijemeTerminaOd+"'\n" +
                            "SET @date2='"+vrijemeTerminaDo+"'\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AppointmentService].[Appointments] ([Description], [DoctorFullName], [DoctorId], \n" +
                            "[PatientFullName], [PatientId], [Status], [TimeFrom], [TimeTo], [Title])  VALUES (@p0, @p1, @p2, @p3, @p4, @p5, @p6, @p7, @p8);  \n" +
                            "\n" +
                            "SELECT [Id]  FROM [AppointmentService].[Appointments]  WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();    \n" +
                            "\n" +
                            "',N'@p0 nvarchar(4000),@p1 nvarchar(4000),@p2 nvarchar(4000),@p3 nvarchar(4000),@p4 nvarchar(4000),@p5 int,@p6 datetime2(7),@p7 datetime2(7),\n" +
                            "@p8 nvarchar(4000)',\n" +
                            "\n" +
                            "@p0=N'Bolest',@p1=N'Duro Belacic',@p2=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p3=N'Ivan Horvat',@p4=N'c7641fef-ad4e-4f9c-ab69-5fb4365db1c7',\n" +
                            "@p5=0,@p6=@date,@p7=@date2,@p8=N'Pregled'\n";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Dodaj termin
     * */
    public void dbConnectionDodajTermin2() throws ClassNotFoundException {
        String sVrijemeTermina = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String vrijemeTerminaOd = LocalDate.now().format(formatter)+" 10:00:00";
        String vrijemeTerminaDo = LocalDate.now().format(formatter)+" 11:00:00";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DECLARE @date datetime, @date2 datetime\n" +
                            "SET @date='"+vrijemeTerminaOd+"'\n" +
                            "SET @date2='"+vrijemeTerminaDo+"'\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;  INSERT INTO [AppointmentService].[Appointments] ([Description], [DoctorFullName], [DoctorId], \n" +
                            "[PatientFullName], [PatientId], [Status], [TimeFrom], [TimeTo], [Title])  VALUES (@p0, @p1, @p2, @p3, @p4, @p5, @p6, @p7, @p8);  \n" +
                            "\n" +
                            "SELECT [Id]  FROM [AppointmentService].[Appointments]  WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();    \n" +
                            "\n" +
                            "',N'@p0 nvarchar(4000),@p1 nvarchar(4000),@p2 nvarchar(4000),@p3 nvarchar(4000),@p4 nvarchar(4000),@p5 int,@p6 datetime2(7),@p7 datetime2(7),\n" +
                            "@p8 nvarchar(4000)',\n" +
                            "\n" +
                            "@p0=N'Bolest',@p1=N'Duro Belacic',@p2=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p3=N'Ivan Horvat',@p4=N'c7641fef-ad4e-4f9c-ab69-5fb4365db1c7',\n" +
                            "@p5=1,@p6=@date,@p7=@date2,@p8=N'Pregled'\n";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Obrisi obavijest
     * */

    public void dbConnectionObrisiObavijestDoktor(String sPacijentID, String sDoktorId) throws ClassNotFoundException {

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM NotificationService.Notifications WHERE CreatedBy = '"+sPacijentID+"' AND CreatedFor='"+sDoktorId+"'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbConnectionObrisiObavijestPacijent(String sPacijentID, String sDoktorId) throws ClassNotFoundException {

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM NotificationService.Notifications WHERE CreatedBy = '"+sDoktorId+"' AND CreatedFor='"+sPacijentID+"'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    * Dodaj obavijest
    * */
    public void dbConnectionDodajObavijestDoktoru() throws ClassNotFoundException{
        String sVrijemeTermina = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String vrijemeTerminaOd = LocalDate.now().format(formatter)+" 10:00:00";
        String sSadrzaj = LocalDate.now().format(formatter2)+" - Bolest";
        System.out.println(sSadrzaj);
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DECLARE @date datetime, @sadrzaj nvarchar(MAX)\n" +
                            "SET @date='"+vrijemeTerminaOd+"'\n" +
                            "SET @sadrzaj='"+sSadrzaj+"'\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;\n" +
                            "INSERT INTO [NotificationService].[Notifications] ([Content], [CreatedAt], [CreatedBy], [CreatedFor], [Read], [Title])\n" +
                            "VALUES (@p0, @p1, @p2, @p3, @p4, @p5);\n" +
                            "SELECT [Id]\n" +
                            "FROM [NotificationService].[Notifications]\n" +
                            "WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();\n" +
                            "',N'@p0 nvarchar(4000),@p1 datetime2(7),@p2 nvarchar(4000),@p3 nvarchar(4000),@p4 bit,@p5 nvarchar(4000)',\n" +
                            "@p0=@sadrzaj,@p1=@date,@p2=N'c7641fef-ad4e-4f9c-ab69-5fb4365db1c7',\n" +
                            "@p3=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p4=0,@p5=N'Ivan Horvat dodao novi termin.'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Dodaj obavijest
     * */
    public void dbConnectionDodajObavijestPacijentu() throws ClassNotFoundException{
        String sVrijemeTermina = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String vrijemeTerminaOd = LocalDate.now().format(formatter)+" 10:00:00";
        String sSadrzaj = "Termin "+LocalDate.now().format(formatter2)+" 10:00 - "+ LocalDate.now().format(formatter2) + " 10:30 kod liječnika Duro Belacic je potvrđen";
        System.out.println(sSadrzaj);
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DECLARE @date datetime, @sadrzaj nvarchar(MAX)\n" +
                            "SET @date='"+vrijemeTerminaOd+"'\n" +
                            "SET @sadrzaj='"+sSadrzaj+"'\n" +
                            "exec sp_executesql N'SET NOCOUNT ON;\n" +
                            "INSERT INTO [NotificationService].[Notifications] ([Content], [CreatedAt], [CreatedBy], [CreatedFor], [Read], [Title])\n" +
                            "VALUES (@p0, @p1, @p2, @p3, @p4, @p5);\n" +
                            "SELECT [Id]\n" +
                            "FROM [NotificationService].[Notifications]\n" +
                            "WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();\n" +
                            "',N'@p0 nvarchar(4000),@p1 datetime2(7),@p2 nvarchar(4000),@p3 nvarchar(4000),@p4 bit,@p5 nvarchar(4000)',\n" +
                            "@p0=@sadrzaj,@p1=@date,\n" +
                            "@p2=N'e9fbae22-ea0c-4f52-9618-2717e8f53046',@p3=N'c7641fef-ad4e-4f9c-ab69-5fb4365db1c7',@p4=0,@p5=N'Termin je ažuriran.'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Vrati broj obavijesti
    * */
    public int dbConnectionVratiBrojObavijesti(String sPacijentID, String sDoktorId) throws ClassNotFoundException {
        int nBrojObavijesti = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT COUNT(*) AS BrojObavijesti FROM NotificationService.Notifications WHERE CreatedBy = '"+sPacijentID+"' AND CreatedFor='"+sDoktorId+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                nBrojObavijesti = rs.getInt("BrojObavijesti");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return nBrojObavijesti;
    }

    /*
     * Vrati broj specijalizacija
     * */
    public int dbConnectionVratiBrojSpecijalizacija(String sSpecijalizacija) throws ClassNotFoundException {
        int nBrojSpecijalizacija = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT COUNT(*) AS BrojSpecijalizacija FROM MainService.Specializations WHERE Name = '"+sSpecijalizacija+"'";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                nBrojSpecijalizacija = rs.getInt("BrojSpecijalizacija");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return nBrojSpecijalizacija;
    }
    /*
     * Obrisi specijalizaciju
     * */
    public void dbConnectionObrisiSpecijalizaciju(String sSpecijalizacija) throws ClassNotFoundException {

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM MainService.Specializations WHERE Name = '"+sSpecijalizacija+"'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Dodaj specijalizaciju
    * */
    public void dbConnectionDodajSpecijalizaciju() throws ClassNotFoundException {

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "exec sp_executesql N'SET NOCOUNT ON;\n" +
                            "INSERT INTO [MainService].[Specializations] ([Description], [Name])\n" +
                            "VALUES (@p0, @p1);\n" +
                            "SELECT [Id]\n" +
                            "FROM [MainService].[Specializations]\n" +
                            "WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();\n" +
                            "',N'@p0 nvarchar(4000),@p1 nvarchar(4000)',@p0=N'Hematologija je znanost koja se bavi proučavanjem krvi',@p1=N'Hematologija'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
     * Vrati dodanu gresku
     * */
    public int dbConnectionVratiDodanuGresku(String sEmail) throws ClassNotFoundException {
        int nDodanaGreska = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "SELECT COUNT(*) as DodanaGreska FROM MainService.BugReports WHERE Email = '"+sEmail+"'";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()){
                nDodanaGreska = rs.getInt("DodanaGreska");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return nDodanaGreska;
    }
    /*
     * Obrisi gresku
     * */
    public void dbConnectionObrisiGresku(String sEmail) throws ClassNotFoundException {

        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL = "DELETE FROM MainService.BugReports WHERE Email = '"+sEmail+"'";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Obrisi gresku
     * */
    public void dbConnectionDodajGresku() throws ClassNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String vrijeme = LocalDate.now().format(formatter)+" 10:00:00";
        try (Connection con = getConnection(); Statement stmt = con.createStatement()){

            String SQL =
                    "DECLARE @date datetime, @sadrzaj nvarchar(MAX)\n" +
                            "SET @date='"+vrijeme+"'\n" +
                    "exec sp_executesql N'SET NOCOUNT ON;\n" +
                            "INSERT INTO [MainService].[BugReports] ([Content], [Email], [Timestamp])\n" +
                            "VALUES (@p0, @p1, @p2);\n" +
                            "SELECT [Id]\n" +
                            "FROM [MainService].[BugReports]\n" +
                            "WHERE @@ROWCOUNT = 1 AND [Id] = scope_identity();\n" +
                            "',N'@p0 nvarchar(4000),@p1 nvarchar(4000),@p2 datetime2(7)',@p0=N'Nije moguće rezervirati termin',@p1=N'ivanhorvat@gmail.com',@p2=@date";

            stmt.execute(SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
