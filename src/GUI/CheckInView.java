package GUI;

import BE.BEFireman;
import BLL.BLLFireman;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.html.parser.Element;

/**
 *
 * @author Team Kawabunga
 */
public class CheckInView extends javax.swing.JFrame {

    BLLFireman bllFireman;
    ArrayList<BEFireman> allFiremen = new ArrayList<>();
    private JPanel main;
    private int width;
    private int height;

    /**
     * Creates new form CheckUD
     */
    public CheckInView() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bllFireman = BLLFireman.getInstance();
        this.setUndecorated(true);
        this.setResizable(false);
        setResizable(false);
        this.setTitle("CHECK UD SCREEN");
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        main = getBorderLayout();
        this.add(main);
    }

    private JPanel getBorderLayout() {
        JPanel p = new JPanel();
        BorderLayout blayout = new BorderLayout();
        p.setLayout(blayout);
        p.add(getPanelLayout(), BorderLayout.CENTER);
        return p;
    }

    private JPanel getPanelLayout() {
        int amount = 1;
        allFiremen = bllFireman.getAllfiremen();
        FlowLayout flayout = new FlowLayout();
        flayout.setHgap(30); // definer afstanden imellem grupperne
        JPanel p = new JPanel(flayout);
        p.setBackground(new Color(164, 164, 164));
        for (int i = 1; i <= bllFireman.getHighestTeamNumber(); i++) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Hold: " + i);
            Font f = new Font(Font.SANS_SERIF, Font.BOLD, 24);
            label.setFont(f);
            label.setAlignmentY(CENTER_ALIGNMENT);
            JPanel labelpanel = new JPanel(new FlowLayout());
            labelpanel.setAlignmentX(CENTER_ALIGNMENT);
            labelpanel.add(label);
            labelpanel.setOpaque(false);
            GridLayout glayout = new GridLayout(9, 1);
            glayout.setVgap(10);
            panel.setPreferredSize(new Dimension(width / (bllFireman.getHighestTeamNumber() + 1), height));
            panel.setLayout(glayout);
            panel.add(labelpanel);
            for (BEFireman fm : allFiremen) {
                panel.setBackground(getColorTeam(i));
                JButton b = new firemanButton(fm);
                b.setHorizontalAlignment(SwingConstants.LEFT);
                b.setIcon(new ImageIcon(((new ImageIcon("images/" + fm.getMedarbjeder().getPortræt())).getImage()).getScaledInstance(70, 80, java.awt.Image.SCALE_SMOOTH)));
                b.setFocusable(false);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckInView.firemanButton fb = (CheckInView.firemanButton) e.getSource();
                        CheckOutView frame = new CheckOutView(fb.localFireman);
                        frame.setVisible(true);
                    }
                });

                if (fm.getTeam() == amount) {
                    panel.add(b);
                }
//                panel.add(label);
            }
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            p.add(panel);
            amount++;
        }
        return p;
    }

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
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private Color getColorTeam(int i) {
        Color myColor = Color.red;
        switch (i) {
            case 1:
                myColor = new Color(255, 51, 51);
                break;
            case 2:
                myColor = Color.lightGray;
                break;
            case 3:
                myColor = new Color(0, 128, 255);
                break;
            case 4:
                myColor = new Color(248, 215, 0);
                break;
        }
        return myColor;
    }

    private class firemanButton extends JButton {

        String name;
        BEFireman localFireman;

        public firemanButton(BEFireman fm) {
            localFireman = fm;
            this.name = fm.getMedarbjeder().getFornavn() + " " + fm.getMedarbjeder().getEfternavn();
            this.setBackground(getColor());
            this.setText(name);
 
        }

        private Color getColor() {
            Color myColor = new Color(255, 255, 102);
            switch (localFireman.getTeam()) {
                case 1:
                    myColor = new Color(255, 99, 71);
                    break;
                case 2:
                    myColor = new Color(245, 245, 220);
                    break;
                case 3:
                    myColor = new Color(173, 216, 230);
                    break;
            }
            return myColor;
        }

        public void updateLocalFireman(BEFireman lfm) {
            localFireman = lfm;
            this.setBackground(getColor());
        }
    }
}
