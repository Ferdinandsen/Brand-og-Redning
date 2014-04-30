package GUI;

import BE.BEVehicle;
import BLL.BLLTimelist;
import BLL.BLLAppearance;
import BLL.BLLVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HLAfterAction1 extends javax.swing.JFrame {

    BLLVehicle bllVehicle;
    BLLTimelist bllTime;
    BLLAppearance bllAppearance;
    private FremmødeTableModel model;

    public HLAfterAction1() {
        initComponents();
        initOtherComponents();

        bllVehicle = BLLVehicle.getInstance();
        bllTime = BLLTimelist.getInstance();
        bllAppearance = BLLAppearance.getInstance();

        this.setTitle("Hl After Action 1");
        this.setResizable(false);

        fillCboxKøretøj();
        populateFremmødeTable();


    }

    private void initOtherComponents() {
        btnHent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

    }

    private void fillCboxKøretøj() {
        for (BEVehicle veh : bllVehicle.GetVehicles()) {
            cboxKøretøj.addItem(veh);
        }
    }

    private void populateFremmødeTable() {
        model = new FremmødeTableModel(bllAppearance.getAppearances());
        tblTider.setModel(model);
        model.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lblDato = new javax.swing.JLabel();
        btnBekæft = new javax.swing.JButton();
        lblTid = new javax.swing.JLabel();
        btnHent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTider = new javax.swing.JTable();
        lblKøretøj = new javax.swing.JLabel();
        cboxKøretøj = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

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

        lblKøretøj.setText("Køretøj:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblKøretøj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHent, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBekæft, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDato)
                        .addComponent(lblTid)
                        .addComponent(btnHent)
                        .addComponent(lblKøretøj)
                        .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBekæft)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBekæft;
    private javax.swing.JButton btnHent;
    private javax.swing.JComboBox cboxKøretøj;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDato;
    private javax.swing.JLabel lblKøretøj;
    private javax.swing.JLabel lblTid;
    private javax.swing.JTable tblTider;
    // End of variables declaration//GEN-END:variables
}
