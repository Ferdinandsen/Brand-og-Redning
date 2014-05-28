package GUI;

import BE.BEAppearance;
import BLL.BLLAppearance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Date;

/**
 * REFACTOR TIL ARVECLASS?????
 *
 * @author Team Kawabunga
 */
public class ViewChangeTime extends javax.swing.JDialog {

    boolean checkInIsCorrect = false;
    boolean checkOutIsCorrect = false;
    BLLAppearance bllAppearance;
    BEAppearance localAppearance;

    public ViewChangeTime(BEAppearance appearance) {
       
        
        localAppearance = appearance;
        bllAppearance = BLLAppearance.getInstance();
        
        initComponents();
        initOtherComponents();

        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Rediger tid");
        fillInformation();
        calculateHours();
    }

    private void fillInformation() {
        txtFornavn.setText(localAppearance.getFireman().getMedarbjeder().getFornavn());
        txtEfternavn.setText(localAppearance.getFireman().getMedarbjeder().getEfternavn());
        txtCheckIn.setText(localAppearance.getAlarm().getTimeString());
        txtCheckUd.setText(localAppearance.getCheckOutString());
        dateCheckIn.setDate(new Date(localAppearance.getCheckIn().getTime()));
        dateCheckUd.setDate(new Date(localAppearance.getCheckOut().getTime()));
    }

    private void initOtherComponents() {
        txtFornavn.setEditable(false);
        txtEfternavn.setEditable(false);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calculateHours();
                confirm();
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        txtCheckIn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtCheckIn.getText().length() == 2) {
                    txtCheckIn.setText(txtCheckIn.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtCheckIn.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtCheckIn.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtCheckIn.getText().length() > 0 && txtCheckIn.getText().charAt(0) == '2') {
                    if (txtCheckIn.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtCheckIn.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtCheckIn.setText("");
                }
            }
        });

        txtCheckUd.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (txtCheckUd.getText().length() == 2) {
                    txtCheckUd.setText(txtCheckUd.getText() + ":");
                }
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) && txtCheckUd.getText().length() != 5
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (Character.isDigit(vChar) && txtCheckUd.getText().length() == 0 && Integer.parseInt(String.valueOf(vChar)) > 2) {
                    e.consume();
                }
                if (txtCheckUd.getText().length() > 0 && txtCheckUd.getText().charAt(0) == '2') {
                    if (txtCheckUd.getText().length() == 1 && Integer.parseInt(String.valueOf(vChar)) > 3) {
                        e.consume();
                    }
                }
                if (Character.isDigit(vChar) && txtCheckUd.getText().length() == 3 && Integer.parseInt(String.valueOf(vChar)) > 5) {
                    e.consume();
                }
                if (vChar == KeyEvent.VK_BACK_SPACE) {
                    txtCheckUd.setText("");
                }
            }
        });

        txtCheckIn.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                if (txtCheckIn.getText().length() == 5 && txtCheckUd.getText().length() == 5) {
                    calculateHours();
                }
                if (txtCheckIn.getText().length() != 5) {
                    txtCheckIn.requestFocus();
                }
            }
        });

        txtCheckUd.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                if (txtCheckIn.getText().length() == 5 && txtCheckUd.getText().length() == 5) {
                    calculateHours();
                }
                if (txtCheckUd.getText().length() != 5) {
                    txtCheckUd.requestFocus();
                }
            }
        });
    }

    private void calculateHours() {
        lblTotal.setText(String.valueOf(bllAppearance.calculateTotalTime(getcheckInTime(), getcheckOutTime())));
    }

    private Timestamp getcheckInTime() {
        String[] checkInHour = txtCheckIn.getText().split(":");
        Timestamp checkInHourTs = new Timestamp(dateCheckIn.getDate().getYear(), dateCheckIn.getDate().getMonth(), dateCheckIn.getDate().getDate(), Integer.parseInt(checkInHour[0]), Integer.parseInt(checkInHour[1]), 0, 0);
        return checkInHourTs;
    }

    private Timestamp getcheckOutTime() {
        String[] checkOutHour = txtCheckUd.getText().split(":");
        Timestamp checkOutHourTs = new Timestamp(dateCheckUd.getDate().getYear(), dateCheckUd.getDate().getMonth(), dateCheckUd.getDate().getDate(), Integer.parseInt(checkOutHour[0]), Integer.parseInt(checkOutHour[1]), 0, 0);
        return checkOutHourTs;
    }

    private void confirm() {
        Timestamp newCheckInTime = getcheckInTime();
        Timestamp newCheckOutTime = getcheckOutTime();
        localAppearance.setCheckIn(newCheckInTime);
        localAppearance.setCheckOut(newCheckOutTime);
        localAppearance.setTotalTid(Integer.parseInt(lblTotal.getText()));
        bllAppearance.updateAppearance(localAppearance);
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFornavn = new javax.swing.JTextField();
        txtEfternavn = new javax.swing.JTextField();
        txtCheckIn = new javax.swing.JTextField();
        txtCheckUd = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        dateCheckUd = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Fornavn:");

        jLabel2.setText("Efternavn:");

        jLabel3.setText("Check ind tid:");

        jLabel4.setText("Check ud tid");

        jLabel5.setText("Total:");

        lblTotal.setText("X");

        btnOk.setText("OK");

        btnCancel.setText("Fortryd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateCheckUd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFornavn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtEfternavn))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotal))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnOk)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCheckUd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtFornavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtEfternavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCheckUd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateCheckUd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckUd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtCheckUd;
    private javax.swing.JTextField txtEfternavn;
    private javax.swing.JTextField txtFornavn;
    // End of variables declaration//GEN-END:variables
}
