package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class NeuronList {
	
	private JFrame frame;
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
		
        frame = new JFrame("");
        frame.setLocation(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel contentPane = new JPanel(new BorderLayout());
        
        frame.getContentPane().add(contentPane);
       
        contentPane.add(loadLists(), BorderLayout.WEST);
        
        frame.setVisible(true);
        frame.pack();
	}
	
	private JComponent loadLists() {
		
		JPanel areaPanel = new JPanel(new SpringLayout());
		
		JLabel listNeuron = new JLabel("List of existing Neurons:");
		
		areaPanel.add(listNeuron);
		
        SpringUtilities.makeCompactGrid(areaPanel, 1, 1, 10, 10, 10, 20);
    	
        return areaPanel;
		
	}

}
