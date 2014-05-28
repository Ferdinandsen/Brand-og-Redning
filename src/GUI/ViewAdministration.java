package GUI;

import TableModels.TableModelLøn;
import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import BLL.BLLAlarm;
import BLL.BLLAppearance;
import BLL.BLLFireman;
import BLL.BLLLønPdf;
import Renderes.RenderFremmødeTableCell;
import TableModels.Observerble;
import com.itextpdf.text.DocumentException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Team Kawabunga
 */
public class ViewAdministration extends JDialog {

    final long DAY = 86400000; // 1 day in milliseconds
    BELogin localLog;
    BLLAppearance bllAppearance;
    BLLFireman bllFireman;
    BLLAlarm bllAlarm;
    TableModelLøn model;
    DefaultListModel<String> alarmliste;
    LabelPanel panel;
    ListenerLabel l;

    /**
     * Creates new form Administration
     *
     * @param log gets the login from the form that opened this
     */
    public ViewAdministration(BELogin log) {
        localLog = log;
        bllAppearance = BLLAppearance.getInstance();
        bllFireman = BLLFireman.getInstance();
        bllAlarm = BLLAlarm.getInstance();

        initComponents();
        initOtherComponents();
        populateLønTable(null, dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY));
        fillcboxBrandMand();
        populateAlarmList();
        addCellRenderer();

        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Administration - Løn");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

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
        btnEdit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnHent = new javax.swing.JButton();
        pnlcustom = showLabelPanel();
        btnBack = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnEdit.setText("Rediger");

        jLabel1.setText("Fra:");

        jLabel2.setText("Til:");

        jLabel3.setText("Ikke godkendte alarmer:");

        btnHent.setText("Hent");

