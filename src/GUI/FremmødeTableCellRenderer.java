package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FremmødeTableCellRenderer extends DefaultTableCellRenderer {

    ImageIcon warning;

    public FremmødeTableCellRenderer() {
//         warning = new ImageIcon(new ImageIcon(getClass().getResource("/Images/warning.jpg")).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
                  
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);

        cell.setBackground(row % 2 == 0 ? Color.WHITE : Color.lightGray);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        Font total = new Font(Font.SANS_SERIF, Font.BOLD, 15);

        if (table.convertColumnIndexToModel(column) == 5) // Name String
        {
            cell.setFont(total);
//            cell.setBackground(Color.YELLOW);
            cell.setForeground(Color.WHITE);
               
//            JLabel res = new JLabel(obj.toString());
//            res.setIcon(warning);
//            return res;
        }

        if (isSelected) {
            cell.setBackground(Color.DARK_GRAY);
            if (table.convertColumnIndexToModel(column) != 5) {
                cell.setForeground(Color.WHITE);
            }
            cell.setFont(f);

        } else {
            cell.setForeground(Color.BLACK);
        }



        if (table.convertColumnIndexToModel(column) == 2) // Salery Double
        {
        }
        return cell;
    }
}
