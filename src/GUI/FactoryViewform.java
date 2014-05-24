package GUI;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import BE.BEUsage;
import java.util.ArrayList;

/**
 *
 * @author Team KAwabunga
 */
public class FactoryViewform {

    private static ViewHLAfterAction hl_Instance = null;

    public static void FactoryViewform() {
        
    }

    public static ViewMain createViewformMain() {
        return new ViewMain();
    }

    public static ViewILIndsats createViewILIndsats(BELogin log) {
        return new ViewILIndsats(log);
    }

    public static ViewILFremmødeliste createILFremmødeliste(BEAlarm alarm, BELogin log) {
        return new ViewILFremmødeliste(alarm, log);
    }

    public static ViewHLUsageReport createHLUsageReport(BEAlarm alarm, ArrayList<BEUsage> usage) {
        return usage == null ? new ViewHLUsageReport(alarm) : new ViewHLUsageReport(alarm, usage);
    }

    public static ViewHLAfterAction createHLAfterAction(BELogin log) {
        if (hl_Instance == null) {
            hl_Instance = new ViewHLAfterAction(log);
        } else {
            hl_Instance.update();
        }
        return hl_Instance;
    }

    public static ViewHLAfterActionStory createHLAfterActionStory(ArrayList<BEAppearance> app, BEAlarm alarm) {
        return new ViewHLAfterActionStory(app, alarm);
    }

    public static ViewHLErrorReport createHLErrorReport() {
        return new ViewHLErrorReport();
    }

    public static ViewAdministration createAdministration(BELogin log) {
        return new ViewAdministration(log);
    }

    public static ViewAddAppearance createAddAppearanceView(BEAlarm alarm) {
        return new ViewAddAppearance(alarm);
    }

    public static ViewCar createCarView() {
        return new ViewCar();
    }

    public static ViewChangeSalary createChangeSalaryView(BEAppearance app) {
        return new ViewChangeSalary(app);
    }

    public static ViewChangeTime createChangeTimeView(BEAppearance app) {
        return new ViewChangeTime(app);
    }

    public static ViewCheckOut createCheckOutView() {
        return new ViewCheckOut();
    }

    public static ViewFiremanCheckOut createCheckInView(BEFireman fm) {
        return new ViewFiremanCheckOut(fm);
    }

    public static ViewFiremen createFiremenView() {
        return new ViewFiremen();
    }

    public static ViewForbrug createForbrugView() {
        return new ViewForbrug();
    }

    public static ViewAdministrationChoose createAdministrationChooseView(BELogin log) {
        return new ViewAdministrationChoose(log);
    }
}
