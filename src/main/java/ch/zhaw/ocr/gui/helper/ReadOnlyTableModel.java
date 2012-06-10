package ch.zhaw.ocr.gui.helper;

import javax.swing.table.DefaultTableModel;

/**
 * Class used to display read only tables.
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
@SuppressWarnings("serial")
public class ReadOnlyTableModel extends DefaultTableModel {
	public ReadOnlyTableModel(){
		super();		
	}
	

	public ReadOnlyTableModel(String[] columnNames, int rowCount){
		super(columnNames, rowCount);
	}
	
	/**
	 * Returns false for any input (= not editable)
	 * @param row
	 * @param column
	 */
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
