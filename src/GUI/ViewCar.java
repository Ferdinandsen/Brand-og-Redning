package GUI;

import BE.BEVehicle;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewCar extends javax.swing.JFrame {

    BLLVehicle bllVehicle;
    DefaultListModel model = new DefaultListModel();

    public ViewCar() {
        bllVehicle = BLLVehicle.getInstance();

        initComponents();
        initOtherComponents();
        fillList();
        lstVehicles.setModel(model);

        this.setResizable(false);
        this.setTitle("Køretøjs administration");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void fillList() {
        model.clear();
        for (BEVehicle veh : bllVehicle.getAllVehicles()) {
            model.addElement(veh);
        }
    }

    private void initOtherComponents() {
        btnRemove.setEnabled(false);

        lstVehicles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnRemove.setEnabled(lstVehicles.getSelectedIndex() != -1);
                btnAdd.setEnabled(lstVehicles.getSelectedIndex() == 0);
                if (lstVehicles.getSelectedIndex() != -1) {
                    BEVehicle veh = (BEVehicle) lstVehicles.getSelectedValue();
                    txtBilnr.setText(String.valueOf(veh.getOdinnummer()));
                    txtNummerplade.setText(String.valueOf(veh.getRegistrering()));
                    txtMærke.setText(String.valueOf(veh.getMærke()));
                    txtModel.setText(String.valueOf(veh.getModel()));
                    txtBeskrivelse.setText(String.valueOf(veh.getBeskrivelse()));
                } else {
                    txtBilnr.setText("");
                    txtNummerplade.setText("");
                    txtMærke.setText("");
                    txtModel.setText("");
                    txtBeskrivelse.setText("");
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCar();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCar();
            }
        });
    }

    private void deleteCar() {
        BEVehicle veh = (BEVehicle) lstVehicles.getSelectedValue();
        bllVehicle.deleteVehicle(veh);
        model.removeElement(veh);
        JOptionPane.showMessageDialog(this, "Bilen er nu slettet!");
    }

    private void createCar() {
        if (txtBeskrivelse.getText().isEmpty() && txtBilnr.getText().isEmpty() && txtModel.getText().isEmpty() && txtMærke.getText().isEmpty() && txtNummerplade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Udfyld venligst al information!");
        } else {
            bllVehicle.addVehicle(txtBeskrivelse.getText(), txtBilnr.getText(), txtModel.getText(), txtMærke.getText(), txtNummerplade.getText());
            fillList();
            txtBilnr.setText("");
            txtNummerplade.setText("");
            txtMærke.setText("");
            txtModel.setText("");
            txtBeskrivelse.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstVehicles = new javax.swing.JList();
        btnRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBilnr = new javax.swing.JTextField();
        txtNummerplade = new javax.swing.JTextField();
        txtMærke = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtBeskrivelse = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstVehicles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstVehicles);

        btnRemove.setText("Fjern");

        jLabel1.setText("Bil nr:");

        jLabel2.setText("Nummerplade:");

        jLabel3.setText("Mærke");

        jLabel4.setText("Model:");

        jLabel5.setText("Beskrivelse:");

        btnAdd.setText("Tilføj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBilnr)
                                .addComponent(txtNummerplade)
                                .addComponent(txtMærke, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtModel)
                                .addComponent(txtBeskrivelse))))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBilnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNummerplade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMærke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtBeskrivelse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstVehicles;
    private javax.swing.JTextField txtBeskrivelse;
    private javax.swing.JTextField txtBilnr;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtMærke;
    private javax.swing.JTextField txtNummerplade;
    // End of variables declaration//GEN-END:variables
}
