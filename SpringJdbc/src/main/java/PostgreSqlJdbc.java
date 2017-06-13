/**
 * Created by hao-pham on 6/12/17.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PostgreSqlJdbc {

    private static Connection db;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    Statement stmt = null;
    public ShotBox sb =new ShotBox();

    public static void main(String args[]) {
        PostgreSqlJdbc pg= new PostgreSqlJdbc();
       // pg.DataBaseConnection();
        //pg.CreateTable();
        //pg.InsertDataBase();
        pg.getConnection();

        pg.SelectTable();
        pg.getConnection();
        pg.InsertDataBase();
        pg.SelectTable();

    }

    public static Connection getConnection() {
        db = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream("cfg.properties"));
            URL = properties.getProperty("url"); // lấy giá trị url trong file
            USERNAME = properties.getProperty("username"); // lấy giá trị user trong file
            PASSWORD = properties.getProperty("password"); // lấy giá trị password trong file
            DriverManager.registerDriver(new org.postgresql.Driver());
            db = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD); // kết nối vào cơ sở dữ liệu MySQL
        } catch (Exception  ex) {
            Logger.getLogger(SpringJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (db);
    }
    public void CreateTable(){
        try {
            stmt = db.createStatement();
            String sql = "CREATE TABLE SHOTBOX " +
                    "(ID LONG PRIMARY KEY     NOT NULL," +
                    " USERNAME           TEXT    NOT NULL, " +
                    " CONTENT            TEXT     NOT NULL, " +
                    " DATETIME_TEXT         TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully\n");
    }
    public void InsertDataBase(){
        try {
            Scanner s = new Scanner(System.in);
           stmt = db.createStatement();

            sb.setContent(s.nextLine());

            Date Time = new Date();
            SimpleDateFormat setTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
            String showTime = setTime.format(Time.getTime());
            sb.setId(Time.getTime());
            String sql = "INSERT INTO SHOTBOX (ID,USERNAME,CONTENT,DATETIME_TEXT) " +
                    "VALUES ('"+ sb.getId()+"','Guest said:','"+ sb.getContent()+"','"+ showTime+"');";
            //"VALUES ("+sb.getId()+", "+sb.getUserName()+","+ sb.getContent()+",'abc');";
            //System.out.println("SQL: "+sql);
            stmt.executeUpdate(sql);
            //stmt.close();
            //db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Insert database successfully");
    }
    public void SelectTable(){
        try {
            db.setAutoCommit(false);
            stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SHOTBOX WHERE USERNAME = 'Guest said:'");
            while ( rs.next() ) {
                long id = rs.getLong("ID");
                String  name = rs.getString("USERNAME");
                String  content = rs.getString("CONTENT");
                System.out.println( "" + name +" "+ content);
            }
            rs.close();
            stmt.close();
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("\nSelected successfully\n");
    }
    public void UpdateDataBase(){
        try {
            Scanner s = new Scanner(System.in);
            stmt = db.createStatement();
            sb.setContent(s.nextLine());
            String sql = "UPDATE SHOTBOX " +
                    "SET CONTENT ='"+sb.getContent()+"'" +
                    "WHERE USERNAME = 'Guest said:'";
            stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Updated database successfully\n");
    }
}