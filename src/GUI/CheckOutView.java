package GUI;

import BE.BEVehicle;
import BLL.BLLTimelist;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Shadowleet
 */
public class CheckOutView extends javax.swing.JDialog {

    BLLVehicle bllvehicle;
    BLLTimelist blltime;

    /**
     * Creates new form CheckOutView
     */
    public CheckOutView() {

        initComponents();
        initOtherComponents();
        this.setTitle("CHECK UD");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        bllvehicle = BLLVehicle.getInstance();

        fillCboxVehicle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpCheckOut = new javax.swing.ButtonGroup();
        cboxVehicle = new javax.swing.JComboBox();
        rbtnHoldleder = new javax.swing.JRadioButton();
        rbtnChauffør = new javax.swing.JRadioButton();
        rbtnStVagt = new javax.swing.JRadioButton();
        btnAcknowledge = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cboxVehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Intet køretøj valgt" }));

        rbtnHoldleder.setText("Holdleder");

        rbtnChauffør.setText("Chauffør");

        rbtnStVagt.setText("ST Vagt");

        btnAcknowledge.setText("Bekræft");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboxVehicle, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtnStVagt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnHoldleder, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnChauffør, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAcknowledge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cboxVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(rbtnHoldleder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnChauffør)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnStVagt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAcknowledge)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcknowledge;
    private javax.swing.ButtonGroup btnGrpCheckOut;
    private javax.swing.JComboBox cboxVehicle;
    private javax.swing.JRadioButton rbtnChauffør;
    private javax.swing.JRadioButton rbtnHoldleder;
    private javax.swing.JRadioButton rbtnStVagt;
    // End of variables declaration//GEN-END:variables

    private void initOtherComponents() {
        btnAcknowledge.setText("Bekræft");
        btnAcknowledge.setEnabled(true);

        btnGrpCheckOut.add(rbtnStVagt);
        btnGrpCheckOut.add(rbtnChauffør);
        rbtnHoldleder.setEnabled(false);
        rbtnChauffør.setEnabled(false);
        rbtnStVagt.setEnabled(true);

        btnAcknowledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                sendToBLL();
                dispose();
            }
        });
    }

    private void fillCboxVehicle() {
        for (BEVehicle car : bllvehicle.GetVehicle()) {
            cboxVehicle.addItem(car);
        }
    }

    private void sendToBLL() {
        BEVehicle odin;
        boolean hl = false;
        boolean ch = false;
        boolean st = false;
        odin = (BEVehicle) cboxVehicle.getSelectedItem();
        if (rbtnHoldleder.isSelected()) {
            hl = true;
        }
        if (rbtnChauffør.isSelected()) {
            ch = true;
        }
        if (rbtnStVagt.isSelected()) {
            st = true;
        }
         blltime.sendToDAL(odin, hl, ch, st);
    }
}
