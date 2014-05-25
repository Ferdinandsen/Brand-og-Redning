package Renderes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Team Kawabunga
 */
public class RenderActionList extends JLabel implements ListCellRenderer {

    DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public RenderActionList() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        defaultRenderer.setPreferredSize(new Dimension(100, 50));
        defaultRenderer.setHorizontalAlignment(CENTER);
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (index != -1) {
            //Component cell = getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            lbl.setText(value.toString());

            if (index % 2 != 0) {
                lbl.setBackground(new Color(229, 220, 220));
            }
        }
        return lbl;
    }
}
