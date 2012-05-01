package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class NeuronDetail {
	
	private JFrame frame;
	private JTextArea neuronText;
	private MainGui gui;
	
    public static void main(String [ ] args) {
    	
    	new NeuronDetail();

    }
    
    public NeuronDetail(MainGui gui) {
        this.gui = gui;
        openWindow();
    }
	
	public NeuronDetail() {
		openWindow();
	}
	
	private void openWindow() {
		
        frame = new JFrame("");
        frame.setLocation(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel contentPane = new JPanel(new BorderLayout());
        
        frame.getContentPane().add(contentPane);
        
        contentPane.add(loadTextArea(), BorderLayout.CENTER);
        
        frame.setVisible(true);
        frame.pack();
        
	}
	
	private JComponent loadTextArea() {
		
        JPanel areaPanel = new JPanel(new SpringLayout());
        
        neuronText = new JTextArea(20,70);
        JScrollPane scrollAnalysed = new JScrollPane (neuronText);
        
        
        areaPanel.add(scrollAnalysed);
                
        SpringUtilities.makeCompactGrid(areaPanel, 1, 1, 10, 10, 10, 20);
    	
        return areaPanel;
	}	

}
