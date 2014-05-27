package Renderes;

import BE.BEAppearance;
import TableModels.TableModelILFremmøde;
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
public class RenderILFremmødeTableCell extends DefaultTableCellRenderer {

    ArrayList<BEAppearance> allAppearances;

    public RenderILFremmødeTableCell() {
    }

    public RenderILFremmødeTableCell(ArrayList<BEAppearance> allHlGodkendtAppearances) {
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

        TableModelILFremmøde model = (TableModelILFremmøde) table.getModel();
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        BEAppearance a = model.getAppearanceByRow(row);
        cell.setBackground(a.getAlarm().getColor());

        if (isSelected) {
            cell.setBackground(Color.GRAY);
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
