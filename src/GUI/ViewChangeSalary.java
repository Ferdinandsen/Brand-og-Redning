package GUI;

import BE.BEAppearance;
import BLL.BLLAppearance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Team Kawabunga
 */
public class ViewChangeSalary extends javax.swing.JDialog {

    BLLAppearance bllAppearance;
    BEAppearance localAppearance;
    final String HOLDLEDER = "Holdleder";
    final String BRANDMAND = "Brandmand";

    /**
     * Creates new form
     */
    public ViewChangeSalary(BEAppearance appearance) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Rediger løn");
        localAppearance = appearance;
        bllAppearance = BLLAppearance.getInstance();

        initComponents();
        initOtherComponents();

        fillCheckBox();
        fillInformation();
        calculateHours();
    }

    private void fillCheckBox() {
        cboxFunktion.addItem(HOLDLEDER);
        cboxFunktion.addItem(BRANDMAND);
    }

    /**
     * fills in the information from localappearance.
     */
    private void fillInformation() {
        txtFornavn.setText(localAppearance.getFireman().getMedarbjeder().getFornavn());
        txtEfternavn.setText(localAppearance.getFireman().getMedarbjeder().getEfternavn());
        txtCheckIn.setText(localAppearance.getAlarm().getTimeString());
        txtCheckUd.setText(localAppearance.getCheckOutString());
        dateCheckIn.setDate(new Date(localAppearance.getCheckIn().getTime()));
        dateCheckUd.setDate(new Date(localAppearance.getCheckOut().getTime()));
        if (localAppearance.isHoldleder()) {
            cboxFunktion.setSelectedItem(HOLDLEDER);
        } else {
            cboxFunktion.setSelectedItem(BRANDMAND);
        }
    }

    /**
     * adds listeners to the buttons and textfields
     */
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

        txtCheckIn.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

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

    /**
     * updates/sets the text on the lblTotal Label
     */
    private void calculateHours() {
        lblTotal.setText(String.valueOf(bllAppearance.calculateTotalTime(getcheckInTime(), getcheckOutTime())));
    }

    /**
     * Retrieves the user input time from txtCheckin and splits it in to hour
     * and min.
     *
     * @returns the new Timestamp
     */
    private Timestamp getcheckInTime() {
        String[] checkInHour = txtCheckIn.getText().split(":");
        Timestamp checkInHourTs = new Timestamp(dateCheckIn.getDate().getYear(), dateCheckIn.getDate().getMonth(), dateCheckIn.getDate().getDate(), Integer.parseInt(checkInHour[0]), Integer.parseInt(checkInHour[1]), 0, 0);
        return checkInHourTs;
    }

    /**
     * Retrieves the user input from txtcheckUd and splits it into hour and min.
     *
     * @returns the new Timestamp
     */
    private Timestamp getcheckOutTime() {
        String[] checkOutHour = txtCheckUd.getText().split(":");
        Timestamp checkOutHourTs = new Timestamp(dateCheckUd.getDate().getYear(), dateCheckUd.getDate().getMonth(), dateCheckUd.getDate().getDate(), Integer.parseInt(checkOutHour[0]), Integer.parseInt(checkOutHour[1]), 0, 0);
        return checkOutHourTs;
    }

    /**
     * Gathers the information from changeFunction, newCheckIn, newCheckOut and
     * puts it on localappearance to be sent to BllAppearance.updateAppearance.
     */
    private void confirm() {
        Timestamp newCheckInTime = getcheckInTime();
        Timestamp newCheckOutTime = getcheckOutTime();
        localAppearance.setHoldleder(changeFunction());
        localAppearance.setCheckIn(newCheckInTime);
        localAppearance.setCheckOut(newCheckOutTime);
        localAppearance.setTotalTid(Integer.parseInt(lblTotal.getText()));
        bllAppearance.updateAppearance(localAppearance);
        dispose();
    }

    /**
     * determines if the function is HOLDLEDER or BRANDMAND
     *
     * @returns true if user input is HOLDLEDER else false(BRANDMAND)
     *
     */
    private boolean changeFunction() {
        return cboxFunktion.getSelectedItem().equals(HOLDLEDER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFornavn = new javax.swing.JTextField();
        txtEfternavn = new javax.swing.JTextField();
        txtCheckIn = new javax.swing.JTextField();
        txtCheckUd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        dateCheckUd = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboxFunktion = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Fornavn:");

        lblTotal.setText("X");

        jLabel2.setText("Efternavn:");

        btnOk.setText("OK");

        btnCancel.setText("Cancel");

        jLabel3.setText("Check ind tid:");

        jLabel5.setText("Total:");

        jLabel4.setText("Check ud tid");

        jLabel6.setText("Funktion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOk)
                                .addGap(28, 28, 28)
                                .addComponent(btnCancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateCheckUd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboxFunktion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckUd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEfternavn, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(txtFornavn)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFornavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEfternavn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(txtCheckUd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateCheckUd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboxFunktion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private javax.swing.JComboBox cboxFunktion;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckUd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtCheckUd;
    private javax.swing.JTextField txtEfternavn;
    private javax.swing.JTextField txtFornavn;
    // End of variables declaration//GEN-END:variables
}
