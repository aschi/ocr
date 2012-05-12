package ch.zhaw.ocr.gui.lists;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.forms.SpringUtilities;

public class NeuronList{
	private JPanel panel;
	private MainGui gui;
    
    public NeuronList(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
	
	public NeuronList() {
		createPanel();
	}
	
	private void createPanel() {
		panel = new JPanel(new BorderLayout());
        panel.add(loadLists(), BorderLayout.CENTER);
		
	}
	
	private JComponent loadLists() {
		
		JPanel nPanel = new JPanel(new SpringLayout());
		
		JLabel listNeuron = new JLabel("List of existing Neurons:");
		
		nPanel.add(listNeuron);
		
        SpringUtilities.makeCompactGrid(nPanel, 1, 1, 10, 10, 10, 20);
        
        return nPanel;
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
