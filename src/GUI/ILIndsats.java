package GUI;

import BE.BEAction;

/**
 *
 * @author Team Kawabunga
 */
public class ILIndsats extends javax.swing.JFrame {

    private BEAction action;

    /**
     * Creates new form ILIndsats
     */
    public ILIndsats(BEAction a) {
        action = a;
        initComponents();
        //        initOtherComponents();
        this.setTitle("Indsatsleders indsats rapport");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getInfo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblIndsats = new javax.swing.JTable();
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
        jScrollPane7 = new javax.swing.JScrollPane();
        txtPKørselType = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtPAlarmType = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPGrpNo = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtPDetNo = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        lblHLBem = new javax.swing.JLabel();
        lblAlarmTid = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblEva = new javax.swing.JLabel();
        lblKørselType = new javax.swing.JLabel();
        lblAlarmType = new javax.swing.JLabel();
        lblGrpNo = new javax.swing.JLabel();
        lblDetekNo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblIndsats.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblIndsats);

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

        btnUsage.setBackground(new java.awt.Color(255, 102, 0));
        btnUsage.setForeground(new java.awt.Color(255, 102, 0));
        btnUsage.setText("Bekræft Forbrug");

        jScrollPane4.setViewportView(txtPDesc);

        jScrollPane5.setViewportView(txtPAlarmTid);

        txtPEvaNo.setToolTipText("");
        jScrollPane6.setViewportView(txtPEvaNo);

        jScrollPane7.setViewportView(txtPKørselType);

        txtPAlarmType.setToolTipText("");
        jScrollPane8.setViewportView(txtPAlarmType);

        jScrollPane9.setViewportView(txtPGrpNo);

        jScrollPane10.setViewportView(txtPDetNo);

        jLabel1.setText("Bemærkninger");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Her kan Niels læse\nbeskeder fra HL");
        jScrollPane11.setViewportView(jTextArea2);

        lblHLBem.setText("HL bemærkninger:");

        lblAlarmTid.setText("Alarm tid:");

        lblDesc.setText("Beskrivelse:");

        lblEva.setText("EvaNo:");

        lblKørselType.setText("Kørsels type:");

        lblAlarmType.setText("Alarm type:");

        lblGrpNo.setText("GruppeNo");

        lblDetekNo.setText("DetektorNo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblHLBem, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(btnUsage)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAppear)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnUpdate)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(320, 320, 320)
                                    .addComponent(btnCancel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlarmTid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDesc)
                            .addComponent(lblKørselType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAlarmType)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGrpNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDetekNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAlarmTid)
                            .addComponent(lblDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblEva)
                            .addComponent(lblAlarmType)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGrpNo)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblKørselType)
                    .addComponent(lblDetekNo)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAppear)
                    .addComponent(lblHLBem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnUsage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblAlarmTid;
    private javax.swing.JLabel lblAlarmType;
    private javax.swing.JLabel lblAppear;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDetekNo;
    private javax.swing.JLabel lblEva;
    private javax.swing.JLabel lblGrpNo;
    private javax.swing.JLabel lblHLBem;
    private javax.swing.JLabel lblKørselType;
    private javax.swing.JTable tblIndsats;
    private javax.swing.JTextArea txtABem;
    private javax.swing.JTextPane txtPAlarmTid;
    private javax.swing.JTextPane txtPAlarmType;
    private javax.swing.JTextPane txtPDesc;
    private javax.swing.JTextPane txtPDetNo;
    private javax.swing.JTextPane txtPEvaNo;
    private javax.swing.JTextPane txtPGrpNo;
    private javax.swing.JTextPane txtPKørselType;
    // End of variables declaration//GEN-END:variables

    private void initOtherComponents() {

    }

    private void getInfo() {
        txtPAlarmTid.setText(action.getAppearance().getAlarm().getTimeString());
        txtPDesc.setText(action.getAppearance().getAlarm().getDesc());
        txtPKørselType.setText("" + action.getAppearance().getType());
        txtPAlarmType.setText(action.getAlarmType());
        txtPGrpNo.setText(action.getGruppeNo());
        txtPDetNo.setText(action.getDetektorNo());
    }
}
