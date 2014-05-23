package Renderes;

import BE.BEAlarm;
import BLL.BLLAppearance;
import GUI.UnfinishedFremmødeModel;
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
public class UnfinishedAlarmsCellRenderer extends DefaultTableCellRenderer {

    ArrayList<BEAlarm> allAlarms;
    BLLAppearance bllAppearance;

    public UnfinishedAlarmsCellRenderer() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {
        UnfinishedFremmødeModel model = (UnfinishedFremmødeModel) table.getModel();
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);

        cell.setBackground(Color.WHITE);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        BEAlarm a = model.getAlarmByRow(row);
        cell.setBackground(a.getColor());
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
