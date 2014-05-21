package GUI;

import BE.BEMateriel;
import BLL.BLLUsage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ForbrugView extends javax.swing.JFrame {
    BLLUsage bllUsage;
    DefaultListModel model = new DefaultListModel();
    
    public ForbrugView() {
        bllUsage = BLLUsage.getInstance();
        initComponents();
        fillList();
        lstMats.setModel(model);
        initOtherComponents();
        this.setResizable(false);
        this.setTitle("Forbrugs administration");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void fillList() {
        model.clear();
        for (BEMateriel mat : bllUsage.getAllMats()) {
            model.addElement(mat);
        }
    }
    
     private void initOtherComponents() {
        btnRemove.setEnabled(false);
        lstMats.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               btnRemove.setEnabled(lstMats.getSelectedIndex() != -1);
                
            }
        });
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               BEMateriel mat = (BEMateriel) lstMats.getSelectedValue();
                bllUsage.deleteMaterial(mat);
                model.removeElement(mat);
                JOptionPane.showMessageDialog(null, "Bilen er nu slettet!");
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMat.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Udfyld venligst al information!");
                } else {
                    bllUsage.addMaterial(txtMat.getText());
                    fillList();
                    txtMat.setText("");
                    
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMats = new javax.swing.JList();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblMateriale = new javax.swing.JLabel();
        txtMat = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstMats.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstMats);

        btnRemove.setText("Fjern");

        btnAdd.setText("Tilføj");

        lblMateriale.setText("Materiale");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMateriale)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addComponent(txtMat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMateriale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMateriale;
    private javax.swing.JList lstMats;
    private javax.swing.JTextField txtMat;
    // End of variables declaration//GEN-END:variables
}
