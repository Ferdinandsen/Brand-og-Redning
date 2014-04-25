package BLL;

import DAL.DALEmployee;
import java.sql.SQLException;

/**
 *
 * @author Shadowleet
 */
public class BLLEmployee {

    DAL.DALEmployee dalEmployee;
    private static BLLEmployee m_instance = null;

    private BLLEmployee() {
        try {
            dalEmployee = DALEmployee.getInstance();
        } catch (SQLException e) {
          //  System.out.println(ex.getMessage());
        }
    }

    public static BLLEmployee getInstance() {
        if (m_instance == null) {
            m_instance = new BLLEmployee();
        }
        return m_instance;
    }
}
