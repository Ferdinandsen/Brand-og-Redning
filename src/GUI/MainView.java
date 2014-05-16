package GUI;

import BE.BEAlarm;
import BE.BELogin;
import BE.BEOdinAlarm;
import BLL.BLLAlarm;
import BLL.BLLEmployee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Kawabunga
 */
public class MainView extends javax.swing.JFrame {

    BLLAlarm bllAlarm;
    BLLEmployee bllEmployee;
    BELogin log;
    DefaultListModel model = new DefaultListModel();

    /**
     * Creates new form MainView
     */
    public MainView() {
        this.setResizable(false);
//        this.setLocationRelativeTo(null);
        bllAlarm = BLLAlarm.getInstance();
        bllEmployee = BLLEmployee.getInstance();
        initComponents();
        initLogInComponents();
        initOtherComponets();

        ActionListRenderer renderer = new ActionListRenderer();
        lstAction.setCellRenderer(renderer);
        fillListActions();
        lstAction.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogIn = new javax.swing.JLabel();
        btnErrorReport = new javax.swing.JButton();
        btnConfirmApp = new javax.swing.JButton();
        btnIL = new javax.swing.JButton();
        btnSalery = new javax.swing.JButton();
        txtNote = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogOut = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnLogIn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAction = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogIn.setText("Logget ind som:");

        btnErrorReport.setText("Fejl rapport");

        btnConfirmApp.setText("Bekræft Fremmøder");

        btnIL.setText("IndsatsLeder");

        btnSalery.setText("LØN **");

        txtNote.setText("Husk Der er Øvelse d. 21/6 Kl13.00");

        btnLogOut.setText("Log af");

        jLabel1.setText("Fornavn:");

        btnLogIn.setText("Log på");

        jLabel2.setText("Password:");

        jLabel3.setText("Odin's seneste 20 alarmer");

        jScrollPane1.setViewportView(lstAction);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNote, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnLogOut))
                                        .addComponent(lblLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(24, 24, 24)
                                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnConfirmApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnIL, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnErrorReport, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addComponent(btnSalery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnConfirmApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnErrorReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogOut)
                            .addComponent(btnLogIn)))
                    .addComponent(btnIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmApp;
    private javax.swing.JButton btnErrorReport;
    private javax.swing.JButton btnIL;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnSalery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogIn;
    private javax.swing.JList lstAction;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNote;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    private void fillListActions(){
        for (BEOdinAlarm odinAlarm : bllAlarm.getAllOdinAlarms()){
            model.addElement(odinAlarm);
        }
    }
    private void initOtherComponets() {
        txtNote.setEditable(false);
        lblLogIn.setText("Ikke logget på");
        btnConfirmApp.setEnabled(false);
        btnIL.setEnabled(false);
        btnSalery.setEnabled(false);
        btnConfirmApp.setText("Bekræft fremmøder");

        btnConfirmApp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HLAfterAction main = HLAfterAction.getInstance(log);
                main.setVisible(true);
            }
        });

        btnErrorReport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HLErrorReport frame = new HLErrorReport();
                frame.setVisible(true);
            }
        });
    }

    private void initLogInComponents() {
        btnLogOut.setEnabled(false);
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (vChar == KeyEvent.VK_ENTER) {
                    tryLogin();
                }
            }
        });

        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (vChar == KeyEvent.VK_ENTER) {
                    tryLogin();
                }
            }
        });

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryLogin();
            }
        });

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfirmApp.setEnabled(false);
                btnIL.setEnabled(false);
                btnSalery.setEnabled(false);
                lblLogIn.setText("Ikke logget på");
                clearlogintxt();
                btnLogIn.setEnabled(true);
                btnLogOut.setEnabled(false);
            }
        });
    }

    private void clearlogintxt() {
        txtName.setText(null);
        txtPassword.setText(null);
    }

    private void tryLogin() {
        if (btnLogIn.isEnabled()) {
            if (isInformationValid()) {
                if (bllEmployee.doesUserExist(txtName.getText(), txtPassword.getPassword())) {
                    log = bllEmployee.getLogin(txtName.getText());
                    lblLogIn.setText("Logget ind som: " + log.getMedarbejder().toString());
                    btnLogIn.setEnabled(false);
                    btnLogOut.setEnabled(true);
                    clearlogintxt();
                    if (log.isHoldleder()) {
                        btnConfirmApp.setEnabled(true);
                    }
                    if (log.isAdmin()) {
                        btnIL.setEnabled(true);
                    }
                    if (log.isKontor()) {
                        btnSalery.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Forkert kodeord!!!");
                    txtPassword.setText("");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "LOG UD FØRST!!!");
        }
    }

    private boolean isInformationValid() {
        return txtName.getText().length() != 0 && txtPassword.getText().length() != 0;
    }
}
