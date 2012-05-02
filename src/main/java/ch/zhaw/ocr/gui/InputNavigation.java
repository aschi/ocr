package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.forms.SpringUtilities;


public class InputNavigation {
	private JPanel panel;
	private MainGui gui;
	
    public InputNavigation(MainGui gui) {
        this.gui = gui;
        createPanel();
    }

	
	public InputNavigation() {
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
		
		JPanel areaPanel = new JPanel(new SpringLayout());
		
		JLabel listImg = new JLabel("List of used Images:");
		JLabel listTxt = new JLabel("List of used Texts:");
		
		areaPanel.add(listImg);
		areaPanel.add(listTxt);
		
        SpringUtilities.makeCompactGrid(areaPanel, 2, 1, 10, 10, 10, 20);
    	
        return areaPanel;
		
	}

}
