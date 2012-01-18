package org.moparscape.elysium.external;

import org.moparscape.elysium.entity.InvItem;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Shop {

    public int buyModifier;
    public boolean general;
    public String greeting;
    public ArrayList<InvItem> items;
    public int minX, maxX, minY, maxY;
    public String name;
    public String[] options;
    public int respawnRate;
    public int sellModifier;
}
