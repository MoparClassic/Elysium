package org.moparscape.elysium.data;

import org.moparscape.elysium.def.ItemDef;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface DataLayer {

    ItemDef[] loadItemDefinitions() throws SQLException;
}
