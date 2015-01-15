package org.moparscape.elysium.io;

import com.thoughtworks.xstream.XStream;
import org.moparscape.elysium.util.Config;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PersistenceManager {

    private static final XStream xstream = new XStream();

    static {
        setupAliases();
    }

    public static Object load(String filename) {
        try {
            InputStream is = new FileInputStream(new File(Config.CONF_DIR, filename));
            if (filename.endsWith(".gz")) {
                is = new GZIPInputStream(is);
            }
            Object rv = xstream.fromXML(is);
            return rv;
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return null;
    }

    public static void setupAliases() {
        try {
            Properties aliases = new Properties();
            FileInputStream fis = new FileInputStream(new File(Config.CONF_DIR, "aliases.xml"));
            aliases.loadFromXML(fis);
            for (Enumeration<?> e = aliases.propertyNames(); e.hasMoreElements(); ) {
                String alias = (String) e.nextElement();
                Class<?> c = Class.forName((String) aliases.get(alias));
                xstream.alias(alias, c);
            }
        } catch (Exception ioe) {
            System.out.println(ioe);
        }
    }

    public static void write(String filename, Object o) {
        try {
            OutputStream os = new FileOutputStream(new File(Config.CONF_DIR, filename));
            if (filename.endsWith(".gz")) {
                os = new GZIPOutputStream(os);
            }
            xstream.toXML(o, os);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
