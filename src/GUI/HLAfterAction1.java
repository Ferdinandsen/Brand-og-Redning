
package GUI;

import BE.BETime;
import BE.BEVehicle;
import BLL.BLLTimelist;
import BLL.BLLVehicle;
public class HLAfterAction1 extends javax.swing.JFrame {
BLLVehicle bllVehicle;
    BLLTimelist bllTime;
    public HLAfterAction1() {
        bllVehicle = BLLVehicle.getInstance();
        bllTime = BLLTimelist.getInstance();
        initComponents();
        this.setTitle("Hl After Action 1");
        this.setResizable(false);
        
        fillCboxDato();
        fillCboxTid();
        fillCboxKøretøj();
    }

    private void fillCboxDato() {
//        for (BETime time : bllTime.){
//            cboxKøretøj.addItem(veh);
//        }
        for (String date : bllTime.getDates()){
            cboxDato.addItem(date);
        }
    }
    
    private void fillCboxTid() {
    }
    
    private void fillCboxKøretøj() {
        for (BEVehicle veh : bllVehicle.GetVehicles()){
            cboxKøretøj.addItem(veh);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxDato = new javax.swing.JComboBox();
        lblDato = new javax.swing.JLabel();
        btnBekæft = new javax.swing.JButton();
        lblTid = new javax.swing.JLabel();
        cboxTid = new javax.swing.JComboBox();
        btnHent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTider = new javax.swing.JTable();
        lblKøretøj = new javax.swing.JLabel();
        cboxKøretøj = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDato.setText("Dato:");

        btnBekæft.setText("Bekræft hold");

        lblTid.setText("Tid:");

        cboxTid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11:30", "12:00", "12:30", "13:00", "13:30" }));

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKøretøj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBekæft, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDato)
                    .addComponent(cboxDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTid)
                    .addComponent(cboxTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHent)
                    .addComponent(lblKøretøj)
                    .addComponent(cboxKøretøj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JComboBox cboxDato;
    private javax.swing.JComboBox cboxKøretøj;
    private javax.swing.JComboBox cboxTid;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDato;
    private javax.swing.JLabel lblKøretøj;
    private javax.swing.JLabel lblTid;
    private javax.swing.JTable tblTider;
    // End of variables declaration//GEN-END:variables
}
