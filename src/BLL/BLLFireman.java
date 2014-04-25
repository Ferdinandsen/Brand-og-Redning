/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.DALFireman;
import java.sql.SQLException;

/**
 *
 * @author Jakob
 */
public class BLLFireman {
 
        
    DALFireman dalFireman;
    private static BLLFireman m_instance = null;

    private BLLFireman() {
        try {
            dalFireman = DALFireman.getInstance();
        } catch (SQLException e) {
            System.out.println("database connection fail");
        }
    }
    public static BLLFireman getInstance(){
        if (m_instance == null){
            m_instance = new BLLFireman();
        }
        return m_instance;
    }
}
