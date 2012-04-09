package org.moparscape.elysium;

import org.moparscape.elysium.external.ItemDef;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface DataLayer {

    ItemDef[] loadItemDefinitions() throws SQLException;
}
