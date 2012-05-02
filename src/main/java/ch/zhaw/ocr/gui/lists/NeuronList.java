package ch.zhaw.ocr.gui.lists;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.forms.SpringUtilities;

public class NeuronList{
	private JPanel panel;
	private MainGui gui;
	
    public static void main(String [ ] args) {
    	
    	new NeuronList();

    }
    
    public NeuronList(MainGui gui) {
        this.gui = gui;
        openWindow();
    }
	
	public NeuronList() {
		openWindow();
	}
	
	private void openWindow() {
		loadLists();
	}
	
	private void loadLists() {
		
		panel = new JPanel(new SpringLayout());
		
		JLabel listNeuron = new JLabel("List of existing Neurons:");
		
		panel.add(listNeuron);
		
        SpringUtilities.makeCompactGrid(panel, 1, 1, 10, 10, 10, 20);
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
