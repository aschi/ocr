package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.forms.HistoryForm;
import ch.zhaw.ocr.gui.forms.InputForm;
import ch.zhaw.ocr.gui.forms.NeuronDetail;
import ch.zhaw.ocr.gui.lists.NeuronList;

public class MainGui {
	
	public final static String KNNPANEL = "Card with KNN Panel";
	public final static String TEXTPANEL = "Card with Text Panel";
	public final static String HISTORYPANEL = "Card with History Panel";
	
	private JFrame frame;
	private JPanel inputPanel;
	private JPanel neuronPanel;
	private JPanel historyPanel;
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
        
        neuronPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new BorderLayout());
        historyPanel = new JPanel(new BorderLayout());
        
        cards = new JPanel(new CardLayout());
        cards.add(createInputPanel(), TEXTPANEL);
        cards.add(createNeuronPanel(), KNNPANEL);
        cards.add(createHistoryPanel(), HISTORYPANEL);
        
        frame.setContentPane(cards);
        createMenuBar();
        
        frame.setVisible(true);

		
	}
	
    public void selectOverview(String panelSelection){
        CardLayout cl = (CardLayout) cards.getLayout();
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
	
	public JComponent createHistoryPanel() {
		
		HistoryForm hform = new HistoryForm(this);
		InputNavigation inavi = new InputNavigation(this);
		historyPanel.add(inavi.getPanel(), BorderLayout.WEST);
		historyPanel.add(hform.getPanel(), BorderLayout.CENTER);
		
		return historyPanel;
		
	}
	
	
    private void createMenuBar(){
    	
        JMenuBar menuBar = new JMenuBar();
        
        frame.setJMenuBar(menuBar);
        
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");
        
        JMenuItem knn = new JMenuItem("Switch to KNN");
        JMenuItem text = new JMenuItem("Switch to Text");
        
        final JButton switchView = new JButton("Switch to KNN");  
        
        switchView.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if (switchView.getText() == "Switch to KNN") {
			
				selectOverview(KNNPANEL);
                switchView.setText("Switch to Text");
                
			}
				else if (switchView.getText() == "Switch to Text") {
					selectOverview(TEXTPANEL);
					switchView.setText("Switch to KNN");
				}
			}
        	
        } 
        )
        ;
        
        
        
        menuBar.add(file);
        menuBar.add(switchView);
        
        view.add(knn);
        view.add(text);
        
    }

}
