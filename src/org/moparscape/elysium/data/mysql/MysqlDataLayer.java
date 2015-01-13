package org.moparscape.elysium.data.mysql;

import org.moparscape.elysium.data.DataLayer;
import org.moparscape.elysium.def.ItemDef;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class MysqlDataLayer implements DataLayer {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/elysium", "root", "djl1992");
        return connection;
    }

    public ItemDef[] loadItemDefinitions() throws SQLException {
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement("SELECT MAX(id) FROM item");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int highestId = rs.getInt(1);

        ItemDef[] itemDefs = new ItemDef[highestId + 1];
        rs = ps.executeQuery("SELECT * FROM item");
        //id 	name 	description 	command 	wieldable 	sprite 	base_price 	picture_mask 	stackable 	tradable 	members

        //    public ItemDef(String name, String description,
//        String command, int basePrice, int sprite,
//        boolean stackable, boolean wieldable,
//        int mask, boolean members, boolean tradable) {
        while (rs.next()) {
            ItemDef def = new ItemDef(rs.getString("name"), rs.getString("description"), rs.getString("command"),
                    rs.getInt("base_price"), rs.getInt("sprite"), rs.getBoolean("stackable"),
                    rs.getBoolean("wieldable"), rs.getInt("picture_mask"), rs.getBoolean("members"),
                    rs.getBoolean("tradable"));
            itemDefs[rs.getInt("id")] = def;
        }
        return itemDefs;
    }
}
