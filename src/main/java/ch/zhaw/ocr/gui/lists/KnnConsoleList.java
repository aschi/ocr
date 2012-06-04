package ch.zhaw.ocr.gui.lists;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.forms.SpringUtilities;

public class KnnConsoleList{
	private JPanel panel;
	private MainGui gui;
    
    public KnnConsoleList(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
	
	public KnnConsoleList() {
		createPanel();
	}
	
	private void createPanel() {
		panel = new JPanel(new BorderLayout());
        panel.add(loadLists(), BorderLayout.CENTER);
		
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	private JComponent loadLists() {
		
		JPanel nPanel = new JPanel(new SpringLayout());
		
		JLabel titleLabel = new JLabel("Knn details of image-analyse");
		
		nPanel.add(titleLabel);
		
        SpringUtilities.makeCompactGrid(nPanel, 1, 1, 10, 10, 10, 20);
        
        return nPanel;
	}

}
