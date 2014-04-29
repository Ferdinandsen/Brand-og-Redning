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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CheckInView extends javax.swing.JFrame {

    BLLFireman bllFireman;
    BLLTimelist bllTimelist;
    ArrayList<BEFireman> allFiremen = new ArrayList<>();
    int amount;
    JPanel main;
    int width = 5;
    int height = amount / width;

    public CheckInView() {
        bllFireman = BLLFireman.getInstance();
        bllTimelist = BLLTimelist.getInstance();
        this.setUndecorated(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        amount = BEFireman.getAmount();
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setResizable(false);
        main = getBorderLayout();
        this.setTitle("Fireman check in screen");
        this.setLocationRelativeTo(null);
        this.add(main);
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
            JButton b = new firemanButton(fireman);
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
    }
}
