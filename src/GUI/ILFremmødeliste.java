package GUI;

import BE.BEAlarm;
import BE.BEUsage;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLUsage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class ILFremmødeliste extends javax.swing.JFrame {

    BLLAppearance bllAppearance;
    BLLAlarm bllAlarm;
    BLLUsage bllUsage;
    BEAlarm localAlarm;
    private ILFremmødeTableModel model;
    boolean usageConfirmed = false;

    public ILFremmødeliste(BEAlarm alarm) {
        bllAlarm = BLLAlarm.getInstance();
        bllUsage = BLLUsage.getInstance();
        bllAppearance = BLLAppearance.getInstance();
        localAlarm = alarm;
        initComponents();
        initOtherComponents();
        this.setTitle("Indsatsleders indsats rapport");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getInfo();
        populateFremmødeTable();
        addCellRenderer();
    }

    private void addCellRenderer() {

        ILFremmødeTableCellRenderer renderer = new ILFremmødeTableCellRenderer();
        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblFremmøde.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFremmøde = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtABem = new javax.swing.JTextArea();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblAppear = new javax.swing.JLabel();
        btnUsage = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPDesc = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPAlarmTid = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPEvaNo = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPGrpNo = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtPDetNo = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtHlKommentar = new javax.swing.JTextArea();
        lblHLBem = new javax.swing.JLabel();
        lblAlarmTid = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblEva = new javax.swing.JLabel();
        lblAlarmType = new javax.swing.JLabel();
        lblGrpNo = new javax.swing.JLabel();
        lblDetekNo = new javax.swing.JLabel();
        cboxAlarmType = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblFremmøde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblFremmøde);

        txtABem.setColumns(20);
        txtABem.setRows(5);
        txtABem.setText("Her kan Niels skrive bemærkninger til indsatsen\n- f.eks om der var fremmede enheder med?!");
        jScrollPane2.setViewportView(txtABem);

        btnOK.setText("OK");
        btnOK.setMaximumSize(new java.awt.Dimension(65, 23));
        btnOK.setMinimumSize(new java.awt.Dimension(65, 23));
        btnOK.setPreferredSize(new java.awt.Dimension(65, 23));

        btnCancel.setText("Cancel");

        btnAdd.setText("Tilføj");

        btnUpdate.setText("Opdater");

        btnDelete.setText("Slet");

        lblAppear.setText("Deltagere:");

        btnUsage.setForeground(new java.awt.Color(255, 102, 0));
        btnUsage.setText("Bekræft Forbrug");

        jScrollPane4.setViewportView(txtPDesc);

        jScrollPane5.setViewportView(txtPAlarmTid);

        txtPEvaNo.setToolTipText("");
        jScrollPane6.setViewportView(txtPEvaNo);

        jScrollPane9.setViewportView(txtPGrpNo);

        jScrollPane10.setViewportView(txtPDetNo);

        jLabel1.setText("Bemærkninger");

        txtHlKommentar.setColumns(20);
        txtHlKommentar.setRows(5);
        txtHlKommentar.setText("Her kan Niels læse\nbeskeder fra HL");
        jScrollPane11.setViewportView(txtHlKommentar);

        lblHLBem.setText("HL bemærkninger:");

        lblAlarmTid.setText("Alarm tid:");

        lblDesc.setText("Beskrivelse:");

        lblEva.setText("EvaNo:");

        lblAlarmType.setText("Alarm type:");

        lblGrpNo.setText("GruppeNr:");

        lblDetekNo.setText("DetektorNr:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHLBem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblEva)
                                            .addComponent(lblAlarmTid))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblAlarmType)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblGrpNo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblDetekNo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(lblDesc)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(lblAppear))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblHLBem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDesc))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(15, 15, 15)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblAlarmType)
                                                        .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblGrpNo)
                                                        .addComponent(lblDetekNo)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAlarmTid)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEva)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblAppear)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAdd)
                                    .addComponent(btnUpdate)
                                    .addComponent(btnDelete))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUsage, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                        .addGap(41, 41, 41)))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUsage;
    private javax.swing.JComboBox cboxAlarmType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAlarmTid;
    private javax.swing.JLabel lblAlarmType;
    private javax.swing.JLabel lblAppear;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDetekNo;
    private javax.swing.JLabel lblEva;
    private javax.swing.JLabel lblGrpNo;
    private javax.swing.JLabel lblHLBem;
    private javax.swing.JTable tblFremmøde;
    private javax.swing.JTextArea txtABem;
    private javax.swing.JTextArea txtHlKommentar;
    private javax.swing.JTextPane txtPAlarmTid;
    private javax.swing.JTextPane txtPDesc;
    private javax.swing.JTextPane txtPDetNo;
    private javax.swing.JTextPane txtPEvaNo;
    private javax.swing.JTextPane txtPGrpNo;
    // End of variables declaration//GEN-END:variables

    private void initOtherComponents() {
        txtPAlarmTid.setEditable(false);
        btnUsage.setEnabled(doesUsageExist());
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        txtHlKommentar.setEditable(false);
        txtHlKommentar.setLineWrap(true);
        txtABem.setLineWrap(true);
        txtABem.setText("");
        tblFremmøde.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnDelete.setEnabled(tblFremmøde.getSelectedRow() != -1);
                btnUpdate.setEnabled(tblFremmøde.getSelectedRow() != -1);

            }
        });
        txtPGrpNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        txtPDetNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        txtPAlarmTid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtPAlarmTid.getText().length() == 2) {
                    txtPAlarmTid.setText(txtPAlarmTid.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtPAlarmTid.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtPAlarmTid.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtPAlarmTid.getText().length() > 0 && txtPAlarmTid.getText().charAt(0) == '2') {
                    if (txtPAlarmTid.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtPAlarmTid.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtPAlarmTid.setText("");
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAppearance();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ILIndsats view = new ILIndsats();
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                dispose();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppearance();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAppearance();
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmTeam();
            }
        });
        btnUsage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUsage();
            }
        });
    }
    private void confirmTeam(){
        if (isInformationValid()){
            localAlarm.setEvaNo(Integer.parseInt(txtPEvaNo.getText()));
            localAlarm.setDetekterNo(Integer.parseInt(txtPDetNo.getText()));
            localAlarm.setGruppeNo(Integer.parseInt(txtPGrpNo.getText()));
            localAlarm.setAlarmType(cboxAlarmType.getSelectedItem().toString());
            localAlarm.setIlBemærkning(txtABem.getText());
            bllAlarm.confirmAlarm(localAlarm);
            
            bllAppearance.confirmAlarmTeam(localAlarm);
            
        }
    }
    private boolean doesUsageExist(){
         for (BEUsage theUsage : bllUsage.getAllUsages()) {
            if (theUsage.getAlarm() == localAlarm) {
                return true;
            }
        }
        return false;
    }

    private void openUsage() {
        ArrayList<BEUsage> localUsages = new ArrayList<>();
        for (BEUsage usage : bllUsage.getAllUsages()) {
            if (usage.getAlarm() == localAlarm) {
                localUsages.add(usage);
            }
        }
        HLUsageReport view = new HLUsageReport(localUsages);
        view.setModal(true);
        view.setVisible(true);
        usageConfirmed = true;
        btnUsage.setForeground(Color.GREEN);
    }

    private void deleteAppearance() {
        bllAppearance.deleteAppearance(bllAppearance.getAllHlGodkendtAppearances(localAlarm).get(tblFremmøde.convertRowIndexToView(tblFremmøde.getSelectedRow())));
        model.setAppearanceList(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        model.fireTableDataChanged();
    }

    private void updateAppearance() {
        ChangeTimeView ctView = new ChangeTimeView(bllAppearance.getAllHlGodkendtAppearances(localAlarm).get(tblFremmøde.convertRowIndexToView(tblFremmøde.getSelectedRow())));
        ctView.setModal(true);
        ctView.setLocationRelativeTo(null);
        ctView.setVisible(true);
        model.fireTableDataChanged();
    }

    private void addAppearance() {
        AddAppearanceView view = new AddAppearanceView(localAlarm);
        view.setModal(true);
        view.setVisible(true);
        model.setAppearanceList(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        model.fireTableDataChanged();
    }

    private void populateFremmødeTable() {
        model = new ILFremmødeTableModel(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        tblFremmøde.setModel(model);
        model.fireTableDataChanged();
    }

    private void getInfo() {
        cboxAlarmType.addItem("Falsk Alarm");
        cboxAlarmType.addItem("Blind Alarm");
        cboxAlarmType.addItem("Normal Alarm");
        cboxAlarmType.setSelectedItem(localAlarm.getAlarmType());
        txtHlKommentar.setText(localAlarm.getHlBemærkning());
        txtPGrpNo.setText(String.valueOf(localAlarm.getGruppeNo()));
        txtPDetNo.setText(String.valueOf(localAlarm.getDetekterNo()));
        txtPAlarmTid.setText(localAlarm.getTimeString());
        txtPDesc.setText(localAlarm.getDesc());
    }

    private boolean isInformationValid() {
        if (txtPAlarmTid.getText().isEmpty() && txtPDesc.getText().isEmpty() && txtPDetNo.getText().isEmpty() && txtPEvaNo.getText().isEmpty() && txtPGrpNo.getText().isEmpty()){
            return false;
        }
        return true;
    }
}