        javax.swing.GroupLayout pnlcustomLayout = new javax.swing.GroupLayout(pnlcustom);
        pnlcustom.setLayout(pnlcustomLayout);
        pnlcustomLayout.setHorizontalGroup(
            pnlcustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        pnlcustomLayout.setVerticalGroup(
            pnlcustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        btnBack.setText("Tilbage");

        btnOK.setText("Gem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
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
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(601, 601, 601)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pnlcustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE))
                                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlcustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOK)
                            .addComponent(btnBack)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
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
    private javax.swing.JList lstAlarm;
    private javax.swing.JPanel pnlcustom;
    private javax.swing.JTable tblBM;
    // End of variables declaration//GEN-END:variables
/**
     * sets the datechoosers and the button adds the listeners the the different
     * buttons and tables.
     */
    private void initOtherComponents() {
        lstAlarm.setEnabled(false);
        long firstDayOfMonth = new Date().getDate() * DAY - DAY;// the first in the current month.
        dchoFra.setDate(new Date(new Date().getTime() - firstDayOfMonth));
        dchoTil.setDate(new Date()); // actual date "today"
        btnEdit.setEnabled(false);
        tblBM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
                hentInfo();
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
                ArrayList<BEAppearance> app = confirm();
                createPDF(app);
            }
        });
    }

    /**
     * creates the panel that contains the labelpanel witch contains the
     * observer label
     *
     * @return the panel
     */
    public LabelPanel showLabelPanel() {
        panel = new LabelPanel();
        panel.setLayout(null);
        l = new ListenerLabel();
        l.setBounds(0, 0, 200, 30);
        panel.add(l);
        repaint();
        return panel;

    }

    /**
     *
     * @return !#!#!#!#!#!#!#!#!#!#1#!#!#!#!"#!#@throws HeadlessException wtf is
     * this shit "!!!"!"#!#!#!#!#!¤!¤!¤!"¤!"¤
     */
    private ArrayList<BEAppearance> confirm() throws HeadlessException {
        ArrayList<BEAppearance> app = new ArrayList<>();
        TableModelLøn løn = (TableModelLøn) tblBM.getModel();
        for (int i = 0; i < tblBM.getRowCount(); i++) {
            app.add(løn.getRow(i));
        }
        bllAppearance.EndSalary(app);
        JOptionPane.showMessageDialog(this, "Lønnen bekræftet");
        return app;
    }

    /**
     * ???? skal den her ikk kun repainte eller noget
     * ??????????????????????????????????????????????????????????? sets the
     * model for the table with an arraylist
     */
    private void hentInfo() {
        if (cboxBM.getSelectedIndex() == 0) {
            BEFireman localFireman = null;
            model.setAppearanceList(bllAppearance.getAllAppearancesWithDateCriteria(localFireman, dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)));
            tblBM.setModel(model);
        } else {
            model.setAppearanceList(bllAppearance.getAllAppearancesWithDateCriteria((BEFireman) cboxBM.getSelectedItem(), dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)));
            tblBM.setModel(model);
        }
    }

    /**
     * fills the alarmliste, with all unfinishedalarms(do not have a timestamp
     * from IL)
     */
    private void populateAlarmList() {
        alarmliste = new DefaultListModel<>();
        lstAlarm.setModel(alarmliste);
        for (BEAlarm a : bllAlarm.getAllUnfinishedAlarms()) {
            alarmliste.addElement(a.toString());
        }
    }

    /**
     * populates the table with
     *
     * @param localFireman the firemans apperances
     * @param from
     * @param to
     */
    private void populateLønTable(BEFireman localFireman, Date from, Date to) {
        model = new TableModelLøn(bllAppearance.getAllAppearancesWithDateCriteria(localFireman, from, to));
        tblBM.setModel(model);
        setTotalTime();
    }

    /**
     * fills the cboxBrandmand with all firemen.
     */
    private void fillcboxBrandMand() {
        cboxBM.addItem("Alle Brandmænd");
        for (BEFireman bm : bllFireman.getAllfiremen()) {
            cboxBM.addItem(bm);
        }
    }

    /**
     * opens the selected firemans appearance, so the user can change the time
     * stamps. either when no firemen is selected or when there is.
     */
    private void updateSalary() {
        if (cboxBM.getSelectedIndex() == 0) {
            BEFireman localFireman = null;
            ViewChangeSalary frame = new ViewChangeSalary(bllAppearance.getAllAppearancesWithDateCriteria(localFireman, dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)).get(tblBM.convertRowIndexToView(tblBM.getSelectedRow())));
            frame.setVisible(true);
            model.setAppearanceList(bllAppearance.getAllAppearancesWithDateCriteria(null, dchoFra.getDate(), dchoTil.getDate()));
        } else {
            try {
                ViewChangeSalary frame = new ViewChangeSalary(bllAppearance.getAllAppearancesWithDateCriteria((BEFireman) cboxBM.getSelectedItem(), dchoFra.getDate(), new Date(dchoTil.getDate().getTime() + DAY)).get(tblBM.convertRowIndexToView(tblBM.getSelectedRow())));
                frame.setVisible(true);
                model.setAppearanceList(bllAppearance.getAllAppearancesWithDateCriteria((BEFireman) cboxBM.getSelectedItem(), dchoFra.getDate(), dchoTil.getDate()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Du skal huske at hente først");
            }
        }
        model.setAppearanceList(bllAppearance.getAllAppearancesWithDateCriteria(null, dchoFra.getDate(), dchoTil.getDate()));
//        tblBM.setModel(model);
//        tblBM.repaint();
    }

    /**
     * sets the text on the label listener.
     */
    private void setTotalTime() {
        int amount = 0;
        for (int i = 0; i < tblBM.getRowCount(); i++) {
            amount += (int) tblBM.getModel().getValueAt(i, 7);// 7 is the column where time is.
        }
        l.setText("Total Tid: " + amount);
        l.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    }

    private void addCellRenderer() {
        RenderFremmødeTableCell renderer = new RenderFremmødeTableCell();
        for (int col = 0; col < model.getColumnCount(); col++) {
            renderer.setHorizontalAlignment(JLabel.CENTER);
            TableColumn tc = tblBM.getColumnModel().getColumn(col);
            tc.setCellRenderer(renderer);
        }
    }

    /**
     * creates the pdf for løn!
     *
     * @param app all the appearances to make løn from!
     */
    private void createPDF(ArrayList<BEAppearance> app) {
        try {
            new BLLLønPdf(app, localLog, String.valueOf(dchoFra.getDate().getDate() + "-" + (dchoFra.getDate().getMonth() + 1) + "-" + (dchoFra.getDate().getYear() + 1900)), String.valueOf(dchoTil.getDate().getDate() + "-" + dchoTil.getDate().getMonth() + "-" + (dchoTil.getDate().getYear() + 1900)));
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Fejl ved at lave PDF.");
        }
        JOptionPane.showMessageDialog(this, "PDF'en er nu lavet på skrivebordet!");
    }

    /**
     * a panel to put in, too add our listener label to
     */
    public class LabelPanel extends JPanel {

        public LabelPanel() {
            setPreferredSize(new Dimension(100, 40));
        }
    }

    /**
     * a label with an observer, so it changes every time you invoke the
     * setAppearanceList method on the model
     */
    public class ListenerLabel extends JLabel implements IObserver {

        public ListenerLabel() {
            setPreferredSize(new Dimension(50, 30));
            Observerble.observer.add(this);
        }

        @Override
        public void notifyObserver() {
            setTotalTime();
        }
    }
}
