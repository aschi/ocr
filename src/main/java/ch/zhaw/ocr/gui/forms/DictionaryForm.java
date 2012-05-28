package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.gui.MainGui;

public class DictionaryForm {


	private JPanel panel;
	private MainGui gui;	
	private Dictionary dic;
	
    public DictionaryForm(MainGui gui) {
        this.gui = gui;
        createPanel();
        this.dic = gui.getOcr().getDictionary();
    }
    
    public void createPanel() {
    	
    	panel = new JPanel(new BorderLayout());
    	
    }
    

    public JPanel getPanel() {
    	return panel;
    }

}
