package ch.zhaw.ocr.gui.forms;

import javax.swing.table.DefaultTableModel;

/**
 * Klasse wird verwendet um ReadOnly JTables darzustellen. Wird für alle Listenansichten verwendet.
 * @author adrian
 */
public class ReadOnlyTableModel extends DefaultTableModel {
	public ReadOnlyTableModel(){
		super();		
	}
	

	public ReadOnlyTableModel(String[] columnNames, int rowCount){
		super(columnNames, rowCount);
	}
	
	/**
	 * Gibt unabhängig von den Paramtern false (= nicht editierbar) zurück
	 * @param row
	 * @param column
	 */
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
