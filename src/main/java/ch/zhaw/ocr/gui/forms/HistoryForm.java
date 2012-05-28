package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ch.zhaw.ocr.gui.InputNavigation;
import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.helper.TextFileHandler;

public class HistoryForm {
	
	private JTextArea historyText;
	private JPanel panel;
	private MainGui gui;
	private ImageIcon img;
	
    public HistoryForm(MainGui gui) {
        this.gui = gui;
        createPanel();
    }
    
    public HistoryForm() {
    	createPanel();	
    }
    
    public void createPanel() {
    	
    	panel = new JPanel(new BorderLayout());
		InputNavigation inavi = new InputNavigation(gui);
		panel.add(inavi.getPanel(), BorderLayout.WEST);
        panel.add(loadImage(img), BorderLayout.NORTH);
        panel.add(loadTextArea(), BorderLayout.SOUTH);
    }
    
    private JComponent loadImage(ImageIcon img) {
    	
    	img = new ImageIcon();
    	JPanel imgPanel = new JPanel();
    	JLabel imgLabel = new JLabel(img);
    	
    	imgPanel.add(imgLabel);
    	
    	return imgPanel;
    	
    }
    
    public void setImg(ImageIcon img) {
    	this.img = img;
    }
    
    public void setText(String datei) throws IOException {
    	
    	historyText.setText(TextFileHandler.readTextFile(datei));
        
    } 
    
    private JComponent loadTextArea() {
    	JPanel areaPanel = new JPanel();
    	historyText = new JTextArea(20,70);
    	JScrollPane scrollHistory = new JScrollPane(historyText);
    	
    	areaPanel.add(scrollHistory);
    	
    	return areaPanel;
    }
    

    public JPanel getPanel() {
    	return panel;
    }

}
