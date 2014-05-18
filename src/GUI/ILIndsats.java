package GUI;

import BE.BEAlarm;
import BLL.BLLAlarm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class ILIndsats extends javax.swing.JFrame {

    BLLAlarm bllAlarm;
    private UnfinishedFremmødeModel model;

    public ILIndsats() {
        bllAlarm = BLLAlarm.getInstance();
        this.setResizable(false);
        this.setTitle("Indsatsleder - Fremmødeliste");
        initComponents();
        initOtherComponents();
        populateFremmødeTable();
        addCellRenderer();
    }

    private void addCellRenderer() {

        FremmødeTableCellRenderer renderer = new FremmødeTableCellRenderer();
        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblFremmøder.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    private void populateFremmødeTable() {
        model = new UnfinishedFremmødeModel(bllAlarm.getAllUnfinishedAlarms());
        tblFremmøder.setModel(model);
        model.fireTableDataChanged();
    }

    private void initOtherComponents() {
        btnHent.setEnabled(false);

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainView view = new MainView();
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                dispose();
            }
        });
        tblFremmøder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnHent.setEnabled(tblFremmøder.getSelectedRow() != -1);

            }
        });
        btnHent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BEAlarm alarm = bllAlarm.getAllUnfinishedAlarms().get(tblFremmøder.convertRowIndexToView(tblFremmøder.getSelectedRow()));
                System.out.println(alarm);
                System.out.println(alarm.getForbrug().getAmount());
                ILFremmødeliste view = new ILFremmødeliste(alarm);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFremmøder = new javax.swing.JTable();
        btnHent = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblFremmøder.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFremmøder.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblFremmøder);

        btnHent.setText("Hent indsats");

        btnBack.setText("Tilbage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHent, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHent)
                    .addComponent(btnBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnHent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFremmøder;
    // End of variables declaration//GEN-END:variables
}
