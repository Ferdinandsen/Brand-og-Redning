package GUI;

/**
 *
 * @author Team Kawabunga
 */
public class Main {

    public static void main(String[] args) {
        MainView main = new MainView();
//        HLAfterAction1 main = HLAfterAction1.getInstance();
//        CheckUdView main = new CheckUdView();
//        CheckOutView main = new CheckOutView();
//        HLErrorReport main = new HLErrorReport());
//        LoginView main = new LoginView();
//        HLUsageReport main = new HLUsageReport(null);
        
        ILIndsats main = new ILIndsats(null);
        main.setVisible(true);
    }
}
