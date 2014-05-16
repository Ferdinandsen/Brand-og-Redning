package GUI;

import BE.BEAlarm;
import BE.BEFireman;
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
import javax.swing.Action;

/**
 *
 * @author Kaj
 */
public class AddAppearanceView extends javax.swing.JFrame {

    BLLAppearance bllAppearance;
    BLLFireman bllFireman;
    BLLAlarm bllAlarm;
    BLLVehicle bllVehicle;

    public AddAppearanceView() {
        bllAppearance = BLLAppearance.getInstance();
        bllFireman = BLLFireman.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        this.setResizable(false);
        initComponents();
        initOtherComponents();
        fillCboxFireman();
        fillCboxAlarm();
        fillCboxVehicle();
    }

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
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });

        rbtnHoldleder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });

        rbtnStVagt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cboxKøretøj.setSelectedIndex(0);
                btnOk.setEnabled(cboxAlarm.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });
        cboxKøretøj.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnStVagt.isSelected() && cboxKøretøj.getSelectedIndex() != 0) {
                    btnGroup.clearSelection();
                }
                btnOk.setEnabled(cboxKøretøj.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0);
            }
        });
        cboxAlarm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                btnOk.setEnabled((cboxAlarm.getSelectedIndex() != 0 && cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0) || cboxFireman.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && rbtnStVagt.isSelected());
            }
        });
//        cboxFireman.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cboxKøretøj.addItemListener(new ItemListener() {
//                    @Override
//                    public void itemStateChanged(ItemEvent e) {
//                        btnOk.setEnabled((cboxAlarm.getSelectedIndex() != 0 && cboxKøretøj.getSelectedIndex() != 0 && cboxFireman.getSelectedIndex() != 0) || cboxFireman.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0 && rbtnStVagt.isSelected());
//                    }
//                });
//            }
//        });
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppearance();
            }
        });

    }

    private void addAppearance() {
        boolean hl = rbtnHoldleder.isSelected();
        boolean ch = rbtnChauffør.isSelected();
        boolean st = rbtnStVagt.isSelected();
        
        BEVehicle veh = null;
        if (cboxKøretøj.getSelectedIndex() != 0) {
            veh = (BEVehicle) cboxKøretøj.getSelectedItem();
        }
        bllAppearance.addAppearance((BEFireman) cboxFireman.getSelectedItem(), (BEAlarm) cboxAlarm.getSelectedItem(), veh, txtCheckUdTid.getText(), hl, ch, st);
    }

    private void fillCboxFireman() {
        cboxFireman.addItem("Ingen brandmand valgt");
        for (BEFireman fireman : bllFireman.getAllfiremen()) {
            cboxFireman.addItem(fireman);
        }

    }

    private void fillCboxAlarm() {
        cboxAlarm.addItem("Ingen alarm valgt");
        for (BEAlarm alarm : bllAlarm.getAllAlarms()) {
            cboxAlarm.addItem(alarm);
        }
    }

    private void fillCboxVehicle() {
        cboxKøretøj.addItem("Intet køretøj valgt");
        for (BEVehicle veh : bllVehicle.getAllVehicles()) {
            cboxKøretøj.addItem(veh);
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Brandmand:");

        jLabel2.setText("Alarm:");

        jLabel3.setText("Køretøj:");

        jLabel4.setText("Check ud tid:");

        rbtnHoldleder.setText("Holdleder");

        rbtnChauffør.setText("Chauffør");

        rbtnStVagt.setText("ST Vagt");

        btnOk.setText("OK");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxFireman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckUdTid, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnChauffør)
                            .addComponent(rbtnHoldleder)
                            .addComponent(rbtnStVagt)))
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
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
                    .addComponent(jLabel4)
                    .addComponent(txtCheckUdTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnHoldleder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnChauffør)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnStVagt)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton rbtnChauffør;
    private javax.swing.JRadioButton rbtnHoldleder;
    private javax.swing.JRadioButton rbtnStVagt;
    private javax.swing.JTextField txtCheckUdTid;
    // End of variables declaration//GEN-END:variables
}
