package GUI;

import BE.BEAlarm;
import BLL.BLLAlarm;
import BLL.BLLTimelist;
import BLL.BLLAppearance;
import BLL.BLLVehicle;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
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
    private static HLAfterAction1 m_instance = null;

    private HLAfterAction1() {
        initComponents();
        initOtherComponents();
        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllTime = BLLTimelist.getInstance();
        bllAppearance = BLLAppearance.getInstance();

        this.setTitle("HL - Bekræft hold");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        fillCboxType();

        populateFremmødeTable();
        addCellRenderer();
//        btnBekæft.setEnabled(oneTeamOrNot());
        lblCount.setText("Fremmødt: " + model.getRowCount());

    }

    public static HLAfterAction1 getInstance() {
        if (m_instance == null) {
            m_instance = new HLAfterAction1();
        } else {
            m_instance.update();
        }
        return m_instance;
    }

    private void update() {
        bllVehicle.update();
        bllTime.update();
        bllAppearance.update();
        model.setAppearanceList(bllAppearance.getAllAppearances());
        model.fireTableDataChanged();
//                    btnBekæft.setEnabled(oneTeamOrNot());
        lblCount.setText("Fremmødt: " + model.getRowCount());

    }

    private void msgbox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void initOtherComponents() {
        txtTolerance.setText("10");
        btnBekæft.setEnabled(true);
        txtTolerance.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)) || txtTolerance.getText().length() >= 3) {
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
                if (cboxType.getSelectedIndex() != -1 && txtTid.getText().length() != 4 && jDateChooser.getDate() != null && model.getRowCount() != 0 && !txtFremmøde.getText().isEmpty()) {
                    confirmTeam();
                    msgbox("Holdet er nu bekræftet!");
                    txtFremmøde.setText("");
                    txtTid.setText("");
                    jDateChooser.setDate(null);
                    HLUsageReport frame = new HLUsageReport(BLLAppearance.getInstance().newAppearances.get(0));
                    frame.setVisible(true);
                    dispose();
                } else {
                    msgbox("Udfyld venligst al information!");
                }
            }
        });

        btnHent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isInformationFilled()) {
                    update();
                    model.setAppearanceList(bllAppearance.getAppearancesWithCriteria(jDateChooser.getDate(), txtTid.getText(), Integer.parseInt(txtTolerance.getText())));
                    model.fireTableDataChanged();
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
        try {
            BEAlarm alarm = bllAlarm.createAlarm(jDateChooser.getDate(), txtTid.getText(), txtFremmøde.getText());
            bllAppearance.confirmTeam((int) cboxType.getSelectedItem(), alarm);

        } catch (Exception ex) {
            msgbox(ex.getMessage());
        }

    }

    private void fillCboxType() {
        cboxType.addItem(1);
        cboxType.addItem(2);
        cboxType.setSelectedIndex(-1);
    }

    private void populateFremmødeTable() {
        model = new FremmødeTableModel(bllAppearance.getAllAppearances());
        tblTider.setModel(model);
        model.fireTableDataChanged();
    }

    private boolean isInformationFilled() {
        if (jDateChooser.getDate() != null && txtTid.getText().trim().length() == 5) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDato = new javax.swing.JLabel();
        btnBekæft = new javax.swing.JButton();
        lblTid = new javax.swing.JLabel();
        btnHent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTider = new javax.swing.JTable();
        lblFremmøde = new javax.swing.JLabel();
        txtTid = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        lblCount = new javax.swing.JLabel();
        cboxType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtTolerance = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblmin = new javax.swing.JLabel();
        txtFremmøde = new javax.swing.JTextField();

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

        lblFremmøde.setText("Fremmøde ved:");

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
                        .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHent))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCount)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFremmøde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFremmøde)
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
                        .addComponent(txtTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(lblmin))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFremmøde)
                        .addComponent(txtFremmøde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btnBekæft))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekæft;
    private javax.swing.JButton btnHent;
    private javax.swing.JComboBox cboxType;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblDato;
    private javax.swing.JLabel lblFremmøde;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblmin;
    private javax.swing.JTable tblTider;
    private javax.swing.JTextField txtFremmøde;
    private javax.swing.JTextField txtTid;
    private javax.swing.JTextField txtTolerance;
    // End of variables declaration//GEN-END:variables
}
