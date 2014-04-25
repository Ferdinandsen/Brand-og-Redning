package BLL;

import BE.BEKØTJ;
import DAL.DALKØTJ;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class BLLKØTJ {

    DAL.DALKØTJ dalkøtj;
    private static BLLKØTJ m_instance = null;

    private BLLKØTJ() {
        try {
            dalkøtj = DALKØTJ.getInstance();
        } catch (SQLException e) {
            System.out.println("SQL fejl i BLLKØTJ" + e);
        }
    }

    public static BLLKØTJ getInstance() {
        if (m_instance == null) {
            m_instance = new BLLKØTJ();
        }
        return m_instance;
    }

    public ArrayList<BEKØTJ> GetKØTJ() {
        return dalkøtj.getKøtj();
    }
}
