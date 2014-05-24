package GUI;

import BE.BEEmployee;
import BE.BEFireman;
import BLL.BLLEmployee;
import BLL.BLLFireman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewFiremen extends javax.swing.JFrame {

    BLLFireman bllFireman;
    BLLEmployee bllEmployee;
    DefaultListModel model = new DefaultListModel();

    public ViewFiremen() {
        this.setResizable(false);
        bllEmployee = BLLEmployee.getInstance();
        bllFireman = BLLFireman.getInstance();
        initComponents();
        initOtherComponents();
        lstEmployees.setModel(model);
        fillList();
    }

    private void initOtherComponents() {
        chckboxCH.setEnabled(false);
        chckboxHL.setEnabled(false);
        txtTeam.setEnabled(false);

        lstEmployees.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnRemove.setEnabled(lstEmployees.getSelectedIndex() != 0 && lstEmployees.getSelectedIndex() != -1);
                if (lstEmployees.getSelectedIndex() != 0 && lstEmployees.getSelectedIndex() != -1) {

                    BEEmployee emp = (BEEmployee) lstEmployees.getSelectedValue();

                    if (emp.isFriviligBrand()) {
                        BEFireman fireman = null;
                        fireman = bllFireman.getFiremanByMedabejderNo(emp, fireman);
                        chckboxFireman.setEnabled(true);
                        txtTeam.setEnabled(true);
                        txtTeam.setText(String.valueOf(fireman.getTeam()));
                        chckboxHL.setSelected(fireman.isHoldleder());
                        chckboxCH.setSelected(fireman.isChaffør());
                    } else {
                        txtTeam.setText("");
                        txtTeam.setEnabled(false);
                    }
                    fillInfo(emp); 
                    } else {
                    clearInfo();
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        chckboxFireman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTeam.setEnabled(chckboxFireman.isSelected());
                chckboxCH.setEnabled(chckboxFireman.isSelected());
                chckboxHL.setEnabled(chckboxFireman.isSelected());
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployee();
            }
        });
    }

    private void clearInfo() {
        txtTeam.setText("");
        txtTeam.setEnabled(false);
        txtFornavn.setText("");
        txtMellemnavn.setText("");
        txtEfternavn.setText("");
        txtCPR.setText("");
        txtImage.setText("");
        chckboxFireman.setSelected(false);
        txtGadenavn.setText("");
        txtGadenummer.setText("");
        txtEtage.setText("");
        txtLejlighed.setText("");
        txtPostNr.setText("");
        txtTeam.setText("");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setIcon(new ImageIcon(((new ImageIcon("images/blank.jpg")).getImage()).getScaledInstance(110, 100, java.awt.Image.SCALE_SMOOTH)));
    }

    private void fillInfo(BEEmployee emp){
        txtFornavn.setText(emp.getFornavn());
                    txtMellemnavn.setText(emp.getMellemnavn());
                    txtEfternavn.setText(emp.getEfternavn());
                    txtCPR.setText(emp.getCpr());
                    txtImage.setText(emp.getPortræt());
                    chckboxFireman.setSelected(emp.isFriviligBrand());
                    txtGadenavn.setText(emp.getAdress().getStreetName());
                    txtGadenummer.setText(String.valueOf(emp.getAdress().getStreetNumber()));
                    txtEtage.setText(String.valueOf(emp.getAdress().getFloor()));
                    txtLejlighed.setText(emp.getAdress().getApartment());
                    txtPostNr.setText(String.valueOf(emp.getAdress().getZip().getZipCode()));
                    lblImage.setHorizontalAlignment(SwingConstants.CENTER);
                    lblImage.setIcon(new ImageIcon(((new ImageIcon("images/" + emp.getPortræt())).getImage()).getScaledInstance(110, 100, java.awt.Image.SCALE_SMOOTH)));
                
    }
    

    private void addEmployee() {
        int gadenummer = !txtGadenummer.getText().isEmpty() ? Integer.parseInt(txtGadenummer.getText()) : 0;
        int etage = !txtEtage.getText().isEmpty() ? Integer.parseInt(txtEtage.getText()) : 0;
        int postNr = !txtPostNr.getText().isEmpty() ? Integer.parseInt(txtPostNr.getText()) : 0;

        if (txtFornavn.getText().isEmpty() && txtEfternavn.getText().isEmpty() && txtGadenavn.getText().isEmpty() && txtGadenummer.getText().isEmpty() && txtPostNr.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Udfyld venligst al information!");
        } else {
            int adresseID = bllEmployee.addAddress(txtGadenavn.getText(), gadenummer, etage, txtLejlighed.getText(), postNr);
            bllEmployee.addEmployee(txtFornavn.getText(), txtMellemnavn.getText(), txtEfternavn.getText(), txtCPR.getText(), txtImage.getText(), chckboxFireman.isSelected(), adresseID, chckboxCH.isSelected(), chckboxHL.isSelected(), !txtTeam.getText().isEmpty() ? Integer.parseInt(txtTeam.getText()) : 0);
            JOptionPane.showMessageDialog(this, "Medarbejderen er nu tilføjet!");
            fillList();
        }
    }

    private void removeEmployee() {
        BEEmployee emp = (BEEmployee) lstEmployees.getSelectedValue();
        if (emp.isFriviligBrand()) {
            bllFireman.deleteFireman(emp);
        }
        bllEmployee.deleteEmployee(emp);
        model.removeElement(emp);
        JOptionPane.showMessageDialog(this, "Medarbejderen er nu slettet!");
    }

    private void fillList() {
        model.clear();
        model.addElement("Ingen brandmand valgt");
        for (BEEmployee emp : bllEmployee.getAllEmployees()) {
            model.addElement(emp);
        }
        lstEmployees.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmployees = new javax.swing.JList();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        pnlPerson = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFornavn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEfternavn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtImage = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        chckboxFireman = new javax.swing.JCheckBox();
        txtMellemnavn = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCPR = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTeam = new javax.swing.JTextField();
        chckboxCH = new javax.swing.JCheckBox();
        chckboxHL = new javax.swing.JCheckBox();
        pnlAdresse = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtGadenavn = new javax.swing.JTextField();
        txtGadenummer = new javax.swing.JTextField();
        txtEtage = new javax.swing.JTextField();
        txtLejlighed = new javax.swing.JTextField();
        txtPostNr = new javax.swing.JTextField();
        pnlImage = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();

        jLabel11.setText("jLabel11");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstEmployees.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstEmployees);

        btnRemove.setText("Fjern");

        btnAdd.setText("Tilføj");

        pnlPerson.setBorder(javax.swing.BorderFactory.createTitledBorder("Person"));

        jLabel1.setText("Fornavn:");

        jLabel6.setText("Efternavn:");

        jLabel7.setText("Portræt:");

        btnBrowse.setText("...");

        chckboxFireman.setText("Brandmand");

        jLabel9.setText("Mellemnavn:");

        jLabel10.setText("CPR:");

        jLabel12.setText("Hold:");

        chckboxCH.setText("CH");

        chckboxHL.setText("HL");

        javax.swing.GroupLayout pnlPersonLayout = new javax.swing.GroupLayout(pnlPerson);
        pnlPerson.setLayout(pnlPersonLayout);
        pnlPersonLayout.setHorizontalGroup(
            pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPersonLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFornavn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPersonLayout.createSequentialGroup()
                        .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlPersonLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMellemnavn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPersonLayout.createSequentialGroup()
                                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPersonLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(chckboxFireman)
                                        .addComponent(txtEfternavn)
                                        .addComponent(txtImage, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                        .addComponent(txtCPR))
                                    .addGroup(pnlPersonLayout.createSequentialGroup()
                                        .addComponent(chckboxCH)
                                        .addGap(18, 18, 18)
                                        .addComponent(chckboxHL)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowse)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlPersonLayout.setVerticalGroup(
            pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFornavn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMellemnavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEfternavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chckboxFireman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chckboxCH)
                    .addComponent(chckboxHL))
                .addGap(139, 139, 139))
        );

        pnlAdresse.setBorder(javax.swing.BorderFactory.createTitledBorder("Adresse"));

        jLabel2.setText("Gadenavn:");

        jLabel3.setText("Gadenummer:");

        jLabel4.setText("Etage:");

        jLabel5.setText("Lejlighed:");

        jLabel8.setText("Post nr:");

        javax.swing.GroupLayout pnlAdresseLayout = new javax.swing.GroupLayout(pnlAdresse);
        pnlAdresse.setLayout(pnlAdresseLayout);
        pnlAdresseLayout.setHorizontalGroup(
            pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdresseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPostNr, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(txtLejlighed)
                    .addComponent(txtEtage)
                    .addComponent(txtGadenavn, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(txtGadenummer))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlAdresseLayout.setVerticalGroup(
            pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdresseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGadenavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGadenummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLejlighed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(pnlAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPostNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlImage.setBorder(javax.swing.BorderFactory.createTitledBorder("Portræt"));

        javax.swing.GroupLayout pnlImageLayout = new javax.swing.GroupLayout(pnlImage);
        pnlImage.setLayout(pnlImageLayout);
        pnlImageLayout.setHorizontalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageLayout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlImageLayout.setVerticalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageLayout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlPerson, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnRemove;
    private javax.swing.JCheckBox chckboxCH;
    private javax.swing.JCheckBox chckboxFireman;
    private javax.swing.JCheckBox chckboxHL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JList lstEmployees;
    private javax.swing.JPanel pnlAdresse;
    private javax.swing.JPanel pnlImage;
    private javax.swing.JPanel pnlPerson;
    private javax.swing.JTextField txtCPR;
    private javax.swing.JTextField txtEfternavn;
    private javax.swing.JTextField txtEtage;
    private javax.swing.JTextField txtFornavn;
    private javax.swing.JTextField txtGadenavn;
    private javax.swing.JTextField txtGadenummer;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtLejlighed;
    private javax.swing.JTextField txtMellemnavn;
    private javax.swing.JTextField txtPostNr;
    private javax.swing.JTextField txtTeam;
    // End of variables declaration//GEN-END:variables
}
