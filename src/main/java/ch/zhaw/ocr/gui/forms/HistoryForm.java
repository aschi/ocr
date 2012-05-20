package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ch.zhaw.ocr.gui.MainGui;

public class HistoryForm {
	
	private JTextArea historyText;
	private JPanel panel;
	private MainGui gui;
	
    public HistoryForm(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
    
    public HistoryForm() {
    	createPanel();	
    }
    
    public void createPanel() {
    	
    	panel = new JPanel(new BorderLayout());
        panel.add(loadImage(), BorderLayout.NORTH);
        panel.add(loadTextArea(), BorderLayout.SOUTH);
    }
    
    private JComponent loadImage() {
    	JPanel imgPanel = new JPanel();
    	JLabel imgLabel = new JLabel(new ImageIcon("/home/ildril/images.jpg"));
    	
    	imgPanel.add(imgLabel);
    	
    	return imgPanel;
    	
    }
    
    
    private JComponent loadTextArea() {
    	JPanel areaPanel = new JPanel();
    	historyText = new JTextArea(20,70);
    	JScrollPane scrollHistory = new JScrollPane(historyText);
    	
    	areaPanel.add(scrollHistory);
    	
    	return areaPanel;
    }
    


}
