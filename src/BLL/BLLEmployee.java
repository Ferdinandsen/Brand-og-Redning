package BLL;

import BE.BELogin;
import DAL.DALEmployee;
import DAL.DALLogin;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Team Kawabunga
 */
public class BLLEmployee {
    DAL.DALEmployee dalEmployee;
    DAL.DALLogin dalLogin;
    private static BLLEmployee m_instance = null;

    private BLLEmployee() {
        try {
            dalEmployee = DALEmployee.getInstance();
            dalLogin = DALLogin.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i bllEmployee " + e);
        }
    }

    public static BLLEmployee getInstance() {
        if (m_instance == null) {
            m_instance = new BLLEmployee();
        }
        return m_instance;
    }

    public boolean doesUserExist(String name, char[] password) {
        for (BELogin login : dalLogin.getAllLogins()) {
            if (login.getMedarbejder().getFornavn().trim().equalsIgnoreCase(name.trim()) && isPasswordCorrect(password, login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordCorrect(char[] password, BELogin login) {
        boolean isCorrect;
        char[] correctPassword = login.getKode().toCharArray();

        if (password.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(password, correctPassword);
        }
        //Fjerner koden fra hukommelsen, så man ikke kan dumpe den
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    public BELogin getLogin(String name) {
        for (BELogin login : dalLogin.getAllLogins()) {
            if (name.trim().equalsIgnoreCase(login.getMedarbejder().getFornavn().trim())) {
                return login;
            }
        }
        return null;
    }
}
