package GUI;

import TableModels.TableModelUnfinishedFremmøde;
import Renderes.RenderUnfinishedAlarmsCell;
import BE.BEAlarm;
import BE.BELogin;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
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
public class ViewILIndsats extends javax.swing.JFrame {

    BLLAlarm bllAlarm;
    BLLAppearance bllAppearance;
    private TableModelUnfinishedFremmøde model;
    BELogin localLogin;
    private static ViewILIndsats m_instance = null;

    private ViewILIndsats(BELogin log) {
        localLogin = log;
        bllAlarm = BLLAlarm.getInstance();
        bllAppearance = BLLAppearance.getInstance();
        this.setResizable(false);
        this.setTitle("Indsatsleder - Indsatser");
        initComponents();
        initOtherComponents();
        populateFremmødeTable();
        addCellRenderer();
        this.setLocationRelativeTo(null);
    }

    public static ViewILIndsats getInstance(BELogin log) {
        if (m_instance == null) {
            m_instance = new ViewILIndsats(log);
        }
//        bllAlarm.update();
//        bllAppearance.update();
        return m_instance;
    }

    private void addCellRenderer() {
        RenderUnfinishedAlarmsCell renderer = new RenderUnfinishedAlarmsCell();
        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblFremmøder.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    private void populateFremmødeTable() {
        model = new TableModelUnfinishedFremmøde(bllAlarm.getAllHLGodkendtAndNotILGodkend());
        tblFremmøder.setModel(model);
        model.fireTableDataChanged();
    }

    private void initOtherComponents() {
        btnHent.setEnabled(false);

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
                BEAlarm alarm = bllAlarm.getAllHLGodkendtAndNotILGodkend().get(tblFremmøder.convertRowIndexToView(tblFremmøder.getSelectedRow()));
                ViewILFremmødeliste frame = new ViewILFremmødeliste(alarm, localLogin);
                frame.setVisible(true);
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(btnHent, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnHent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
