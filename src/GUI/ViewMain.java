package GUI;

import Renderes.RenderActionList;
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
public class ViewMain extends javax.swing.JFrame {

    BLLAlarm bllAlarm;
    BLLEmployee bllEmployee;
    BELogin log;
    DefaultListModel model = new DefaultListModel();

    /**
     * Creates new form MainView
     */
    public ViewMain() {
        bllAlarm = BLLAlarm.getInstance();
        bllEmployee = BLLEmployee.getInstance();

        this.setResizable(false);
        this.setTitle("Main View");

        initComponents();
        initLogInComponents();
        initOtherComponets();

        RenderActionList renderer = new RenderActionList();
        lstAction.setCellRenderer(renderer);
        fillListActions();
        lstAction.setModel(model);
        txtName.requestFocus();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
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

        btnErrorReport.setText("Fejl Rapport");

        btnConfirmApp.setText("Holdleder");

        btnIL.setText("Indsatsleder");

        btnSalery.setText("Administration");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNote)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                .addComponent(txtPassword))
                                            .addGap(30, 30, 30)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnConfirmApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnIL, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSalery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnErrorReport, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))))
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogIn)
                            .addComponent(btnLogOut)))
                    .addComponent(btnIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
 /**
  * populates our list with all the odin alarms
  */
    private void fillListActions() {
        for (BEOdinAlarm odinAlarm : bllAlarm.getAllOdinAlarms()) {
            model.addElement(odinAlarm);
        }
    }
    
/**
 * Sets the settings for our components, and initialize our listeners
 */
    private void initOtherComponets() {
        txtName.setNextFocusableComponent(txtPassword);
        txtPassword.setNextFocusableComponent(txtName);
        btnErrorReport.setEnabled(true);
        txtNote.setEditable(false);
        lblLogIn.setText("Ikke logget på");
        btnConfirmApp.setEnabled(false);
        btnIL.setEnabled(false);
        btnSalery.setEnabled(false);
        btnConfirmApp.setText("Holdleder");

        btnSalery.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewAdministrationChoose frame = new ViewAdministrationChoose(log);
                frame.setVisible(true);
            }
        });

        btnConfirmApp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHLAfterAction frame = new ViewHLAfterAction(log);
                frame.setVisible(true);
            }
        });

        btnErrorReport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHLErrorReport frame = new ViewHLErrorReport();
                frame.setVisible(true);
            }
        });

        btnIL.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewILIndsats frame = new ViewILIndsats(log);
                frame.setVisible(true);
            }
        });
    }
    /**
     * login component settings, and listeners
     */

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
                txtName.setEnabled(true);
                txtPassword.setEnabled(true);
                txtName.requestFocus();
            }
        });
    }
    
    /**
     * clears the login components
     */
    private void clearlogintxt() {
        txtName.setText(null);
        txtPassword.setText(null);
    }

    /**
     * if the information entered is valid, it will check if the user exists. if it does, it will enable the components according to the users rank
     */
    private void tryLogin() {
        if (btnLogIn.isEnabled()) {
            if (isInformationValid()) {
                if (bllEmployee.doesUserExist(txtName.getText(), txtPassword.getPassword())) {
                    log = bllEmployee.getLogin(txtName.getText());
                    lblLogIn.setText("Velkommen " + log.getMedarbejder().toString());
                    btnLogIn.setEnabled(false);
                    btnLogOut.setEnabled(true);
                    txtName.setEnabled(false);
                    txtPassword.setEnabled(false);
                    clearlogintxt();
                    if (log.isHoldleder()) {
                        btnConfirmApp.setEnabled(true);
                        btnErrorReport.setEnabled(true);
                    }
                    if (log.isAdmin()) {
                        btnConfirmApp.setEnabled(true);
                        btnIL.setEnabled(true);
                        btnSalery.setEnabled(true);
                        btnErrorReport.setEnabled(true);
                    }
                    if (log.isKontor()) {
                        btnSalery.setEnabled(true);
                        btnErrorReport.setEnabled(true);
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

    /**
     * 
     * @return true if the text length is 0 
     */
    private boolean isInformationValid() {
        return txtName.getText().length() != 0 && txtPassword.getText().length() != 0;
    }
}
