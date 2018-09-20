package clothes.dao;

import app.App;
import clothes.Clothes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.Color;
import tools.temperature.IronTemperature;
import tools.temperature.WashingTemperature;

import java.sql.*;

public class ClothesDao {
    private static final Logger logger = LoggerFactory.getLogger(ClothesDao.class);


    public ClothesDao() throws SQLException {
    }

    public Clothes findOne(Connection conn, Integer id) throws SQLException {
        PreparedStatement stmt;
        String query = "SELECT * FROM CLOTHES WHERE ID=?";
        stmt = conn.prepareStatement(query);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        return new Clothes(new WashingTemperature(rs.getInt(1))
                , new IronTemperature(rs.getInt(2))
                , Color.valueOf(rs.getString(3)));
    }

    public void delete(Connection conn, Integer id) throws SQLException {
        PreparedStatement stmt;
        String query = "DELETE FROM CLOTHES WHERE ID=?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        logger.info("Executing query: " + stmt);
        stmt.executeQuery();
    }

    public void update(Connection conn, Integer id, Clothes clothes) throws SQLException {
        PreparedStatement stmt;
        String query = "UPDATE CLOTHES SET CLOTHES=? WHERE ID=? AND washing_temperature=? AND " +
                "iron_temperature=? AND color=?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, clothes.getWashingTemperature().getTemperature());
        stmt.setInt(2, clothes.getIronTemperature().getTemperature());
        stmt.setString(3, clothes.getColor().toString());
        logger.info("Executing query: " + stmt);
        stmt.executeQuery();
    }

    public void create(Connection conn, Clothes clothes) throws SQLException {
        PreparedStatement stmt;
        String query = "INSERT INTO CLOTHES VALUES (?,?,?, NULL)";
        stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, clothes.getWashingTemperature().getTemperature());
        stmt.setInt(2, clothes.getIronTemperature().getTemperature());
        stmt.setString(3, clothes.getColor().toString());
        logger.info("Executing query: " + stmt);
        stmt.execute();
    }


}
