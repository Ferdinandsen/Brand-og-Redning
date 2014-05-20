package GUI;

import BE.BEAlarm;
import BE.BEAppearance;
import BLL.BLLAppearance;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Team Kawabunga
 */
public class UnfinishedAlarmsCellRenderer extends DefaultTableCellRenderer {

    ArrayList<BEAlarm> allAlarms;
    BLLAppearance bllAppearance;

    public UnfinishedAlarmsCellRenderer(ArrayList<BEAlarm> allUnfinishedAlarms) {

        allAlarms = allUnfinishedAlarms;
                System.out.println(allAlarms.size());
        bllAppearance = BLLAppearance.getInstance();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);

        cell.setBackground(Color.WHITE);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);

        boolean hasFoundOne = false;
        for (BEAlarm alarm : allAlarms) {
            System.out.println("WWWWWWWWWWWWWWWWWWW ALARM: " + alarm);
            hasFoundOne = false;
            for (BEAppearance appearance : bllAppearance.getAllHlGodkendtAppearances(alarm)) {
                if (bllAppearance.getAllHlGodkendtAppearances(alarm).get(row).getLogin().getMedarbejder().getFornavn().equalsIgnoreCase("gæst")) {
                    cell.setBackground(Color.ORANGE);
                    System.out.println("fandt en orange");
                    hasFoundOne = true;
                } else {
                    System.out.println("grøn");
                    cell.setBackground(Color.GREEN);
                }
                 System.out.println("Alarmen er: " + alarm.getDesc() + " og, appearance er: " + appearance.getFireman().getMedarbjeder().getFornavn() + " " + appearance.getFireman().getMedarbjeder().getEfternavn());
            }
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
