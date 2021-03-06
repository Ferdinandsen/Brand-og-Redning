package GUI;

import BE.BEError;
import BE.BEVehicle;
import BLL.BLLVehicle;
import BLL.BLLReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Kawabunga
 */
public class ViewHLErrorReport extends JDialog {

    BLLVehicle bllVehicle;
    BLLReport bllreport;

    /**
     * Creates new form HLErrorReport
     */
    public ViewHLErrorReport() {
        bllVehicle = BLLVehicle.getInstance();
        bllreport = BLLReport.getInstance();
        initComponents();
        initOtherComponents();
        fillCboxKøretøj();
        this.setModal(true);
        this.setTitle("Fejl/Mangler");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblKøtj = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCourse = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtOutofOrder = new javax.swing.JTextField();
        txtUrgent = new javax.swing.JTextField();
        txtInDueTime = new javax.swing.JTextField();
        txtWash = new javax.swing.JTextField();
        btnAcknowledge = new javax.swing.JButton();
        cboxKøretøj = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtMadeBy = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblHeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtError.setColumns(20);
        txtError.setRows(5);
        jScrollPane1.setViewportView(txtError);

        jLabel2.setText("FEJL:");

        lblKøtj.setText("Køretøj:");

        txtCourse.setColumns(20);
        txtCourse.setRows(5);
        jScrollPane2.setViewportView(txtCourse);

        jLabel5.setText("Evt. årsag:");

        jLabel6.setText("Ud af drift:");

        jLabel7.setText("Haster:");

        jLabel8.setText("Ved lejlighed:");

        jLabel9.setText("Branddragter til vask:");

        btnAcknowledge.setText("Bekræft");

        jLabel1.setText("Udfyldt af :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel1))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtOutofOrder)
                                    .addComponent(txtUrgent)
                                    .addComponent(txtInDueTime)
                                    .addComponent(txtWash, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(btnAcknowledge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(txtMadeBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblKøtj, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKøtj)
                    .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOutofOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtUrgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtInDueTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtWash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMadeBy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAcknowledge)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcknowledge;
    private javax.swing.JComboBox cboxKøretøj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblKøtj;
    private javax.swing.JTextArea txtCourse;
    private javax.swing.JTextArea txtError;
    private javax.swing.JTextField txtInDueTime;
    private javax.swing.JTextField txtMadeBy;
    private javax.swing.JTextField txtOutofOrder;
    private javax.swing.JTextField txtUrgent;
    private javax.swing.JTextField txtWash;
    // End of variables declaration//GEN-END:variables

    /**
     * fills our combobox with vehicles
     */
    private void fillCboxKøretøj() {
        for (BEVehicle veh : bllVehicle.getAllVehicles()) {
            cboxKøretøj.addItem(veh);
        }
        cboxKøretøj.setSelectedIndex(-1);
    }

    /**
     * set component settings, and initialize listeners
     */
    private void initOtherComponents() {
        lblHeader.setText("Fejl og mangler");
        btnAcknowledge.setEnabled(true);
        btnAcknowledge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm();
            }
        });
    }

    /**
     * creates the error report
     */
    private void confirm() {
        if (!txtError.getText().isEmpty() && cboxKøretøj.getSelectedIndex() != -1 && !txtMadeBy.getText().isEmpty()) {
            bllreport.createErrorReport(errorAsBE(), (BEVehicle) cboxKøretøj.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Fejl rapport sendt");
            dispose();
        } else {
            msgbox("Indtast venligst hvilket køretøj, fejl og mangler samt hvem du er");
        }
    }

    /**
     * 
     * @param message 
     * a message dialog, showing the parameter
     */
    private void msgbox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * 
     * @return the business entity BEError
     */
    private BEError errorAsBE() {
        String error = txtError.getText();
        String course = txtCourse.getText();
        String ooo = txtOutofOrder.getText();
        String urgent = txtUrgent.getText();
        String idt = txtInDueTime.getText();
        String wash = txtWash.getText();

        BEError be = new BEError(error, course, ooo, urgent, idt, wash);
        return be;
    }
}
