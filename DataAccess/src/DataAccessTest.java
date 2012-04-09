import org.moparscape.elysium.mysql.MysqlDataLayer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class DataAccessTest {

    public static void main(String[] args) throws Exception {
        MysqlDataLayer data = new MysqlDataLayer();
        data.loadItemDefinitions();
//        Connection con = data.connect();
//        PreparedStatement ps = con.prepareStatement("SELECT * FROM item");
//        ResultSet results = ps.executeQuery();
//
//        while (results.next()) {
//            System.out.printf("%d %s\n", results.getInt("id"), results.getString("name"));
//        }
//
//        con.close();
    }
}
