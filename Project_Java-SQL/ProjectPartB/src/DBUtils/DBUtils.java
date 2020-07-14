
package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {
  
    private static final String USERNAME = "root";
    private static final String PASS = "";
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/project_part_b?zeroDateTimeBehavior=CONVERT_TO_NULL" +
        "&useUnicode=true&useJDBCCompliantTimezoneShift=true &useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
                                    
    // Connection me thn Vasi 
    public static Connection getConnection () {
        Connection con = null;
        try {
            con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);            
        } catch (SQLException ex) {
          ex.printStackTrace ();
          
        }
        return con;
    }
    
}

