import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by HaoPham on 6/12/2017.
 */
public class SpringJdbc {
    private static Connection con;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    public static void main(String[] args) {
        Connection c = getConnection();
        if (c == null) {
            System.out.println("something wrong");
        } else {
            System.out.println("ok");
        }
    }
    public static Connection getConnection() {
        con = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream("cfg.properties"));
            URL = properties.getProperty("url"); // lấy giá trị url trong file
            USERNAME = properties.getProperty("username"); // lấy giá trị user trong file
            PASSWORD = properties.getProperty("password"); // lấy giá trị password trong file
            // driver register
            DriverManager.registerDriver(new org.postgresql.Driver());
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD); // kết nối vào cơ sở dữ liệu MySQL
        } catch (Exception  ex) {
            Logger.getLogger(SpringJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (con);
    }
}
