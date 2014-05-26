/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author Shadowleet
 */
public class BLLFactory {

    public enum bllType {

        BLLALARM, BLLAPPEARANCE, BLLEMPLOYEE, BLLFIREMAN, BLLREPORT, BLLUSAGE, BLLVEHICLE
    }

    public IBLLType createClass(bllType type) {
        switch (type) {
            case BLLALARM:
                return BLLAlarm.getInstance();
            case BLLAPPEARANCE:
                return BLLAppearance.getInstance();
            case BLLEMPLOYEE:
                return BLLEmployee.getInstance();
            case BLLFIREMAN:
                return BLLFireman.getInstance();
            case BLLREPORT:
                return BLLReport.getInstance();
            case BLLUSAGE:
                return BLLUsage.getInstance();
            case BLLVEHICLE:
                return BLLVehicle.getInstance();
        }
        return null;
    }
}
