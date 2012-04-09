package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class InputForm {
	
	final JFileChooser fc = new JFileChooser();
	
    private JFrame frame;
    private JPanel panel;
    private JPanel areaPanel;
    private JPanel buttonPanel;
    private JTextArea analysedText;
    private JButton saveButton;
    private JButton browseButton;
    private JButton analyseButton;
    
    public static void main(String [ ] args) {
    	
    	new InputForm();

    }
    
    public InputForm() {
    	
    	openWindow();
    	
    }
    
    private void openWindow() {
    	
        frame = new JFrame("Dumdidum");
        frame.setLocation(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel contentPane = new JPanel(new BorderLayout());
        
        panel = new JPanel(new SpringLayout());
        areaPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new SpringLayout());
        
        JLabel browseImg = new JLabel("Browse Image:");
        
        analysedText = new JTextArea(20,70);
        
        saveButton = new JButton("Save as text");
        browseButton = new JButton("Browse");
        analyseButton = new JButton("Analyse");
        
        browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == browseButton) {
					
					fc.addChoosableFileFilter(new ImageFilter());
					fc.setAcceptAllFileFilterUsed(false);				
					
					int returnVal = fc.showOpenDialog(browseButton);
					
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            java.io.File file = fc.getSelectedFile();
			        } 
				}
				
			}
        });
        
        panel.add(new JLabel());
        
        areaPanel.add(analysedText);
        
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(saveButton);
        buttonPanel.add(browseImg);
        buttonPanel.add(new JLabel());
        buttonPanel.add(browseButton);
        buttonPanel.add(analyseButton);
        
        frame.getContentPane().add(contentPane);
        
        SpringUtilities.makeCompactGrid(panel, 1, 1, 5, 5, 5, 5);
        SpringUtilities.makeCompactGrid(buttonPanel, 2, 4, 5, 5, 5, 5);

        
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(areaPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.pack();

    }

}
