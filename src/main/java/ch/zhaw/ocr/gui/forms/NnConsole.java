package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;

/**
 * Neural Network Console
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class NnConsole {
	
	private JPanel panel;
	private JTextArea consoleText;
	private MainGui gui;
	
        
    public NnConsole(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
	
	public void generateTextArea() {			
			JPanel nPanel = new JPanel(new SpringLayout());
			
			JLabel titleLabel = new JLabel("details of image analysis");
			
			nPanel.add(titleLabel);
			
	        SpringUtilities.makeCompactGrid(nPanel, 1, 1, 10, 10, 10, 20);

	        panel.add(nPanel, BorderLayout.NORTH);       
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
