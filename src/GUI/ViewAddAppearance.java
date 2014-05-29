package GUI;

import BE.BEAlarm;
import BE.BEFireman;
import BE.BELogin;
import BE.BEVehicle;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLFireman;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Kawabunga
 */
public class ViewAddAppearance extends javax.swing.JDialog {

    BLLAppearance bllAppearance;
    BLLFireman bllFireman;
    BLLAlarm bllAlarm;
    BLLVehicle bllVehicle;
    BEAlarm localAlarm;
    BELogin localLog;

    public ViewAddAppearance(BEAlarm alarm, BELogin log) {
        localAlarm = alarm;
        localLog = log;
        bllAppearance = BLLAppearance.getInstance();
        bllFireman = BLLFireman.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();

        initComponents();
        initOtherComponents();
        fillCboxFireman();
        fillCboxAlarm();
        fillCboxVehicle();
        fillCboxKørselstype();

        this.setResizable(false);
        this.setTitle("Tilføj brandmand");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     *
     */
    private void initOtherComponents() {

        btnGroup.add(rbtnStVagt);
        btnGroup.add(rbtnChauffør);
        btnGroup.add(rbtnHoldleder);
        btnOk.setEnabled(false);

        txtCheckUdTid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtCheckUdTid.getText().length() == 2) {
                    txtCheckUdTid.setText(txtCheckUdTid.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtCheckUdTid.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtCheckUdTid.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtCheckUdTid.getText().length() > 0 && txtCheckUdTid.getText().charAt(0) == '2') {
                    if (txtCheckUdTid.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtCheckUdTid.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtCheckUdTid.setText("");
                }
            }
        });

        rbtnChauffør.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });

        rbtnHoldleder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });

        rbtnStVagt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cboxKøretøj.setSelectedIndex(0);
                btnOk.setEnabled(cboxFireman.getSelectedIndex() != 0);
            }
        });
        cboxKøretøj.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnStVagt.isSelected() && cboxKøretøj.getSelectedIndex() != 0) {
                    btnGroup.clearSelection();
                }
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });
        cboxAlarm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                btnOk.setEnabled((cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0) || cboxFireman.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && rbtnStVagt.isSelected());
            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppearance();
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Gathers all the information that the user have put in, to make an
     * appearance Splits the date from dateChooser into hour and min Calls the
     * addApperance in BLLAppearance
     */
    private void addAppearance() {
        if (txtCheckUdTid.getText().length() == 5) {
            boolean hl = rbtnHoldleder.isSelected();
            boolean ch = rbtnChauffør.isSelected();
            boolean st = rbtnStVagt.isSelected();

            BEVehicle veh = null;
            if (cboxKøretøj.getSelectedIndex() != 0) {
                veh = (BEVehicle) cboxKøretøj.getSelectedItem();
            }
            Date date = dateChooser.getDate();
            String[] test = txtCheckUdTid.getText().split(":");
            int hour = Integer.parseInt(test[0]);
            int min = Integer.parseInt(test[1]);
            Timestamp thistime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), hour, min, 0, 0);
            bllAppearance.addAppearance((BEFireman) cboxFireman.getSelectedItem(), (BEAlarm) cboxAlarm.getSelectedItem(), veh, thistime, hl, ch, st, (int) cboxKørselstype.getSelectedItem(), localLog);
            JOptionPane.showMessageDialog(this, cboxFireman.getSelectedItem() + " er nu tilføjet til listen!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Skriv venligst et check ud tidspunkt");
        }
    }

    /**
     * Fills the cboxFiremen with all firemen But first it adds an item "ingen
     * brandmand"
     *
     */
    private void fillCboxFireman() {
        cboxFireman.addItem("Ingen brandmand valgt");
        for (BEFireman fireman : bllFireman.getAllfiremen()) {
            cboxFireman.addItem(fireman);
        }
    }

    /**
     * Fills the cboxAlam with the alarm That the IL have choosen to add an
     * appaerance to
     */
    private void fillCboxAlarm() {
        cboxAlarm.addItem(localAlarm);
    }

    /**
     * Fills the cboxVehicle with all vehicles But first it adds an item "intet
     * køretøj valgt"
     */
    private void fillCboxVehicle() {
        cboxKøretøj.addItem("Intet køretøj valgt");
        for (BEVehicle veh : bllVehicle.getAllVehicles()) {
            cboxKøretøj.addItem(veh);
        }
    }

    /**
     * Fills the cboxKørselstype, with the 2 options 1 "udrykning" 2 "alm
     * kørsel"
     */
    private void fillCboxKørselstype() {
        cboxKørselstype.addItem(1);
        cboxKørselstype.addItem(2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbtnHoldleder = new javax.swing.JRadioButton();
        rbtnChauffør = new javax.swing.JRadioButton();
        rbtnStVagt = new javax.swing.JRadioButton();
        cboxFireman = new javax.swing.JComboBox();
        cboxAlarm = new javax.swing.JComboBox();
        cboxKøretøj = new javax.swing.JComboBox();
        txtCheckUdTid = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboxKørselstype = new javax.swing.JComboBox();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Brandmand:");

        jLabel2.setText("Alarm:");

        jLabel3.setText("Køretøj:");

        jLabel4.setText("Check ud tid:");

        rbtnHoldleder.setText("Holdleder");

        rbtnChauffør.setText("Chauffør");

        rbtnStVagt.setText("ST Vagt");

        btnOk.setText("OK");

        btnCancel.setText("Cancel");

        jLabel5.setText("Køreselstype:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnChauffør)
                                    .addComponent(rbtnHoldleder)
                                    .addComponent(rbtnStVagt)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboxAlarm, javax.swing.GroupLayout.Alignment.LEADING, 0, 50, Short.MAX_VALUE)
                                        .addComponent(cboxFireman, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxKørselstype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCheckUdTid, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboxFireman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboxKørselstype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCheckUdTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(rbtnHoldleder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnChauffør)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnStVagt))
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox cboxAlarm;
    private javax.swing.JComboBox cboxFireman;
    private javax.swing.JComboBox cboxKøretøj;
    private javax.swing.JComboBox cboxKørselstype;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton rbtnChauffør;
    private javax.swing.JRadioButton rbtnHoldleder;
    private javax.swing.JRadioButton rbtnStVagt;
    private javax.swing.JTextField txtCheckUdTid;
    // End of variables declaration//GEN-END:variables
}
