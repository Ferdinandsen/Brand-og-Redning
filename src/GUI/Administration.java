package GUI;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLFireman;
import BLL.BLLLønPdf;
import BLL.BLLPdf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Team Kawabunga
 */
public class Administration extends javax.swing.JFrame {

    final long DAY = 86400000; //1 day in milliseconds
    BLLAppearance bllAppearance;
    BLLFireman bllFireman;
    BLLAlarm bllAlarm;
    LønTableModel model;
    DefaultListModel<String> alarmliste;

    /**
     * Creates new form Administration
     */
    public Administration() {
        bllAppearance = BLLAppearance.getInstance();
        bllFireman = BLLFireman.getInstance();
        bllAlarm = BLLAlarm.getInstance();
        initComponents();
        initOtherComponents();
        populateLønTable(null, dchoFra.getDate(), dchoTil.getDate());
        fillcboxBrandMand();
        populateAlarmList();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Administration");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxBM = new javax.swing.JComboBox();
        dchoFra = new com.toedter.calendar.JDateChooser();
        dchoTil = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBM = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAlarm = new javax.swing.JList();
        btnOK = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnHent = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblTid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblBM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblBM);

        lstAlarm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        lstAlarm.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstAlarm);

        btnOK.setText("Gem");

        btnEdit.setText("Rediger");

        jLabel1.setText("Fra:");

        jLabel2.setText("Til:");

        jLabel3.setText("Ikke godkendte alarmer:");

        btnHent.setText("Hent");

        btnBack.setText("Tilbage");

        lblTid.setText("Total tid:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTid, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboxBM, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dchoFra, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dchoTil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHent, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 133, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxBM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dchoFra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dchoTil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(btnHent))
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOK)
                            .addComponent(btnBack)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHent;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cboxBM;
    private com.toedter.calendar.JDateChooser dchoFra;
    private com.toedter.calendar.JDateChooser dchoTil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTid;
    private javax.swing.JList lstAlarm;
    private javax.swing.JTable tblBM;
    // End of variables declaration//GEN-END:variables

    private void initOtherComponents() {
        lstAlarm.setEnabled(false);
        long dagenstal = new Date().getDate() * DAY - DAY;// den første i måneden, fra dd.
        dchoFra.setDate(new Date(new Date().getTime() - dagenstal));
        dchoTil.setDate(new Date());
        btnEdit.setEnabled(false);

        tblBM.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnEdit.setEnabled(tblBM.getSelectedRow() != -1);

            }
        });

        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateSalary();
            }
        });

        btnHent.setText("Hent");
        btnHent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxBM.getSelectedIndex() == 0) {
                    BEFireman localFireman = null;
                    populateLønTable(localFireman, dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY));
                } else {
                    populateLønTable((BEFireman) cboxBM.getSelectedItem(), dchoFra.getDate(), dchoTil.getDate());
                }
            }
        });

        btnBack.setText("Tilbage");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<BEAppearance> app = new ArrayList<>();
                LønTableModel løn = (LønTableModel) tblBM.getModel();
                for (int i = 0; i < tblBM.getRowCount(); i++) {
                    app.add(løn.getRow(i));
                }
                bllAppearance.EndSalary(app);
                 JOptionPane.showMessageDialog(null, "Lønnen bekræftet");
                 createPDF(app);
            }
        });
    }

    private void populateAlarmList() {
        alarmliste = new DefaultListModel<>();
        lstAlarm.setModel(alarmliste);
        for (BEAlarm a : bllAlarm.getAllUnfinishedAlarms()) {
        }
    }

    private void populateLønTable(BEFireman localFireman, Date from, Date to) {
        model = new LønTableModel(bllAppearance.getAllAppearancesWithDateCriteria(localFireman, from, to));
        tblBM.setModel(model);
        model.fireTableDataChanged();
        setTotalTime();
    }

    private void fillcboxBrandMand() {
        cboxBM.addItem("Alle Brandmænd");
        for (BEFireman bm : bllFireman.getAllfiremen()) {
            cboxBM.addItem(bm);
        }
    }

    private void updateSalary() {
        if (cboxBM.getSelectedIndex() == 0) {
            BEFireman localFireman = null;
            ChangeSalaryView frame = new ChangeSalaryView(bllAppearance.getAllAppearancesWithDateCriteria(localFireman, dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)).get(tblBM.convertRowIndexToView(tblBM.getSelectedRow())));
            frame.setVisible(true);
        } else {
            try {
                ChangeSalaryView frame = new ChangeSalaryView(bllAppearance.getAllAppearancesWithDateCriteria((BEFireman) cboxBM.getSelectedItem(), dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)).get(tblBM.convertRowIndexToView(tblBM.getSelectedRow())));
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Du skal huske at hente først!");
            }
        }
        tblBM.setModel(new LønTableModel(bllAppearance.getAllAppearancesWithDateCriteria(null, dchoFra.getDate(), dchoTil.getDate())));
    }

    private void setTotalTime() {
        int amount = 0;
        for (int i = 0; i < tblBM.getRowCount(); i++) {
            amount = +(int) tblBM.getModel().getValueAt(i, 7);
        }
        lblTid.setText("Total Tid: " + amount);
    }
     private void createPDF(ArrayList<BEAppearance> app){
        BLLLønPdf pdf = new BLLLønPdf(app);
        JOptionPane.showMessageDialog(this, "PDF'en er nu lavet på skrivebordet!");
    }
}
