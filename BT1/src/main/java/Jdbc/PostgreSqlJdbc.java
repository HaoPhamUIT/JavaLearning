package Jdbc; /**
 * Created by hao-pham on 6/12/17.
 */


import object.ShotBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;


public class PostgreSqlJdbc {

    Connection db = null;
    Statement stmt = null;
    public ShotBox sb =new ShotBox();

    public static void main(String args[]) {
        PostgreSqlJdbc pg= new PostgreSqlJdbc();
       // pg.DataBaseConnection();
        //pg.CreateTable();
        //pg.InsertDataBase();
        pg.dataBaseConnection();
        pg.selectTable();
        pg.dataBaseConnection();
        pg.insertDataBase();
        pg.selectTable();

    }

    public void dataBaseConnection(){
        try {
            Class.forName("org.postgresql.Driver"); //load driver
            db = getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=public",
                    "postgres", "123456");
        }
        catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }
        System.out.println("Opened database successfully\n");
    }
    public void createTable(){
        try {
            stmt = db.createStatement();
            String sql = "CREATE TABLE SHOTBOX " +
                    "(ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," +
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
    public void insertDataBase(){
        try {
            Scanner s = new Scanner(System.in);
            stmt = db.createStatement();

            sb.setContent(s.nextLine());

            Date Time = new Date();
            SimpleDateFormat setTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
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
    public void selectTable(){
        try {
            db.setAutoCommit(false);
            stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SHOTBOX");
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
    public void updateDataBase(){
        try {
            Scanner s = new Scanner(System.in);
            stmt = db.createStatement();
            sb.setContent(s.nextLine());
            String sql = "UPDATE SHOTBOX " +
                    "SET CONTENT ='"+sb.getContent()+"'";
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