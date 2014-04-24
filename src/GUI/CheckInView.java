package GUI;

import BE.BEFireman;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CheckInView extends javax.swing.JFrame {
    
    ArrayList<String> stuff = new ArrayList<>();
    ArrayList<BEFireman> allFiremen = new ArrayList<>();
    int amount;
    JPanel main;
    int width = 5;
    int height = amount / width;

    public CheckInView() {
        stuff.add("André");
        stuff.add("Jacob");
        stuff.add("Jakob");
        stuff.add("Andreas");
        stuff.add("Michael");
        stuff.add("Sus");
        stuff.add("Poul");
        stuff.add("Niels");
        stuff.add("Stephen");
        stuff.add("Michelle");
        stuff.add("Steffen");
        stuff.add("Kevin");
        stuff.add("Mikkel");
        stuff.add("Morten");
        stuff.add("Peter");
        this.setUndecorated(true);
        makeBE();
        amount = BEFireman.amount;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        for (BEFireman fireman : allFiremen) {
            JButton b = new firemanButton(fireman);
            b.setText(fireman.getName());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    msgbox((firemanButton) e.getSource());
                }
                private void msgbox(firemanButton fButton) {
                System.out.println(fButton.getMyName());
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

    private void makeBE() {
        for (int i = 0; i < stuff.size(); i++) {
            BEFireman fireman = new BEFireman(stuff.get(i));
            allFiremen.add(fireman);
        }
    }

    private class firemanButton extends JButton {

        String name;

        public firemanButton(BEFireman fireman) {
            this.name = fireman.getName();
        }

        public String getMyName() {
            return name;
        }
    }
}