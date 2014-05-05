package GUI;

import BE.BEFireman;
import BLL.BLLFireman;
import BLL.BLLTimelist;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Team Kawabunga
 */
public class CheckInView extends javax.swing.JFrame {

    BLLFireman bllFireman;
    BLLTimelist bllTimelist;
    ArrayList<BEFireman> allFiremen = new ArrayList<>();
    ArrayList<firemanButton> allFireManButtons = new ArrayList<>();
    JPanel main;
    int width = 5;
    int height = allFiremen.size() / width;

    public CheckInView() {

        bllTimelist = BLLTimelist.getInstance();
        bllFireman = BLLFireman.getInstance();
        this.setUndecorated(true);
        setResizable(false);
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        main = getBorderLayout();
        this.setTitle("Fireman check in screen");
        this.setLocationRelativeTo(null);
        this.add(main);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Timer timer = new Timer();
        timer.schedule(new SayHello(this, allFireManButtons), 0, 3000);

    }

    private JPanel getBorderLayout() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(getGridLayout(), BorderLayout.CENTER);
        return p;
    }

    private Component getGridLayout() {
        JPanel p = new JPanel();
        GridLayout gl = new GridLayout(width, height - 1);
        gl.setVgap(40);
        gl.setHgap(20);
        p.setLayout(gl);
        allFiremen = bllFireman.getAllfiremen();
        for (BEFireman fireman : allFiremen) {

            firemanButton b = new firemanButton(fireman);

            b.setIcon(new ImageIcon(((new ImageIcon("images/" + fireman.getMedarbjeder().getPortræt())).getImage()).getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH)));
            b.setFocusable(false);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    firemanButton fb = (firemanButton) e.getSource();
                    changebit(fb);
                    if (fb.localFireman.isCheckedin()) { //hvis han skal til at logge ind
                        bllTimelist.createCheckInTimestamp(fb.localFireman);
                    } else { // hvis han skal til at logge ud
                        CheckOutView frame = new CheckOutView(fb.localFireman);
                        frame.setModal(true);
                        frame.setVisible(true);
                    }
                }

                private void changebit(firemanButton fb) {
                    fb.changebit();
                }
            });
            allFireManButtons.add(b);
            p.add(b);
        }
        return p;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private class firemanButton extends JButton {

        String name;
        BEFireman localFireman;

        public firemanButton(BEFireman fireman) {
            localFireman = fireman;
            this.name = fireman.getMedarbjeder().getFornavn();
            this.setBackground(getColor());
            this.setText(name);
            System.out.println("brandmanden er " + localFireman.isCheckedin());
        }

        private Color getColor() {
            if (localFireman.isCheckedin()) {
                return Color.RED;
            }
            return Color.GREEN;
        }

        private void setColor() {
            this.setBackground(getColor());
            repaint();
        }

        public void changebit() {
            localFireman.setIsCheckedin(!localFireman.isCheckedin());
            bllFireman.changeBit(localFireman);
            setColor();

        }

        public void updateLocalFireman(BEFireman fm) {
            localFireman = fm;
            this.setBackground(getColor());
        }
    }

    class SayHello extends TimerTask {

        ArrayList<firemanButton> localFiremen;
        CheckInView test;

        private SayHello(CheckInView aThis, ArrayList<firemanButton> firemen) {
            localFiremen = firemen;
            test = aThis;
        }

        @Override
        public void run() {
            bllTimelist.update();
            bllFireman.update();



            for (firemanButton btn : localFiremen) {
                for (BEFireman fm : bllFireman.getAllfiremen()) {
                    if (fm.getMedarbjeder().getMedarbejderNo() == btn.localFireman.getMedarbjeder().getMedarbejderNo()) {
                        btn.updateLocalFireman(fm);
                    }
                }
            }
            
            System.out.println("opdateret");
        }
    }
}
