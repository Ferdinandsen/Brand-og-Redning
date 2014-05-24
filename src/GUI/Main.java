package GUI;

/**
 *
 * @author Team Kawabunga
 */
public class Main {

    public static void main(String[] args) {
//        FactoryViewform.createViewformMain().setVisible(true);
        FactoryViewform.createCheckOutView().setVisible(true);
//        AddAppearanceView main = new AddAppearanceView();
//        CheckInView main = new CheckInView();
//        MainView main = new MainView();
//        HLAfterAction1 main = HLAfterAction1.getInstance();
//        HLErrorReport main = new HLErrorReport());
//        ILIndsats main = new ILIndsats();
//        Administration main = new Administration();
//        main.setLocationRelativeTo(null);
//        main.setVisible(true);

        FactoryViewform factory = new FactoryViewform();
        IViewType frame = factory.getFrame("mainview");
        frame.show();

    }
}
