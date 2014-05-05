package GUI;

import BE.BEAlarm;
import BE.BEVehicle;
import BLL.BLLAlarm;
import BLL.BLLTimelist;
import BLL.BLLAppearance;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class HLAfterAction1 extends javax.swing.JFrame {

    BLLVehicle bllVehicle;
    BLLTimelist bllTime;
    BLLAppearance bllAppearance;
    BLLAlarm bllAlarm;
    private FremmødeTableModel model;
    boolean startUp = true;

    public HLAfterAction1() {
        initComponents();
        initOtherComponents();

        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllTime = BLLTimelist.getInstance();
        bllAppearance = BLLAppearance.getInstance();

        this.setTitle("Hl After Action 1");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        fillCboxType();
        fillCboxEva();
        populateFremmødeTable();
        addCellRenderer();
//        btnBekæft.setEnabled(oneTeamOrNot());
        lblCount.setText("Fremmødt: " + model.getRowCount());

    }

    private void msgbox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void initOtherComponents() {
        btnBekæft.setEnabled(true);
        txtTolerance.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtTolerance.setText("");
                }
            }
        });

        btnBekæft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxType.getSelectedIndex() != -1) {
                    confirmTeam();
                    dispose();
                    HLUsageReport frame = new HLUsageReport(BLLAppearance.getInstance().newAppearances.get(0));
                    frame.setVisible(true);
                } else {
                    msgbox("Vælg venligst type!");
                }
            }
        });

        btnHent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isInformationFilled()) {
                    model.setAppearanceList(bllAppearance.getAppearancesWithCriteria(jDateChooser.getDate(), txtTid.getText(), (BEAlarm) cboxEva.getSelectedItem(), Integer.parseInt(txtTolerance.getText())));
                    model.fireTableDataChanged();
//                    btnBekæft.setEnabled(oneTeamOrNot());
                    lblCount.setText("Fremmødt: " + model.getRowCount());
                }
            }
        });

        txtTid.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtTid.getText().length() == 2) {
                    txtTid.setText(txtTid.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtTid.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtTid.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtTid.getText().length() > 0 && txtTid.getText().charAt(0) == '2') {
                    if (txtTid.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtTid.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    System.out.println("test");
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtTid.setText("");
                }
            }
        });
    }

    private void confirmTeam() {
        bllAppearance.confirmTeam((int) cboxType.getSelectedItem());
        JOptionPane.showMessageDialog(this, "Holdet er nu bekræftet!");
    }

    private void fillCboxType() {
        cboxType.addItem(1);
        cboxType.addItem(2);
        cboxType.setSelectedIndex(-1);
    }

    private void fillCboxEva() {
        for (BEAlarm alarm : bllAlarm.getAllAlarms()) {
            cboxEva.addItem(alarm);
        }
        cboxEva.setSelectedIndex(-1);
    }

    private void populateFremmødeTable() {
        model = new FremmødeTableModel(bllAppearance.getAllAppearances());
        tblTider.setModel(model);
        model.fireTableDataChanged();
    }

    private boolean isInformationFilled() {
        if (jDateChooser.getDate() != null && txtTid.getText().trim().length() == 5 && cboxEva.getSelectedIndex() != -1) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Udfyld venligst al information");
        return false;
    }

    private void addCellRenderer() {

        FremmødeTableCellRenderer renderer = new FremmødeTableCellRenderer();

        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblTider.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

//    private boolean oneTeamOrNot() {
//        int vehNo = 0;
//        if (startUp == true) {
//            for (BEAppearance appearance : bllAppearance.getAllAppearances()) {
//                if (appearance.getVehicle() == null) {
//                    startUp = false;
//                    return false;
//                }
//                if (vehNo == 0) {
//                    vehNo = appearance.getVehicle().getOdinnummer();
//                }
//                if (appearance.getVehicle().getOdinnummer() != vehNo) {
//                    startUp = false;
//                    return false;
//                }
//                vehNo = appearance.getVehicle().getOdinnummer();
//            }
//            startUp = false;
//            return true;
//        } else {
//            for (BEAppearance appearance : bllAppearance.getAppearancesWithCriteria(jDateChooser.getDate(), txtTid.getText(), (BEVehicle) cboxKøretøj.getSelectedItem(), Integer.parseInt(txtTolerance.getText()))) {
//                  if (appearance.getVehicle() == null) {
//                    
//                    return false;
//                }
//                if (vehNo == 0) {
//                    vehNo = appearance.getVehicle().getOdinnummer();
//                }
//                if (appearance.getVehicle().getOdinnummer() != vehNo) {
//                    return false;
//                }
//                vehNo = appearance.getVehicle().getOdinnummer();
//            }
//            return true;
//        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDato = new javax.swing.JLabel();
        btnBekæft = new javax.swing.JButton();
        lblTid = new javax.swing.JLabel();
        btnHent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTider = new javax.swing.JTable();
        lblEva = new javax.swing.JLabel();
        cboxEva = new javax.swing.JComboBox();
        txtTid = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        lblCount = new javax.swing.JLabel();
        cboxType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtTolerance = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDato.setText("Dato:");

        btnBekæft.setText("Bekræft hold");

        lblTid.setText("Tid:");

        btnHent.setText("Hent");

        tblTider.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1339", "HL", "Michael", "Schutenberger", "12:00", "12:49", "2", "Ja", "Nej"},
                {"1339", null, "André", "Thy", "12:01", "12:48", "2", "Nej", "Ja"},
                {"1339", null, "Jacob", "Schlerose", "12:01", "12:49", "2", "Nej", null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Køretøj", "Grad", "Fornavn", "Efternavn", "Check ind", "Check ud", "Timer", "Holdleder?", "Chauffør?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTider);

        lblEva.setText("EvaNr");

        lblCount.setText("Fremmødt:");

        jLabel1.setText("Type:");

        jLabel2.setText("Tolerance:");

        lblmin.setText("min.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTid, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxEva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHent))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBekæft, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDato)
                        .addComponent(lblTid)
                        .addComponent(btnHent)
                        .addComponent(lblEva)
                        .addComponent(cboxEva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(lblmin))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(btnBekæft)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblCount)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekæft;
    private javax.swing.JButton btnHent;
    private javax.swing.JComboBox cboxEva;
    private javax.swing.JComboBox cboxType;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblDato;
    private javax.swing.JLabel lblEva;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblmin;
    private javax.swing.JTable tblTider;
    private javax.swing.JTextField txtTid;
    private javax.swing.JTextField txtTolerance;
    // End of variables declaration//GEN-END:variables
}
