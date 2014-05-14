package BLL;

import BE.BEAction;
import DAL.DALAction;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAction {

    private static BLLAction m_instance = null;
    DALAction dalaction;

    private BLLAction() {
        try {
            dalaction = DALAction.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i bllAction " + e);
        }
    }

    public static BLLAction getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAction();
        }
        return m_instance;
    }

    public ArrayList<BEAction> getAllActions() {
        return dalaction.getAllActions();
    }
}
