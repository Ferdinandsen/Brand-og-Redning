package GUI;

import Renderes.RenderCombobox;
import BE.BEAlarm;
import BE.BEFireman;
import BE.BEVehicle;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLFireman;
import BLL.BLLVehicle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Team Kawabunga
 */
public class ViewFiremanCheckOut extends javax.swing.JDialog {

    BLLFireman bllFireman;
    BLLVehicle bllvehicle;
    BEFireman localFireman;
    BLLAlarm bllAlarm;
    BLLAppearance bllAppearance;

    /**
     * Creates new form CheckOutView
     *
     * @param fireman
     */
    public ViewFiremanCheckOut(BEFireman fireman) {
        initMetaData();

        bllFireman = BLLFireman.getInstance();
        bllvehicle = BLLVehicle.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        bllAppearance = BLLAppearance.getInstance();
        localFireman = fireman;
        initComponents();
        initComponentSettings();
        initOtherComponents();
        fillCboxVehicle();
        fillCboxAlarm();

        lblTitle.setText("Velkommen: " + fireman.getMedarbjeder().getFornavn() + " " + fireman.getMedarbjeder().getEfternavn());
        pnlFrame.setBackground(new Color(164, 164, 164));
        rbtnChauffør.setBackground(new Color(164, 164, 164));
        rbtnHoldleder.setBackground(new Color(164, 164, 164));
        rbtnStVagt.setBackground(new Color(164, 164, 164));

        RenderCombobox renderer = new RenderCombobox();
        cboxAlarm.setRenderer(renderer);
        cboxVehicle.setRenderer(renderer);
    }

    private void initMetaData() {
        this.setUndecorated(true);
        this.setTitle("CHECK UD");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpCheckOut = new javax.swing.ButtonGroup();
        pnlFrame = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        cboxAlarm = new javax.swing.JComboBox();
        cboxVehicle = new javax.swing.JComboBox();
        rbtnHoldleder = new javax.swing.JRadioButton();
        rbtnChauffør = new javax.swing.JRadioButton();
        rbtnStVagt = new javax.swing.JRadioButton();
        btnAcknowledge = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose.setText("X");

        cboxAlarm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ingen alarm valgt" }));
        cboxAlarm.setMinimumSize(new java.awt.Dimension(113, 20));
        cboxAlarm.setPreferredSize(new java.awt.Dimension(113, 20));

        cboxVehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Intet køretøj valgt" }));

        rbtnHoldleder.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rbtnHoldleder.setText("Holdleder");
        rbtnHoldleder.setMaximumSize(new java.awt.Dimension(200, 23));

        rbtnChauffør.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rbtnChauffør.setText("Chauffør");

        rbtnStVagt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rbtnStVagt.setText("ST Vagt");

        btnAcknowledge.setText("Bekræft");

        btnReset.setText("Nulstil");

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setText("Hej: ");

        javax.swing.GroupLayout pnlFrameLayout = new javax.swing.GroupLayout(pnlFrame);
        pnlFrame.setLayout(pnlFrameLayout);
        pnlFrameLayout.setHorizontalGroup(
            pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFrameLayout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addGroup(pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnStVagt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnHoldleder, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnChauffør, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFrameLayout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFrameLayout.createSequentialGroup()
                        .addGroup(pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxVehicle, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxAlarm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAcknowledge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlFrameLayout.setVerticalGroup(
            pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFrameLayout.createSequentialGroup()
                .addGroup(pnlFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addGap(5, 5, 5)
                .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnHoldleder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnChauffør, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnStVagt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcknowledge, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcknowledge;
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGrpCheckOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox cboxAlarm;
    private javax.swing.JComboBox cboxVehicle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlFrame;
    private javax.swing.JRadioButton rbtnChauffør;
    private javax.swing.JRadioButton rbtnHoldleder;
    private javax.swing.JRadioButton rbtnStVagt;
    // End of variables declaration//GEN-END:variables

    private void initOtherComponents() {

        rbtnChauffør.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAcknowledge.setEnabled(cboxVehicle.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0);
            }
        });

        rbtnHoldleder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAcknowledge.setEnabled(cboxVehicle.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0);
            }
        });

        rbtnStVagt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cboxVehicle.setSelectedIndex(0);
                btnAcknowledge.setEnabled(rbtnStVagt.isSelected() && cboxAlarm.getSelectedIndex() != 0);
            }
        });

        btnAcknowledge.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (btnAcknowledge.isEnabled()) {
                    btnAcknowledge.setBackground(new Color(255, 255, 0));
                } else {
                    btnAcknowledge.setBackground(new Color(255, 255, 102));
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGrpCheckOut.clearSelection();
                cboxAlarm.setSelectedIndex(0);
                cboxVehicle.setSelectedIndex(0);
                btnAcknowledge.setEnabled(false);
            }
        });

        btnAcknowledge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endShift();
                dispose();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cboxVehicle.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnStVagt.isSelected() && cboxVehicle.getSelectedIndex() != 0) {
                    btnGrpCheckOut.clearSelection();
                }
                btnAcknowledge.setEnabled(cboxVehicle.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != 0);
            }
        });

        cboxAlarm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                btnAcknowledge.setEnabled((cboxAlarm.getSelectedIndex() != 0 && cboxVehicle.getSelectedIndex() != 0) || cboxAlarm.getSelectedIndex() != 0 && rbtnStVagt.isSelected());
            }
        });
    }

    private void initComponentSettings() {
        btnReset.setBackground(Color.LIGHT_GRAY);
        btnAcknowledge.setBackground(new Color(255, 255, 102));
        btnClose.setBackground(Color.RED);
        btnAcknowledge.setText("Bekræft");
        btnAcknowledge.setEnabled(false);
        rbtnHoldleder.setEnabled(false);
        rbtnStVagt.setEnabled(true);
        btnGrpCheckOut.add(rbtnStVagt);
        btnGrpCheckOut.add(rbtnChauffør);
        btnGrpCheckOut.add(rbtnHoldleder);

        if (localFireman.isHoldleder()) {
            rbtnHoldleder.setEnabled(true);
        }
        rbtnChauffør.setEnabled(false);
        if (localFireman.isChaffør()) {
            rbtnChauffør.setEnabled(true);
        }
    }

    private void fillCboxVehicle() {
        for (BEVehicle car : bllvehicle.getAllVehicles()) {
            cboxVehicle.addItem(car);
        }
    }

    private void fillCboxAlarm() {
        for (BEAlarm alarm : bllAlarm.getAllAlarms()) {
            cboxAlarm.addItem(alarm);
        }
    }

    private BEAlarm getAlarm() {
        return (BEAlarm) cboxAlarm.getSelectedItem();
    }

    private void endShift() {
        BEVehicle veh =  cboxVehicle.getSelectedIndex() == 0 ? null : (BEVehicle) cboxVehicle.getSelectedItem();
        boolean hl = rbtnHoldleder.isSelected();
        boolean ch = rbtnChauffør.isSelected();
        boolean st = rbtnStVagt.isSelected();

        bllAppearance.createEndShift(getAlarm(), localFireman, veh, hl, ch, st);
    }
}
