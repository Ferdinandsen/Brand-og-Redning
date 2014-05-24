package Renderes;

import TableModels.TableModelUnfinishedFremmøde;
import BE.BEAlarm;
import BLL.BLLAppearance;
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
public class RenderUnfinishedAlarmsCell extends DefaultTableCellRenderer {

    ArrayList<BEAlarm> allAlarms;
    BLLAppearance bllAppearance;

    public RenderUnfinishedAlarmsCell() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {
        TableModelUnfinishedFremmøde model = (TableModelUnfinishedFremmøde) table.getModel();
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
