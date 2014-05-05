package GUI;

import BE.BEAppearance;
import BE.BEMateriel;
import BE.BEUsage;
import BLL.BLLUsage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Shadowleet
 */
public class HLUsageReport extends javax.swing.JFrame {

    BLLUsage bllusage;
    BEMateriel m;
    ArrayList<BEMateriel> allMats = new ArrayList<>();
    private JPanel main;
    private int height;
    private int width = 450;

    /**
     * Creates new form HLErrorRapport
     */
    public HLUsageReport(BEAppearance a) {
        bllusage = BLLUsage.getInstance();
        allMats = bllusage.getAllMats();
        height = allMats.size() * 21;
        this.setSize(width, height);
        this.setTitle("Holdleder Forbrugs Rapport");
        this.setLocationRelativeTo(null);
        main = getBorderLayout();
        this.add(main);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel getBorderLayout() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(getGridLayout(), BorderLayout.CENTER);
        p.add(getFlowLayoutSouth(),BorderLayout.SOUTH);
        return p;
    }

    private JPanel getGridLayout() {
        JPanel p = new JPanel();
        GridLayout gl = new GridLayout();
        p.setLayout(gl);
        p.add(getGroupLayout());
        return p;
    }
    private JPanel getFlowLayoutSouth(){
        JPanel p = new JPanel();
        FlowLayout fl = new FlowLayout();
        p.setLayout(fl);
        JButton b = new JButton(("Bekræft"));
        p.add(b);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return p;
    }

    private JPanel getGroupLayout() {
        allMats = bllusage.getAllMats();
        int modsize = allMats.size() % 2;
        JPanel p = new JPanel();
        for (BEMateriel mat : allMats) {
            JPanel panel = new ForbrugPanel(mat);
//            JLabel label = new JLabel(mat.getName());
//            JTextField tf = new JTextField();
//            label.setPreferredSize(new Dimension(140, 20));
//            tf.setPreferredSize(new Dimension(50, 20));

            GroupLayout layout = new GroupLayout(panel);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            /**
             * MANGLER DET RIGTIGE COMPONENT! FRA FORBRUG? at "her"
             */
            layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(label));
            layout.setVerticalGroup(layout.createSequentialGroup().addComponent(tf));
            p.add(panel);
        }
        if (modsize != 0) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(205, 20));
            p.add(panel);
        }
        return p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void createReport(BEUsage u){
    bllusage.createReport(u);
}
    
    private class ForbrugPanel extends javax.swing.JPanel {

        String name;
        int amount;
      public  JLabel lbl;
        JTextField tf;

        /**
         * Creates new form ForbrugPanel
         */
        public ForbrugPanel(BEMateriel m) {

            lbl = new JLabel();
            tf = new JTextField();
            
            name = m.getName();
            lbl.setText(name);
           
            lbl.setPreferredSize(new Dimension(140, 20));
            tf.setPreferredSize(new Dimension(50, 20));
        }
        public JTextField getTF(){
            return tf;
        }
        public JLabel getLBL(){
            return lbl;
        }
    }
}