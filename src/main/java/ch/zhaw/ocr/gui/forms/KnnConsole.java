package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;

public class KnnConsole {
	
	private JPanel panel;
	private JTextArea consoleText;
	private MainGui gui;
	
        
    public KnnConsole(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
	
	public KnnConsole() {
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
        
        consoleText = new JTextArea(20,70);
        JScrollPane scrollAnalysed = new JScrollPane (consoleText);
        consoleText.setText(gui.getConsoleText().toString());
        
        nPanel.add(scrollAnalysed);
                
        SpringUtilities.makeCompactGrid(nPanel, 1, 1, 10, 10, 10, 20);
        
        return nPanel;
	}	
	
	public void setConsoleText(StringBuffer sb) {
		consoleText.setText(sb.toString());
	}
	
	public JTextArea getNeuronText(){
		return consoleText;
	}

}
