package Bank_Servaer;

import java.sql.SQLException;

/**
 * Created by Techno Service on 5/25/2018.
 */
public class dbTester {

    public static void main(String[] args){
        Database_Manager db = new Database_Manager();
        try {
            db.connect_to_database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
