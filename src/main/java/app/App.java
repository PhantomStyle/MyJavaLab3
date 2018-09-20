package app;

import appliances.Washer;
import clothes.Clothes;
import clothes.dao.ClothesDao;
import tools.Color;
import tools.temperature.IronTemperature;
import tools.temperature.WashingTemperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:E:/FOR_WORK/Универъ/JavaUnik/Lab3/src/main/resources/db;DB_CLOSE_DELAY=-1";

    static final String USER = "";
    static final String PASS = "";

    private static ClothesDao clothesDao;
    private static Connection conn;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static Washer load() throws SQLException {
        Clothes clothes = new Clothes(new WashingTemperature(40), new IronTemperature(70), Color.COLOR);
        clothesDao.create(conn, clothes);
        return new Washer(clothes);
    }

    public static Washer load(int washingTemperature, int ironTemperature, Color color) throws SQLException {
        Clothes clothes = new Clothes(new WashingTemperature(washingTemperature)
                , new IronTemperature(ironTemperature)
                , color);
        clothesDao.create(conn, clothes);
        return new Washer(clothes);
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName(JDBC_DRIVER);
        clothesDao = new ClothesDao();
        logger.info("Start app");
        conn = DriverManager.getConnection(DB_URL);
        try {
            Washer washer;
            System.out.println("Do you want custom options?");
            String enterVar = reader.readLine();
            if (enterVar.equals("1")) {
                washer = App.load();
            } else if (enterVar.equals("0")) {
                System.out.println("Enter washing temperature");
                int washingTemperature = Integer.parseInt(reader.readLine());
                System.out.println("Enter iron temperature");
                int ironTemperature = Integer.parseInt(reader.readLine());
                System.out.println("Choose DARK, LIGHT, COLOR");
                Color color;
                switch (reader.readLine().toLowerCase().trim()) {
                    case "dark":
                        color = Color.DARK;
                        break;

                    case "light":
                        color = Color.LIGHT;
                        break;

                    case "color":
                        color = Color.COLOR;
                        break;

                    default:
                        throw new RuntimeException("Error of cloth's type");
                }

                washer = App.load(washingTemperature, ironTemperature, color);

            } else throw new RuntimeException("Wrong entering");
            for (int i = 0; i < 3; i++) {
                System.out.print("Wahing ");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.write(13);
            }
            System.out.println(washer.toString());
            logger.info(washer.toString());
        } catch (Exception ex) {
            logger.error("Error: " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
        logger.info("Stop app");
    }
}
