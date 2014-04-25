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

    private int medarbejderNo;
    private String fornavn;
    private String mellemnavn;
    private String efternavn;
    private String cpr;
    private String portræt;
    private BEAddress adressRef;
    private boolean isFriviligBrand;
    
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

    /**
     * @return the medarbejderNo
     */
    public int getMedarbejderNo() {
        return medarbejderNo;
    }

    /**
     * @param medarbejderNo the medarbejderNo to set
     */
    public void setMedarbejderNo(int medarbejderNo) {
        this.medarbejderNo = medarbejderNo;
    }

    /**
     * @return the fornavn
     */
    public String getFornavn() {
        return fornavn;
    }

    /**
     * @param fornavn the fornavn to set
     */
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    /**
     * @return the mellemnavn
     */
    public String getMellemnavn() {
        return mellemnavn;
    }

    /**
     * @param mellemnavn the mellemnavn to set
     */
    public void setMellemnavn(String mellemnavn) {
        this.mellemnavn = mellemnavn;
    }

    /**
     * @return the efternavn
     */
    public String getEfternavn() {
        return efternavn;
    }

    /**
     * @param efternavn the efternavn to set
     */
    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    /**
     * @return the cpr
     */
    public String getCpr() {
        return cpr;
    }

    /**
     * @param cpr the cpr to set
     */
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    /**
     * @return the portræt
     */
    public String getPortræt() {
        return portræt;
    }

    /**
     * @param portræt the portræt to set
     */
    public void setPortræt(String portræt) {
        this.portræt = portræt;
    }

    /**
     * @return the adressRef
     */
    public BEAddress getAdressRef() {
        return adressRef;
    }

    /**
     * @param adressRef the adressRef to set
     */
    public void setAdressRef(BEAddress adressRef) {
        this.adressRef = adressRef;
    }

    /**
     * @return the isFriviligBrand
     */
    public boolean isIsFriviligBrand() {
        return isFriviligBrand;
    }

    /**
     * @param isFriviligBrand the isFriviligBrand to set
     */
    public void setIsFriviligBrand(boolean isFriviligBrand) {
        this.isFriviligBrand = isFriviligBrand;
    }
}
