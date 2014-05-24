package TableModels;

import BE.BEAppearance;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class TableModelLøn extends AbstractTableModel {
final int HLLønkode = 447;
final int BMLønkode = 446;

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAppearance> appearances;
    private String[] colNames = {
        "Fornavn",
        "Efternavn",
        "Dato",
        "Indsats",
        "evaNo",
        "Funktion",
        "Lønkode",
        "Total Timer"
    };
    
    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        String.class,//Fornavn
        String.class, //Efternavn
        Timestamp.class, //Dato
        String.class, // Indsats
        Integer.class,//EvaNo
        String.class, //Funktion
        Integer.class, //Lønkode
        Integer.class, //Total timer
    };

    /**
     *
     * @param allAppearances
     */
    public TableModelLøn(ArrayList<BEAppearance> allAppearances) {
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
    public Object getValueAt(int row, int col) {
        BEAppearance a = appearances.get(row);
        switch (col) {
            case 0:
                return a.getFireman().getMedarbjeder().getFornavn();
            case 1:
                return a.getFireman().getMedarbjeder().getEfternavn();
            case 2:
                return a.getAlarm().getTime();
            case 3:
                return a.getAlarm().getDesc();
            case 4:
                return a.getAlarm().getEvaNo();
            case 5:
                return a.isHoldleder() ? "Holdleder" : "Brandmand";
            case 6:
               return a.isHoldleder() ? HLLønkode : BMLønkode;
            case 7:
                return a.getTotalTid();
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    public BEAppearance getRow(int row){
        return appearances.get(row);
    }

    public void setAppearanceList(ArrayList<BEAppearance> appearance) {
        this.appearances = appearance;
    }

    public BEAppearance getAppearanceByRow(int row) {
        return appearances.get(row);
    }
}
