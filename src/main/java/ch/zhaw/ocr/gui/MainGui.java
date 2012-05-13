package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.forms.InputForm;
import ch.zhaw.ocr.gui.forms.NeuronDetail;
import ch.zhaw.ocr.gui.lists.NeuronList;

public class MainGui {
	
	private JFrame frame;
	//private JPanel mainPanel;
	private JPanel inputPanel;
	private JPanel neuronPanel;
	private JPanel cards;
	private Ocr ocr;
	
    public MainGui(Ocr ocr){
        this.ocr = ocr;
        createFrame();
    }

	private void createFrame() {
		
		frame = new JFrame("OCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen
        
        //mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new BorderLayout());
        //frame.getContentPane().add(new JScrollPane(new Navigation(this).getTree()), BorderLayout.WEST);
       
        //frame.setContentPane(inputPanel);

        //loadKNN();
        //createInputPanel();
        //createNeuronPanel();
        
        cards = new JPanel(new CardLayout());
        cards.add(createInputPanel());
        //cards.add(createNeuronPanel());
        
        frame.setContentPane(cards);
        //frame.getContentPane().add(cards);
        //frame.getContentPane().add(new JScrollPane(cards), BorderLayout.CENTER);
        createMenuBar();
        
        frame.setVisible(true);

		
	}
	
    public void selectOverview(String panelSelection){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, panelSelection);
    }

	
	public Ocr getOcr(){
		return ocr;
	}
	
	private JComponent createInputPanel(){
		InputForm iform = new InputForm(this);
		InputNavigation inavi = new InputNavigation(this);
		inputPanel.add(inavi.getPanel(), BorderLayout.WEST);
		inputPanel.add(iform.getPanel(), BorderLayout.CENTER);
		
		return inputPanel;
	}
	
	private JComponent createNeuronPanel() {
		
		NeuronDetail ndet = new NeuronDetail(this);
		NeuronList nlist = new NeuronList(this);
		neuronPanel.add(nlist.getPanel(), BorderLayout.WEST);
		neuronPanel.add(ndet.getPanel(), BorderLayout.CENTER);
		
		return neuronPanel;
		
	}
	
/*	private void loadKNN() {
		
		//JPanel areaPanel = new JPanel(new BorderLayout());
		
		NeuronList nl = new NeuronList(this);
		NeuronDetail nd = new NeuronDetail(this);	
		
		mainPanel.add(nl.getPanel(), BorderLayout.WEST);
		mainPanel.add(nd.getPanel(), BorderLayout.CENTER);
		
	}
	
	private void loadNeuron() {
		
		
		
	}*/
	
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
