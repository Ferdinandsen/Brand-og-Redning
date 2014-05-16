package GUI;

import BE.BEAlarm;
import BLL.BLLAppearance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class ILFremmødeliste extends javax.swing.JFrame {

    BLLAppearance bllAppearance;
    BEAlarm localAlarm;
    private ILFremmødeTableModel model;

    public ILFremmødeliste(BEAlarm alarm) {
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnCancel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHLBem)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAlarmTid)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblEva)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAlarmType)
                                    .addComponent(lblDesc))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblGrpNo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblDetekNo)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAppear)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addContainerGap())))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAlarmTid))
                                .addComponent(lblDesc, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEva, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAlarmType)
                                        .addComponent(cboxAlarmType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGrpNo))
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDetekNo))))
                        .addGap(18, 18, 18)
                        .addComponent(lblAppear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHLBem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        txtHlKommentar.setEditable(false);
        txtHlKommentar.setLineWrap(true);
        txtABem.setLineWrap(true);

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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnUsage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void populateFremmødeTable() {
        model = new ILFremmødeTableModel(bllAppearance.getAllHlGodkendtAppearances(localAlarm));
        tblFremmøde.setModel(model);
        model.fireTableDataChanged();
    }

    private void getInfo() {
        cboxAlarmType.addItem(localAlarm.getAlarmType());
        if (localAlarm.getAlarmType().toString().equals("Normal Alarm")) {
            cboxAlarmType.addItem("Blind Alarm");
            cboxAlarmType.addItem("Falsk Alarm");
        }
        if (localAlarm.getAlarmType().toString().equals("Blind Alarm")) {
            cboxAlarmType.addItem("Normal Alarm");
            cboxAlarmType.addItem("Falsk Alarm");
        }
        if (localAlarm.getAlarmType().toString().equals("Falsk Alarm")) {
            cboxAlarmType.addItem("Blind Alarm");
            cboxAlarmType.addItem("Normal Alarm");
        }

        txtPGrpNo.setText(String.valueOf(localAlarm.getGruppeNo()));
        txtPDetNo.setText(String.valueOf(localAlarm.getDetekterNo()));
        txtPAlarmTid.setText(localAlarm.getTimeString());
        txtPDesc.setText(localAlarm.getDesc());
    }
}
