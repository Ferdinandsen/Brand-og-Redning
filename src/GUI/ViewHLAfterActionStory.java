package GUI;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEVehicle;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Team Kawabunga
 */
public class ViewHLAfterActionStory extends javax.swing.JDialog {

    ArrayList<BEAppearance> allappearances;
    BLLAlarm bllAlarm;
    BLLAppearance bllAppearance;
    ArrayList<KørselPanel> køtj = new ArrayList<>();
    BEAlarm localAlarm;

    /**
     * Creates new form HLAfterActionStory
     *
     * @param a
     * @param alarm
     */
    public ViewHLAfterActionStory(ArrayList<BEAppearance> a, BEAlarm alarm) {
        allappearances = a;
        localAlarm = alarm;
        bllAppearance = BLLAppearance.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        initComponents();
        initOtherComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
        this.setTitle("Beretning");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpAlarm = new javax.swing.ButtonGroup();
        rbtnFAlarm = new javax.swing.JRadioButton();
        rbtnBAlarm = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlKørsel = getBorderLayout() ;
        btnBekræft = new javax.swing.JButton();
        txtFGRP = new javax.swing.JTextField();
        txtFDet = new javax.swing.JTextField();
        rbtnNormalAlarm = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rbtnFAlarm.setText("Falsk Alarm");

        rbtnBAlarm.setText("Blind Alarm");

        jLabel1.setText("GruppeNo");

        jLabel2.setText("DetektorNo");

        jLabel3.setText("Kørselstype:");

        pnlKørsel.setLayout(new java.awt.GridLayout(1, 0));

        btnBekræft.setText("Bekræft");

        rbtnNormalAlarm.setText("Normal Alarm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnNormalAlarm)
                            .addComponent(jLabel3)
                            .addComponent(rbtnFAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnBAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(11, 11, 11)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFDet)
                                        .addComponent(txtFGRP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnBekræft)
                                    .addGap(29, 29, 29))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlKørsel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnNormalAlarm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnBAlarm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnFAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFGRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtFDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlKørsel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBekræft)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekræft;
    private javax.swing.ButtonGroup btnGrpAlarm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pnlKørsel;
    private javax.swing.JRadioButton rbtnBAlarm;
    private javax.swing.JRadioButton rbtnFAlarm;
    private javax.swing.JRadioButton rbtnNormalAlarm;
    private javax.swing.JTextField txtFDet;
    private javax.swing.JTextField txtFGRP;
    // End of variables declaration//GEN-END:variables

    private ArrayList<BEVehicle> getVeh() {
        ArrayList<BEVehicle> allvehicles = new ArrayList<>();
        for (BEAppearance a : allappearances) {
            if (a.getVeh() != null && !allvehicles.contains(a.getVeh())) {
                allvehicles.add(a.getVeh());
            }
        }
        return allvehicles;
    }

    private JPanel getBorderLayout() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(getGroupLayout(), BorderLayout.CENTER);
        return p;
    }

    private JPanel getGroupLayout() {
        JPanel p = new JPanel();
        GridLayout gl = new GridLayout(0, 1);
        p.setLayout(gl);
        if (!getVeh().isEmpty()) {
            for (BEVehicle v : getVeh()) {
                KørselPanel panel = new KørselPanel(v);
                GroupLayout layout = new GroupLayout(panel);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(panel.getLBL())
                                .addComponent(panel.combo));
                p.add(panel);
                køtj.add(panel);
            }
        }
        return p;
    }

    private void initOtherComponents() {
        btnGrpAlarm.add(rbtnNormalAlarm);
        btnGrpAlarm.add(rbtnBAlarm);
        btnGrpAlarm.add(rbtnFAlarm);
        btnGrpAlarm.setSelected(rbtnNormalAlarm.getModel(), true);

        txtFGRP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtFGRP.getText().length() >= 5) {
                    e.consume();
                }
                if (!(Character.isDigit(e.getKeyChar()))) {
                    e.consume();
                }
                if ((e.getKeyCode() == KeyEvent.VK_TAB)) {
                    transferFocus();
                    e.consume();
                }
            }
        });

        txtFDet.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtFDet.getText().length() >= 5) {
                    e.consume();
                }
                if (!(Character.isDigit(e.getKeyChar()))) {
                    e.consume();
                }
                if ((e.getKeyCode() == KeyEvent.VK_TAB)) {
                    transferFocus();
                    e.consume();
                }
            }
        });

        btnBekræft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                confirm();
            }
        });
    }

    private void confirm() {
        getDataToIL();
        JOptionPane.showMessageDialog(null, "Tak for bekræftigelsen");
        dispose();
        ViewHLUsageReport frame = new ViewHLUsageReport(localAlarm);
        frame.setVisible(true);
    }

    private void getDataToIL() {
        String alarmtype = "Normal Alarm";
        int gruppeNo;
        int detekNo;

        if (rbtnNormalAlarm.isSelected()) {
            alarmtype = "Normal Alarm";
        }

        if (rbtnBAlarm.isSelected()) {
            alarmtype = "Blind Alarm";
        }

        if (rbtnFAlarm.isSelected()) {
            alarmtype = "Falsk Alarm";
        }

        gruppeNo = txtFGRP.getText().isEmpty() ? 0 : Integer.parseInt(txtFGRP.getText());
        detekNo = txtFDet.getText().isEmpty() ? 0 : Integer.parseInt(txtFDet.getText());
        localAlarm.setAlarmType(alarmtype);
        localAlarm.setGruppeNo(gruppeNo);
        localAlarm.setDetekterNo(detekNo);
        bllAlarm.updateAlarm(localAlarm);
        for (KørselPanel kør : køtj) {
            for (BEAppearance a : allappearances) {
                if (a.getVeh() != null && a.getVeh().getOdinnummer() == kør.name) {
                    bllAppearance.updateKørselType(a, kør.getselected());

                }
            }
        }
    }

    private class KørselPanel extends JPanel {

        int name = 0;
        JLabel lbl;
        JComboBox<Object> combo;
        BEVehicle vehicle;

        public KørselPanel(BEVehicle v) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setPreferredSize(new Dimension(120, 45));
            if (v != null) {
                name = v.getOdinnummer();
            }
            vehicle = v;
            lbl = new JLabel();
            lbl.setPreferredSize(new Dimension(40, 15));
            lbl.setText(String.valueOf(name));
            combo = new JComboBox<>();
            combo.addItem(1);
            combo.addItem(2);
            combo.addItem("Ikke i brug");
            combo.setSelectedIndex(0);
        }

        public int getselected() {
            return combo.getSelectedItem().equals("Ikke i brug") ? 3 : (int) combo.getSelectedItem();
        }

        public JLabel getLBL() {
            return lbl;
        }

        public JComboBox getCombo() {
            return combo;
        }
    }
}
