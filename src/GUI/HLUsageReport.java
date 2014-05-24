package GUI;

import BE.BEAlarm;
import BE.BEMateriel;
import BE.BEUsage;
import BLL.BLLUsage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Team Kawabunga
 */
public class HLUsageReport extends javax.swing.JDialog {

    BLLUsage bllusage;
    BEUsage localUsage;
    ArrayList<BEUsage> localAllUsages = new ArrayList<>();
    BEMateriel m;
    ArrayList<BEMateriel> allMats = new ArrayList<>();
    private JPanel main;
    private int height;
    private int width = 450;
    BEAlarm alarm;
    boolean update = false;
    ArrayList<ForbrugPanel> forbrug = new ArrayList<>();

    /**
     * Creates new form HLErrorRapport
     *
     * @param a
     */
    public HLUsageReport(BEAlarm a) {
        alarm = a;
        bllusage = BLLUsage.getInstance();
        allMats = bllusage.getAllMats();
        height = allMats.size() * 25;
        this.setSize(width, height);
        this.setTitle("Holdleder Forbrugs Rapport");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        main = getBorderLayout();
        this.add(main);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    public HLUsageReport(BEAlarm a, ArrayList<BEUsage> allUsages) {
        alarm = a;
        update = true;
        bllusage = BLLUsage.getInstance();
        allMats = bllusage.getAllMats();
        height = allMats.size() * 25;
        this.setSize(width, height);
        this.setTitle("Holdleder Forbrugs Rapport");
        this.setLocationRelativeTo(null);
        main = getBorderLayout();
        this.add(main);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        localAllUsages = allUsages;
        fillInfo();
    }

    private void fillInfo() {
        for (ForbrugPanel test : forbrug) {
            for (BEUsage usage : localAllUsages) {
                if (usage.getMateriel().getName().equalsIgnoreCase(test.name)) {
                    test.setText(String.valueOf(usage.getAmount()));
                }
            }
        }
    }

    private JPanel getBorderLayout() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(getGridLayout(), BorderLayout.CENTER);
        p.add(getFlowLayoutSouth(), BorderLayout.SOUTH);
        return p;
    }

    private JPanel getGridLayout() {
        JPanel p = new JPanel();
        GridLayout gl = new GridLayout();
        p.setLayout(gl);
        p.add(getGroupLayout());
        return p;
    }

    private JPanel getFlowLayoutSouth() {
        JPanel p = new JPanel();
        FlowLayout fl = new FlowLayout();
        p.setLayout(fl);
        JButton a = new JButton(("Intet Forbrug"));
        JButton b = new JButton(("Bekræft"));
        if (update) {
            a.setText("Tilbage");
            b.setText("Opdater forbrug");
        }
        p.add(a);
        p.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usage();
                JOptionPane.showMessageDialog(null, "Du har nu registreret forbruget - tak!");
                dispose();
            }
        });

        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        return p;
    }

    private JPanel getGroupLayout() {
        allMats = bllusage.getAllMats();
        int modsize = allMats.size() % 2;
        JPanel p = new JPanel();
        for (BEMateriel mat : allMats) {
            ForbrugPanel panel = new ForbrugPanel(mat);
            JTextField tf = panel.getTF();
            GroupLayout layout = new GroupLayout(panel);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(panel.getLBL()));
            layout.setVerticalGroup(layout.createSequentialGroup().addComponent(tf));
            p.add(panel);
            forbrug.add(panel);
        }
        if (modsize != 0) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(205, 20));
            p.add(panel);
        }
        return p;
    }

    /**
     * Update fortæller om det er IL eller HL der bruger formen - hvis HL IKKE
     * har lavet en, laver IL en ny Usage.
     */
    private void usage() {
        BEUsage bu = null;
        for (ForbrugPanel test : forbrug) {
            if (update && !localAllUsages.isEmpty()) {
                for (BEUsage u : bllusage.getAllUsages()) {
                    if (u.getMateriel().getName().equalsIgnoreCase(test.name)) {
                        u.setAmount(test.getAmount());
                        bllusage.updateUsageReport(u);
                    }
                }
            } else {
                for (BEMateriel mat : allMats) {
                    if (mat.getName().equalsIgnoreCase(test.name) && test.getAmount() != 0) {
                        bu = new BEUsage(mat, test.getAmount(), alarm);
                        bllusage.createReport(bu);
                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private class ForbrugPanel extends JPanel {

        String name;
        int amount = 0;
        JLabel lbl;
        JTextField tf;

        /**
         * Creates new form ForbrugPanel
         */
        public ForbrugPanel(BEMateriel m) {

            lbl = new JLabel();
            tf = new JTextField();
            if (localUsage != null) {
                for (BEMateriel mat : allMats) {
                    if (localUsage.getMateriel() == mat) {
                        tf.setText(String.valueOf(mat.getAmount()));
                    }
                }
            }
            name = m.getName();
            lbl.setText(name);
            lbl.setPreferredSize(new Dimension(140, 20));
            tf.setPreferredSize(new Dimension(50, 20));
            tf.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)) || tf.getText().length() >= 6) {
                        e.consume();
                    }
                }
            });
        }

        public JTextField getTF() {
            return tf;
        }

        public void setText(String text) {
            tf.setText(text);
        }

        public JLabel getLBL() {
            return lbl;
        }

        public int getAmount() {
            return !tf.getText().equals("") ? Integer.parseInt(tf.getText()) : 0;
        }
    }
}
