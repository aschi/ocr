package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JComponent;
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
        createPanel();
    }
	
	public NeuronDetail() {
		createPanel();
	}
	
	
	private void createPanel() {
		panel = new JPanel(new BorderLayout());
        panel.add(loadTextArea(), BorderLayout.CENTER);
		
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	private JComponent loadTextArea() {
		
        JPanel nPanel = new JPanel(new SpringLayout());
        
        neuronText = new JTextArea(20,70);
        JScrollPane scrollAnalysed = new JScrollPane (neuronText);
        
        nPanel.add(scrollAnalysed);
                
        SpringUtilities.makeCompactGrid(nPanel, 1, 1, 10, 10, 10, 20);
        
        return nPanel;
	}	
	
	public JTextArea getNeuronText(){
		return neuronText;
	}

}
