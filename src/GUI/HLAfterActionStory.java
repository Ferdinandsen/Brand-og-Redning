package GUI;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEVehicle;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Team Kawabunga
 */
public class HLAfterActionStory extends javax.swing.JFrame {

    ArrayList<BEAppearance> allappearances;
    BLLAlarm bllAlarm;
    BLLAppearance bllAppearance;
    ArrayList<KørselPanel> køtj = new ArrayList<>();
    int køtjtype = 1;
    BEAlarm localAlarm;
//    private JPanel main;

    /**
     * Creates new form HLAfterActionStory
     *
     * @param a
     * @param alarm
     */
    public HLAfterActionStory(ArrayList<BEAppearance> a, BEAlarm alarm) {
        allappearances = a;
        localAlarm = alarm;
        bllAppearance = BLLAppearance.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        initComponents();
        fillCBox();
        initOtherComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpAlarm = new javax.swing.ButtonGroup();
        cboxIkkeIBrug = new javax.swing.JComboBox();
        rbtnFAlarm = new javax.swing.JRadioButton();
        rbtnBAlarm = new javax.swing.JRadioButton();
        rbtnIkkeIBrug = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlKørsel = getGroupLayout();
        btnBekræft = new javax.swing.JButton();
        txtFGRP = new javax.swing.JTextField();
        txtFDet = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rbtnFAlarm.setText("Falsk Alarm");

        rbtnBAlarm.setText("Blind Alarm");

        rbtnIkkeIBrug.setText("Ikke i Brug");

        jLabel1.setText("GruppeNo");

        jLabel2.setText("DetektorNo");

        jLabel3.setText("Kørselstype:");

        javax.swing.GroupLayout pnlKørselLayout = new javax.swing.GroupLayout(pnlKørsel);
        pnlKørsel.setLayout(pnlKørselLayout);
        pnlKørselLayout.setHorizontalGroup(
            pnlKørselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlKørselLayout.setVerticalGroup(
            pnlKørselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );

        btnBekræft.setText("Bekræft");

        txtFGRP.setText("jTextField1");

        txtFDet.setText("jTextField2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cboxIkkeIBrug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rbtnBAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbtnFAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(rbtnIkkeIBrug, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlKørsel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFGRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnBekræft)
                .addGap(0, 68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnBAlarm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnFAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnIkkeIBrug)
                .addGap(5, 5, 5)
                .addComponent(cboxIkkeIBrug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFGRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlKørsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBekræft))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekræft;
    private javax.swing.ButtonGroup btnGrpAlarm;
    private javax.swing.JComboBox cboxIkkeIBrug;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pnlKørsel;
    private javax.swing.JRadioButton rbtnBAlarm;
    private javax.swing.JRadioButton rbtnFAlarm;
    private javax.swing.JRadioButton rbtnIkkeIBrug;
    private javax.swing.JTextField txtFDet;
    private javax.swing.JTextField txtFGRP;
    // End of variables declaration//GEN-END:variables

    private ArrayList<BEVehicle> getVeh() {
        ArrayList<BEVehicle> allvehicles = new ArrayList<>();
        for (BEAppearance a : allappearances) {
            if (!allvehicles.contains(a.getVeh())) {
                allvehicles.add(a.getVeh());
            }
        }
        return allvehicles;
    }

    private JPanel getGroupLayout() {
        JPanel p = new JPanel();
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
                getContentPane().add(panel);
                pack();
                p.add(panel);
                køtj.add(panel);
            }
        }
        return p;
    }

    private void initOtherComponents() {

        cboxIkkeIBrug.setEnabled(false);
        btnGrpAlarm.add(rbtnBAlarm);
        btnGrpAlarm.add(rbtnFAlarm);
        btnGrpAlarm.add(rbtnIkkeIBrug);
        

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
                if (!txtFGRP.getText().isEmpty() && !txtFDet.getText().isEmpty()) {
                    getDataToIL();
                    JOptionPane.showMessageDialog(null, "Tak for bekræftigelsen");
                    HLUsageReport frame = new HLUsageReport(localAlarm);
                    frame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Du skal indtaste alle oplysniger for at kunne bekræfte");
                }
            }
        });

        rbtnIkkeIBrug.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                if (rbtnIkkeIBrug.isSelected()) {
                    cboxIkkeIBrug.setEnabled(true);
                    pnlKørsel.setVisible(false);
                } else if (!rbtnIkkeIBrug.isSelected()) {
                    cboxIkkeIBrug.setEnabled(false);
                    pnlKørsel.setVisible(true);
                }
            }
        });
        getGroupLayout();
    }

    private void fillCBox() {
        for (BEVehicle v : getVeh()) {
            cboxIkkeIBrug.addItem(v);
        }
    }

    private int getKørselType() {
        return køtjtype;
    }

    private void getDataToIL() {
        String alarmtype = "Normal Alarm";
        int gruppeNo;
        int detekNo;

        if (rbtnBAlarm.isSelected()) {
            alarmtype = "Blind Alarm";
        }
        if (rbtnFAlarm.isSelected()) {
            alarmtype = "Falsk Alarm";
        }
        if (rbtnIkkeIBrug.isSelected()) {
            alarmtype = "Normal Alarm";
        }

        gruppeNo = Integer.parseInt(txtFGRP.getText());
        detekNo = Integer.parseInt(txtFDet.getText());
        localAlarm.setAlarmType(alarmtype);
        localAlarm.setGruppeNo(gruppeNo);
        localAlarm.setDetekterNo(detekNo);
        bllAlarm.updateAlarm(localAlarm);
        for (BEAppearance a : allappearances) {
            bllAppearance.updateKørselType(a, getKørselType());
        }
    }

    private class KørselPanel extends JPanel {

        int name = 0;
        JLabel lbl;
        JComboBox<Object> combo;
        BEVehicle vehicle;

        public KørselPanel(BEVehicle v) {
            this.setPreferredSize(new Dimension(100, 40));
            if (v != null) {
                name = v.getOdinnummer();
            }
            vehicle = v;
            lbl = new JLabel();
            lbl.setPreferredSize(new Dimension(40, 15));
            combo = new JComboBox<>();
            combo.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent ie) {
                    køtjtype = (int) combo.getSelectedItem();
                }
            });

            lbl.setText(String.valueOf(name));
            combo.addItem(1);
            combo.addItem(2);
            combo.setSelectedIndex(0);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        public JLabel getLBL() {
            return lbl;
        }

        public JComboBox getCombo() {
            return combo;
        }
    }
}
