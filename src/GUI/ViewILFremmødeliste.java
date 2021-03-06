package GUI;

import TableModels.TableModelILFremmøde;
import Renderes.RenderILFremmødeTableCell;
import BE.BEAlarm;
import BE.BELogin;
import BE.BEUsage;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLOdinPdf;
import BLL.BLLUsage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class ViewILFremmødeliste extends JDialog {

    BLLAppearance bllAppearance;
    BLLAlarm bllAlarm;
    BLLUsage bllUsage;
    BEAlarm localAlarm;
    private TableModelILFremmøde model;
    boolean usageConfirmed = false;
    BELogin localLogin;

    public ViewILFremmødeliste(BEAlarm alarm, BELogin log) {
        localLogin = log;
        bllAlarm = BLLAlarm.getInstance();
        bllUsage = BLLUsage.getInstance();
        bllAppearance = BLLAppearance.getInstance();
        localAlarm = alarm;
        initComponents();
        initOtherComponents();
        this.setTitle("Indsatsleder - Indsats");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        getInfo();
        populateFremmødeTable();
        addCellRenderer();
    }
/**
 * adds a cell renderer to our table
 */
    private void addCellRenderer() {

        RenderILFremmødeTableCell renderer = new RenderILFremmødeTableCell(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
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
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblAppear = new javax.swing.JLabel();
        btnUsage = new javax.swing.JButton();
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
        txtAlarmTid = new javax.swing.JTextField();
        txtBeskrivelse = new javax.swing.JTextField();
        txtEvaNo = new javax.swing.JTextField();
        txtGruppeNr = new javax.swing.JTextField();
        txtDetektorNr = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        tblFremmøde.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblFremmøde);

        txtABem.setColumns(20);
        txtABem.setRows(5);
        txtABem.setText("Her kan Niels skrive bemærkninger til indsatsen\n- f.eks om der var fremmede enheder med?!");
        jScrollPane2.setViewportView(txtABem);

        btnAdd.setText("Tilføj");

        btnUpdate.setText("Opdater");

        btnDelete.setText("Slet");

        lblAppear.setText("Deltagere:");

        btnUsage.setForeground(new java.awt.Color(255, 102, 0));
        btnUsage.setText("Bekræft Forbrug");

        jLabel1.setText("Bemærkninger:");

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

        btnCancel.setText("Cancel");

        btnOK.setText("OK");

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
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(110, 110, 110)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAlarmTid)
                                            .addComponent(lblEva)
                                            .addComponent(lblAppear))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAlarmTid, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                            .addComponent(txtEvaNo))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblAlarmType)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblGrpNo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtGruppeNr, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblDetekNo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDetektorNr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDesc)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBeskrivelse, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblHLBem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblAlarmType)
                                            .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGrpNo)
                                            .addComponent(lblDetekNo)
                                            .addComponent(txtGruppeNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDetektorNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblAlarmTid)
                                            .addComponent(txtAlarmTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDesc)
                                            .addComponent(txtBeskrivelse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblEva)
                                            .addComponent(txtEvaNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(16, 16, 16)
                                        .addComponent(lblAppear)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAdd)
                                    .addComponent(btnUpdate)
                                    .addComponent(btnDelete))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUsage, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel)
                            .addComponent(btnOK))))
                .addContainerGap())
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
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTextField txtAlarmTid;
    private javax.swing.JTextField txtBeskrivelse;
    private javax.swing.JTextField txtDetektorNr;
    private javax.swing.JTextField txtEvaNo;
    private javax.swing.JTextField txtGruppeNr;
    private javax.swing.JTextArea txtHlKommentar;
    // End of variables declaration//GEN-END:variables

    /**
     * set component settings, and initialize listeners
     */
    private void initOtherComponents() {
        txtAlarmTid.setEditable(false);
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

        txtEvaNo.addKeyListener(new KeyAdapter() {
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

        txtGruppeNr.addKeyListener(new KeyAdapter() {
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

        txtDetektorNr.addKeyListener(new KeyAdapter() {
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

        txtAlarmTid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtAlarmTid.getText().length() == 2) {
                    txtAlarmTid.setText(txtAlarmTid.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtAlarmTid.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtAlarmTid.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtAlarmTid.getText().length() > 0 && txtAlarmTid.getText().charAt(0) == '2') {
                    if (txtAlarmTid.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtAlarmTid.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtAlarmTid.setText("");
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
                cancel();
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
    
    /**
     * if 'isInformationValid' is true, it will confirm the appearances on the alarm
     */

    private void confirmTeam() {
        if (isInformationValid()) {
            localAlarm.setEvaNo(Integer.parseInt(txtEvaNo.getText()));
            if (!txtDetektorNr.getText().isEmpty()) {
                localAlarm.setDetekterNo(Integer.parseInt(txtDetektorNr.getText()));
            } else {
                localAlarm.setDetekterNo(0);
            }
            if (!txtGruppeNr.getText().isEmpty()) {
                localAlarm.setGruppeNo(Integer.parseInt(txtGruppeNr.getText()));
            } else {
                localAlarm.setGruppeNo(0);
            }
            localAlarm.setAlarmType(cboxAlarmType.getSelectedItem().toString());
            if (!txtABem.getText().isEmpty()) {
                localAlarm.setIlBemærkning(txtABem.getText());
            } else {
                localAlarm.setIlBemærkning("");
            }

            localAlarm.setDesc(txtBeskrivelse.getText());

            bllAlarm.confirmAlarm(localAlarm);

            bllAppearance.confirmAlarmTeam(localAlarm);
            JOptionPane.showMessageDialog(this, "Indsatsen er nu bekræftet");
            createPDF();
            dispose();
        }
    }

    /**
     * opens a new frame, with the usage for the localAlarm
     */
    private void openUsage() {
        ArrayList<BEUsage> localUsages = new ArrayList<>();
        for (BEUsage usage : bllUsage.getAllUsages()) {
            if (usage.getAlarm() == localAlarm) {
                localUsages.add(usage);
            }
        }
        ViewHLUsageReport frame = new ViewHLUsageReport(localAlarm, localUsages);
        frame.setVisible(true);
        usageConfirmed = true;
        btnUsage.setForeground(Color.GREEN);
    }

    /**
     * goes back to the previous frame
     */
    private void cancel() {
        ViewILIndsats frame = new ViewILIndsats(localLogin);
        dispose();
        frame.setVisible(true);

    }
/**
 * deletes the selected appearance
 */
    private void deleteAppearance() {
        bllAppearance.deleteAppearance(bllAppearance.getAllHlGodkendtAppearances(localAlarm).get(tblFremmøde.convertRowIndexToView(tblFremmøde.getSelectedRow())));
        model.setAppearanceList(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        model.fireTableDataChanged();
    }

    /**
     * opens the update frame with the selected appearance
     */
    private void updateAppearance() {
        ViewChangeTime frame = new ViewChangeTime(bllAppearance.getAllHlGodkendtAppearances(localAlarm).get(tblFremmøde.convertRowIndexToView(tblFremmøde.getSelectedRow())));
        frame.setVisible(true);
        model.fireTableDataChanged();
    }

    /**
     * open the add appearance frame, and refresh the table, so the added appearance will be listed
     */
    private void addAppearance() {
        ViewAddAppearance frame = new ViewAddAppearance(localAlarm, localLogin);
        frame.setVisible(true);
        model.setAppearanceList(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        model.fireTableDataChanged();
    }

    /**
     * fills our table with appearances
     */
    private void populateFremmødeTable() {
        model = new TableModelILFremmøde(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        tblFremmøde.setModel(model);
        model.fireTableDataChanged();
    }

    /**
     * sets our components with the information from the localAlarm(the alarm from the previous form)
     */
    private void getInfo() {
        cboxAlarmType.addItem("Normal Alarm");
        cboxAlarmType.addItem("Falsk Alarm");
        cboxAlarmType.addItem("Blind Alarm");
        cboxAlarmType.setSelectedItem(localAlarm.getAlarmType());
        txtHlKommentar.setText(localAlarm.getHlBemærkning());
        txtGruppeNr.setText(String.valueOf(localAlarm.getGruppeNo()));
        txtDetektorNr.setText(String.valueOf(localAlarm.getDetekterNo()));
        txtAlarmTid.setText(localAlarm.getTimeString());
        txtBeskrivelse.setText(localAlarm.getDesc());
    }

    /**
     * 
     * @return  true if the necessary information is entered
     */
    private boolean isInformationValid() {
        if (txtEvaNo.getText().isEmpty() && usageConfirmed) {
            JOptionPane.showMessageDialog(this, "Indtast venligst EVA nr.");
        } else if (!usageConfirmed && !txtEvaNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bekræft venligst forbrug");
        } else if (txtEvaNo.getText().isEmpty() && !usageConfirmed) {
            JOptionPane.showMessageDialog(this, "Bekræft forbrug og indstast EVA nr.");
        }
        return !txtEvaNo.getText().isEmpty() && usageConfirmed;
    }

    /**
     * creates a new PDF
     */
    private void createPDF() {
        new BLLOdinPdf(localAlarm, localLogin);
        JOptionPane.showMessageDialog(this, "PDF'en er nu lavet på skrivebordet!");
    }
}
