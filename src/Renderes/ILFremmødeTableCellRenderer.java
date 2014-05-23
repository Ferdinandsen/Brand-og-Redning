package Renderes;

import BE.BEAppearance;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Team Kawabunga
 */
public class ILFremmødeTableCellRenderer extends DefaultTableCellRenderer {

    ArrayList<BEAppearance> allAppearances;

    public ILFremmødeTableCellRenderer() {
    }

    public ILFremmødeTableCellRenderer(ArrayList<BEAppearance> allHlGodkendtAppearances) {
        allAppearances = allHlGodkendtAppearances;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);

//        cell.setBackground(row % 2 == 0 ? Color.WHITE : Color.lightGray);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        Font total = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        
        if (!allAppearances.get(row).getLogin().getMedarbejder().getFornavn().equalsIgnoreCase("gæst")){
             cell.setBackground(Color.ORANGE);
        }
        else{
            cell.setBackground(Color.GREEN);
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
