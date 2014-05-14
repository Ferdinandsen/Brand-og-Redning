package GUI;

import BE.BEAlarm;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class HLAfterAction1 extends javax.swing.JFrame {

    BLLVehicle bllVehicle;
    BLLAppearance bllAppearance;
    BLLAlarm bllAlarm;
    private FremmødeTableModel model;
    private static HLAfterAction1 m_instance = null;

    private HLAfterAction1() {
        initComponents();
        initOtherComponents();
        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllAppearance = BLLAppearance.getInstance();

        this.setTitle("HL - Bekræft hold");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        cboxAlarm.setSelectedIndex(-1);
        populateFremmødeTable();
        addCellRenderer();
        fillCboxType();
        fillCboxAlarm();
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
        bllAppearance.update();
        model.setAppearanceList(bllAppearance.getAllAppearances());
        model.fireTableDataChanged();
        lblCount.setText("Fremmødt: " + model.getRowCount());
    }

    private void msgbox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void initOtherComponents() {
        txtFremmøde.setEditable(false);
        btnBekæft.setEnabled(true);
        cboxAlarm.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (cboxAlarm.getSelectedIndex() != -1) {
                    if (cboxAlarm.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != -1) {
                        update();
                        model.setAppearanceList(bllAppearance.getAppearancesWithCriteria((BEAlarm) cboxAlarm.getSelectedItem()));

                        BEAlarm alarm = null;
                        for (BEAlarm theAlarm : bllAlarm.getAllAlarms()) {
                            String[] theAlarmString = cboxAlarm.getSelectedItem().toString().split(" - ");
                            if (theAlarm.getDesc().equals(theAlarmString[0])) {
                                alarm = theAlarm;
                                break;
                            }

                        }
                        txtFremmøde.setText(alarm.getDesc());
                    } else {
                        model.setAppearanceList(bllAppearance.getAllAppearances());
                        txtFremmøde.setText("");
                    }
                    model.fireTableDataChanged();
                    lblCount.setText("Fremmødt: " + model.getRowCount());

                }
            }
        }
        );

        btnBekæft.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (cboxType.getSelectedIndex() != -1 && model.getRowCount() != 0 && cboxAlarm.getSelectedIndex() != 0) {
                            confirmTeam();
                            msgbox("Holdet er nu bekræftet!");
                            HLUsageReport frame = new HLUsageReport(BLLAppearance.getInstance().newAppearances.get(0));
                            frame.setVisible(true);
                            dispose();
                        } else {
                            msgbox("Udfyld venligst al information!");
                        }
                    }
                }
        );

    }

    private void confirmTeam() {
        try {
            bllAppearance.confirmTeam((int) cboxType.getSelectedItem(), (BEAlarm) cboxAlarm.getSelectedItem());
        } catch (Exception ex) {
            msgbox("dd" + ex);
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

        return true;
    }

    private void addCellRenderer() {

        FremmødeTableCellRenderer renderer = new FremmødeTableCellRenderer();

        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblTider.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    private void fillCboxAlarm() {
        cboxAlarm.addItem("Ingen alarm");
        for (BEAlarm alarm : bllAlarm.getAllAlarms()) {
            cboxAlarm.addItem(alarm);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBekæft = new javax.swing.JButton();
        lblAlarm = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTider = new javax.swing.JTable();
        lblFremmøde = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        cboxType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtFremmøde = new javax.swing.JTextField();
        cboxAlarm = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBekæft.setText("Bekræft hold");

        lblAlarm.setText("Alarm");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFremmøde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFremmøde)
                        .addGap(18, 18, 18)
                        .addComponent(btnBekæft, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCount)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlarm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlarm))
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
    private javax.swing.JComboBox cboxAlarm;
    private javax.swing.JComboBox cboxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlarm;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblFremmøde;
    private javax.swing.JTable tblTider;
    private javax.swing.JTextField txtFremmøde;
    // End of variables declaration//GEN-END:variables

}
