package org.moparscape.elysium.entity;

import org.moparscape.elysium.DataLayer;
import org.moparscape.elysium.external.ItemDef;
import org.moparscape.elysium.mysql.MysqlDataLayer;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DefinitionHandler {

    private static final ItemDef[] items;

    public static ItemDef getItemDef(int itemId) {
        return items[itemId];
    }

    static {
        try {
            DataLayer data = new MysqlDataLayer();

            items = data.loadItemDefinitions();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
