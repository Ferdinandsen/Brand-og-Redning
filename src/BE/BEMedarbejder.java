/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Jakob
 */
public class BEMedarbejder {

    int medarbejderNo;
    String fornavn;
    String mellemnavn;
    String efternavn;
    String cpr;
    String portræt;
    BEAddress adressRef;
    boolean isFriviligBrand;
    
    public BEMedarbejder(int medarbejderNo, String fornavn, String mellemnavn, 
            String efternavn, String cpr, String portræt, BEAddress adressRef, 
            boolean isFriviligBrand){
        
        this.medarbejderNo = medarbejderNo;
        this.fornavn = fornavn;
        this.mellemnavn = mellemnavn;
        this.efternavn = efternavn;
        this.cpr = cpr;
        this.portræt = portræt;
        this.adressRef = adressRef;
        this.isFriviligBrand = isFriviligBrand;
    }
}
