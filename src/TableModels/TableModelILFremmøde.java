package TableModels;

import BE.BEAppearance;
import BE.IBESubject;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class TableModelILFremmøde extends AbstractTableModel implements ITableObserver {

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAppearance> appearances;
    private String[] colNames = {
        "Køretøj",
        "Fornavn",
        "Efternavn",
        "Check ud",
        "Timer",
        "Holdleder",
        "Chauffør",
        "ST Vagt"
    };

    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        String.class, //Køretøj
        String.class,//Fornavn
        String.class, //Efternavn
        Integer.class, // Check ud tidspunktet
        String.class, //Antal timer
        String.class, //Holdleder
        String.class, //chauffør
        String.class //ST vagt
    };

    /**
     *
     * @param allAppearances
     * @param sub
     */
    public TableModelILFremmøde(ArrayList<BEAppearance> allAppearances) {
        appearances = allAppearances;

        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return appearances.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col
    ) {
        BEAppearance a = appearances.get(row);
        switch (col) {
            case 0:
                if (a.getVeh() == null) {
                    return "-";
                }
                return a.getVeh().getOdinnummer();
            case 1:
                return a.getFireman().getMedarbjeder().getFornavn();
            case 2:
                return a.getFireman().getMedarbjeder().getEfternavn();
            case 3:
                String[] checkOutTime = a.getCheckOut().toString().split(" ");
                return checkOutTime[1];
            case 4:
                return a.getTotalTid();
            case 5:
                if (a.isHoldleder()) {
                    return "X";
                }
                return "";
            case 6:
                if (a.isChauffør()) {
                    return "X";
                }
                return "";

            case 7:
                if (a.isSTvagt()) {
                    return "X";
                }
                return "";
        }
        return null;
    }

    @Override
    public String getColumnName(int col
    ) {
        return colNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col
    ) {
        return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col
    ) {
        return false;
    }

    public void setAppearanceList(ArrayList<BEAppearance> appearance) {
        this.appearances = appearance;
    }

    public BEAppearance getAppearanceByRow(int row) {
        return appearances.get(row);
    }

    @Override
    public void update() {
        fireTableDataChanged();
    }

}
