package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainGui {
	
	private JFrame frame;
	private JPanel mainPanel;
	
    public MainGui(){
        
        createFrame();
      
    }
    
	

	private void createFrame() {
		
		frame = new JFrame("OCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen	
        
        mainPanel = new JPanel(new BorderLayout());

        //frame.getContentPane().add(new JScrollPane(new Navigation(this).getTree()), BorderLayout.WEST);
       

        createMenuBar();
        
        frame.setVisible(true);

		
		
	}
	
	private void loadKNN() {
		
		JPanel areaPanel = new JPanel(new BorderLayout());
		
		NeuronList nl = new NeuronList(this);
		NeuronDetail nd = new NeuronDetail(this);
		
		areaPanel.add(nl);
		areaPanel.add(nd);
		
        SpringUtilities.makeCompactGrid(areaPanel, 2, 1, 10, 10, 10, 20);
    	
		
	}
	
    private void createMenuBar(){
    	
        JMenuBar menuBar = new JMenuBar();
        
        frame.setJMenuBar(menuBar);
        
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");
        
        JMenuItem knn = new JMenuItem("Switch to KNN");
        JMenuItem text = new JMenuItem("Switch to Text");
        
        menuBar.add(file);
        menuBar.add(view);
        
        view.add(knn);
        view.add(text);
        
    }

}
