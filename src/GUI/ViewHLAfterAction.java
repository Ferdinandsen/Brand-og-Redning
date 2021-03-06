package GUI;

import TableModels.TableModelFremmøde;
import Renderes.RenderFremmødeTableCell;
import BE.BEAlarm;
import BE.BEAppearance;
import BE.BELogin;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class ViewHLAfterAction extends JDialog {

    BLLVehicle bllVehicle;
    BLLAppearance bllAppearance;
    BLLAlarm bllAlarm;
    BELogin localLog;
    private TableModelFremmøde model;
    private static ViewHLAfterAction m_instance = null;

    public ViewHLAfterAction(BELogin log) {
        localLog = log;
        bllAlarm = BLLAlarm.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllAppearance = BLLAppearance.getInstance();
        initComponents();
        initOtherComponents();
        this.setModal(true);
        this.setTitle("Holdleder - Hold bekræftigelse");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        populateFremmødeTable();
        addCellRenderer();
        fillCboxAlarm();
        cboxAlarm.setSelectedIndex(0);
        lblCount.setText("Fremmødt: " + model.getRowCount());
    }

    /**
     * set component settings, and initialize listeners
     */
    private void initOtherComponents() {
        btnChangeTime.setEnabled(false);
        txtFremmøde.setEditable(false);
        btnBekæft.setEnabled(true);

        tblTider.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnChangeTime.setEnabled(tblTider.getSelectedRow() != -1);
            }
        });

        btnChangeTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTime();
                model.fireTableDataChanged();
            }
        });

        cboxAlarm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cboxAlarm.getSelectedIndex() != -1) {
                    if (cboxAlarm.getSelectedIndex() != 0 && cboxAlarm.getSelectedIndex() != -1) {
                        model.setAppearanceList(bllAppearance.getAppearancesWithSameAlarm((BEAlarm) cboxAlarm.getSelectedItem()));
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
                        model.setAppearanceList(bllAppearance.getAllAppearancesNotHlGodkendt());
                        txtFremmøde.setText("");
                    }
                    model.fireTableDataChanged();
                    lblCount.setText("Fremmødt: " + model.getRowCount());
                }
            }
        });

        btnBekæft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() != 0 && cboxAlarm.getSelectedIndex() != 0) {
                    confirmTeam();
                } else {
                    msgbox("Udfyld venligst al information!");
                }
            }
        });
    }

    /**
     * gets the selected appearance, and opens the "ViewChangeTime" form
     */
    private void changeTime() {
        BEAppearance appearance = null;
        if (cboxAlarm.getSelectedIndex() == 0) {
            appearance = bllAppearance.getAllAppearancesNotHlGodkendt().get(tblTider.convertRowIndexToView(tblTider.getSelectedRow()));
        } else {
            appearance = bllAppearance.getAppearancesWithSameAlarm((BEAlarm) cboxAlarm.getSelectedItem()).get(tblTider.convertRowIndexToView(tblTider.getSelectedRow()));
        }
        ViewChangeTime frame = new ViewChangeTime(appearance);
        frame.setVisible(true);
    }

    /**
     * confirms the team
     */
    private void confirmTeam() {
        try {
            bllAppearance.confirmTeam(localLog, (BEAlarm) cboxAlarm.getSelectedItem(), txtComment.getText());
            msgbox("Holdet er nu bekræftet!");
            ViewHLAfterActionStory frame = new ViewHLAfterActionStory(bllAppearance.getAllHlGodkendtAppearances((BEAlarm) cboxAlarm.getSelectedItem()), (BEAlarm) cboxAlarm.getSelectedItem());
            dispose();
            frame.setVisible(true);
            txtComment.setText(null);
        } catch (Exception ex) {
            msgbox("fejl i confirmteam");
        }
    }

    /**
     * fill our table with appearances
     */
    private void populateFremmødeTable() {
        model = new TableModelFremmøde(bllAppearance.getAllAppearancesNotHlGodkendt());
        tblTider.setModel(model);
        model.fireTableDataChanged();
    }

    /**
     * adds a cell renderer to our table
     */
    private void addCellRenderer() {
        RenderFremmødeTableCell renderer = new RenderFremmødeTableCell();
        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblTider.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    /**
     * fills our combobox with alarm that isn't done, and alarms that are not
     * HLGodkendt
     */
    private void fillCboxAlarm() {
        ArrayList<BEAlarm> test = new ArrayList<>();
        cboxAlarm.addItem("Ingen alarm");
        for (BEAlarm alarm : bllAlarm.getAllUnfinishedAlarms()) {
            for (BEAppearance a : bllAppearance.getAllAppearancesNotHlGodkendt()) {
                if (a.getAlarm().equals(alarm) && !test.contains(alarm)) {
                    test.add(alarm);
                }
            }
        }
        for (BEAlarm alarm : test) {
            cboxAlarm.addItem(alarm);
        }
    }

    /**
     *
     * @param message a messagedialog, with the parameter as message
     */
    private void msgbox(String message) {
        JOptionPane.showMessageDialog(this, message);
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
        txtFremmøde = new javax.swing.JTextField();
        cboxAlarm = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtComment = new javax.swing.JTextField();
        btnChangeTime = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 370));

        btnBekæft.setText("Bekræft hold");

        lblAlarm.setText("Alarm:");

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
        tblTider.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblTider);

        lblFremmøde.setText("Fremmøde ved:");

        lblCount.setText("Fremmødt:");

        jLabel2.setText("Kommentar:");

        btnChangeTime.setText("Ændre tid");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtComment))
                                    .addComponent(lblCount))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBekæft, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(btnChangeTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlarm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFremmøde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFremmøde, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 431, Short.MAX_VALUE)))
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlarm)
                    .addComponent(lblFremmøde)
                    .addComponent(txtFremmøde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCount)
                    .addComponent(btnChangeTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBekæft))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekæft;
    private javax.swing.JButton btnChangeTime;
    private javax.swing.JComboBox cboxAlarm;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlarm;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblFremmøde;
    private javax.swing.JTable tblTider;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextField txtFremmøde;
    // End of variables declaration//GEN-END:variables
}
