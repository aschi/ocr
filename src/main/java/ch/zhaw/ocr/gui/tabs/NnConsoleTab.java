package ch.zhaw.ocr.gui.tabs;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.helper.SpringUtilities;

/**
 * Neural Network Console offers an overview about the work of the neuronal network
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class NnConsoleTab {
	
	private JPanel panel;
	private JTextArea consoleText;
	private MainGui gui;
	
      
	/**
	 * Constructor
	 * @param gui main gui
	 */
    public NnConsoleTab(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
	
    /**
     * generates the textarea which is showed on the tab 
     */
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
	
	/**
	 * returns the main-nnconsole-tab-panel
	 * @return JPanel
	 */
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
	
	/**
	 * sets the text which is showed in the teextarea
	 * @param sb StringBuffer
	 */
	public void setConsoleText(StringBuffer sb) {
		consoleText.setText(sb.toString());
	}
	
	/**
	 * returns the textarea which is showed in the tab
	 * @return JTextArea
	 */
	public JTextArea getNeuronText(){
		return consoleText;
	}
}
