package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.zhaw.ocr.gui.tabs.DictionaryTab;
import ch.zhaw.ocr.gui.tabs.HistoryTab;
import ch.zhaw.ocr.gui.tabs.InputTab;
import ch.zhaw.ocr.gui.tabs.NnConsoleTab;
import ch.zhaw.ocr.textRecognition.Ocr;

/**
 * MainGui creates the UI Window / manages functionality (OCR)
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class MainGui {
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel inputPanel;
	private JPanel knnPanel;
	private JPanel historyPanel;
	private JPanel dictionaryPanel;
	private Ocr ocr;
	private HistoryTab hForm;
	private DictionaryTab dForm;
	private NnConsoleTab nnConsole;
	private StringBuffer consoleText;
	
	/**
	 * Constructor for MainGui. Needs an ocr instance to be created.
	 * @param ocr OCR Instance to be used.
	 */
    public MainGui(Ocr ocr){
        this.ocr = ocr;
        consoleText = new StringBuffer();
        createTabbedPane();
    }

	private void createTabbedPane() {
		frame = new JFrame("OCR");
		frame.setSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen
        
        knnPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new BorderLayout());
        historyPanel = new JPanel(new BorderLayout());
        dictionaryPanel = new JPanel(new BorderLayout());
        
        createTabs();
        
        frame.setVisible(true);
	}
		
	/**
	 * Get OCR instance
	 * @return ocr
	 */
	public Ocr getOcr(){
		return ocr;
	}
	
	/**
	 * Get console text instance
	 * @return console text
	 */
	public StringBuffer getConsoleText() {
		return consoleText;
	}
	
	/**
	 * Set conosle text
	 * @param new consoleText
	 */
	public void setConsoleText(StringBuffer consoleText) {
		this.consoleText = consoleText;
	}
	
	private JComponent createInputPanel(){
		InputTab iform = new InputTab(this);
		inputPanel.add(iform.getPanel(), BorderLayout.CENTER);
		
		return inputPanel;
	}
	
	private JComponent createKnnPanel() {
		nnConsole = new NnConsoleTab(this);
		nnConsole.generateTextArea();
		knnPanel.add(nnConsole.getPanel(), BorderLayout.CENTER);
		
		return knnPanel;
	}
	
	private JComponent createHistoryPanel() {
		hForm = new HistoryTab(this);
		historyPanel.add(hForm.getPanel(), BorderLayout.CENTER);
		
		return historyPanel;
	}
	
	private JComponent createDictionaryPanel() {
		dForm = new DictionaryTab(this);
		dictionaryPanel.add(dForm.getPanel(), BorderLayout.CENTER);
		
		return dictionaryPanel;
	}
	
	/**
	 * Get history form
	 * @return history form
	 */
	public HistoryTab getHistoryForm() {
		return hForm;
		
	}
	
	/**
	 * Get NeuralNetwork Console instance
	 * @return nn console
	 */
	public NnConsoleTab getKnnPanel() {
		return nnConsole;
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
