package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.gui.forms.InputForm;
import ch.zhaw.ocr.gui.forms.NeuronDetail;
import ch.zhaw.ocr.gui.lists.NeuronList;

public class MainGui {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel inputPanel;
	private CharacterComperator cc;
	
    public MainGui(CharacterComperator cc){
        this.cc = cc;
        createFrame();
    }
    
	

	private void createFrame() {
		
		frame = new JFrame("OCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen	
        
        mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new BorderLayout());
        //frame.getContentPane().add(new JScrollPane(new Navigation(this).getTree()), BorderLayout.WEST);
       
        frame.setContentPane(inputPanel);
        

        //loadKNN();
        createInputPanel();
        createMenuBar();
        
        frame.setVisible(true);

		
		
	}
	
	public CharacterComperator getCC(){
		return cc;
	}
	
	private void createInputPanel(){
		InputForm iform = new InputForm(this);
		InputNavigation inavi = new InputNavigation(this);
		inputPanel.add(inavi.getPanel(), BorderLayout.WEST);
		inputPanel.add(iform.getPanel(), BorderLayout.CENTER);
	       
	}
	
	private void loadKNN() {
		
		//JPanel areaPanel = new JPanel(new BorderLayout());
		
		NeuronList nl = new NeuronList(this);
		NeuronDetail nd = new NeuronDetail(this);
		
		
		mainPanel.add(nl.getPanel(), BorderLayout.WEST);
		mainPanel.add(nd.getPanel(), BorderLayout.CENTER);
		
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
