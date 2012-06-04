package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.zhaw.ocr.gui.forms.DictionaryForm;
import ch.zhaw.ocr.gui.forms.HistoryForm;
import ch.zhaw.ocr.gui.forms.InputForm;
import ch.zhaw.ocr.gui.forms.KnnConsole;
import ch.zhaw.ocr.textRecognition.Ocr;

public class MainGui {
	
	public final static String KNNPANEL = "Card with KNN Panel";
	public final static String INPUTPANEL = "Card with Text Panel";
	public final static String HISTORYPANEL = "Card with History Panel";
	public final static String DICTIONARYPANEL = "Card with Dictionary Panel";
	
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel inputPanel;
	private JPanel knnPanel;
	private JPanel historyPanel;
	private JPanel dictionaryPanel;
	private JPanel cards;
	private Ocr ocr;
	private HistoryForm hForm;
	private DictionaryForm dForm;
	private KnnConsole knnConsole;
	private StringBuffer consoleText;
	
    public MainGui(Ocr ocr){
        this.ocr = ocr;
        consoleText = new StringBuffer();
        createTabbedPane();
    }

	private void createTabbedPane() {
		
		frame = new JFrame("OCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen
        
        knnPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new BorderLayout());
        historyPanel = new JPanel(new BorderLayout());
        dictionaryPanel = new JPanel(new BorderLayout());
        
        cards = new JPanel(new CardLayout());
        cards.add(inputPanel, INPUTPANEL);
        cards.add(knnPanel, KNNPANEL);
        cards.add(historyPanel, HISTORYPANEL);
        cards.add(dictionaryPanel, DICTIONARYPANEL);
        
        frame.setContentPane(cards);
        createTabs();
        
        frame.setVisible(true);

		
	}
	
    public void selectOverview(String panelSelection){
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, panelSelection);
    }

	
	public Ocr getOcr(){
		return ocr;
	}
	
	public StringBuffer getConsoleText() {
		return consoleText;
	}
	
	public void setConsoleText(StringBuffer consoleText) {
		this.consoleText = consoleText;
	}
	
	private JComponent createInputPanel(){
		InputForm iform = new InputForm(this);
		inputPanel.add(iform.getPanel(), BorderLayout.CENTER);
		
		return inputPanel;
	}
	
	private JComponent createKnnPanel() {
		
		
		knnConsole = new KnnConsole(this);
		knnConsole.generateTextArea();
		knnPanel.add(knnConsole.getPanel(), BorderLayout.CENTER);
		
		return knnPanel;
		
	}
	
	public JComponent createHistoryPanel() {
		
		hForm = new HistoryForm(this);
		historyPanel.add(hForm.getPanel(), BorderLayout.CENTER);
		
		return historyPanel;
		
	}
	
	public JComponent createDictionaryPanel() {
		
		dForm = new DictionaryForm(this);
		dictionaryPanel.add(dForm.getPanel(), BorderLayout.CENTER);
		
		return dictionaryPanel;
		
	}
	
	public HistoryForm getHistoryForm() {
		return hForm;
		
	}
	
	public KnnConsole getKnnPanel() {
		return knnConsole;
	}
	
	
    private void createTabs(){
    	
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Input", createInputPanel() );
		tabbedPane.addTab( "KNN Console", createKnnPanel() );
		tabbedPane.addTab( "History", createHistoryPanel() );
		tabbedPane.addTab( "Dictionary", createDictionaryPanel() );
        
        frame.add(tabbedPane);
        
    }

}
