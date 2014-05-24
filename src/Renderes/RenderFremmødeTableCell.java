package Renderes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Team Kawabunga
 */
public class RenderFremmødeTableCell extends DefaultTableCellRenderer {

    public RenderFremmødeTableCell() {
        
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

        if (table.convertColumnIndexToModel(column) == 5)
        {
            cell.setFont(total);
            cell.setForeground(Color.WHITE);
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
        return cell;
    }
}
