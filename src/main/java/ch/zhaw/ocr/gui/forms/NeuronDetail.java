package ch.zhaw.ocr.gui.forms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;

public class NeuronDetail {
	
	private JPanel panel;
	private JTextArea neuronText;
	private MainGui gui;
	
        
    public NeuronDetail(MainGui gui) {
        this.gui = gui;
        openWindow();
    }
	
	public NeuronDetail() {
		openWindow();
	}
	
	private void openWindow() {
		loadTextArea();
        
	}
	
	private void loadTextArea() {
		
        panel = new JPanel(new SpringLayout());
        
        neuronText = new JTextArea(20,70);
        JScrollPane scrollAnalysed = new JScrollPane (neuronText);
        
        
        panel.add(scrollAnalysed);
                
        SpringUtilities.makeCompactGrid(panel, 1, 1, 10, 10, 10, 20);
	}	
	
	public JPanel getPanel(){
		return panel;
	}
	
	public JTextArea getNeuronText(){
		return neuronText;
	}

}
