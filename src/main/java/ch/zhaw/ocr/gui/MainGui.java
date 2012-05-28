package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.forms.DictionaryForm;
import ch.zhaw.ocr.gui.forms.HistoryForm;
import ch.zhaw.ocr.gui.forms.InputForm;
import ch.zhaw.ocr.gui.forms.NeuronDetail;
import ch.zhaw.ocr.gui.lists.NeuronList;

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
	private Ocr ocr;
	private HistoryForm hForm;
	private DictionaryForm dForm;
	
    public MainGui(Ocr ocr){
        this.ocr = ocr;
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
        
        createTabs();
        
        frame.setVisible(true);

		
	}

	
	public Ocr getOcr(){
		return ocr;
	}
	
	private JComponent createInputPanel(){
		InputForm iform = new InputForm(this);
//		InputNavigation inavi = new InputNavigation(this);
//		inputPanel.add(inavi.getPanel(), BorderLayout.WEST);
		inputPanel.add(iform.getPanel(), BorderLayout.CENTER);
		
		return inputPanel;
	}
	
	private JComponent createKnnPanel() {
		
		NeuronDetail ndet = new NeuronDetail(this);
		NeuronList nlist = new NeuronList(this);
		knnPanel.add(nlist.getPanel(), BorderLayout.WEST);
		knnPanel.add(ndet.getPanel(), BorderLayout.CENTER);
		
		return knnPanel;
		
	}
	
	public JComponent createHistoryPanel() {
		
		hForm = new HistoryForm(this);
		InputNavigation inavi = new InputNavigation(this);
		historyPanel.add(inavi.getPanel(), BorderLayout.WEST);
		historyPanel.add(hForm.getPanel(), BorderLayout.CENTER);
		
		return historyPanel;
		
	}
	
	public JComponent createDictionaryPanel() {
		
		dForm = new DictionaryForm(this);
//		InputNavigation inavi = new InputNavigation(this);
//		dictionaryPanel.add(inavi.getPanel(), BorderLayout.WEST);
		dictionaryPanel.add(dForm.getPanel(), BorderLayout.CENTER);
		
		return dictionaryPanel;
		
	}
	
	public HistoryForm getHistoryForm() {
		return hForm;
		
	}
	
	
    private void createTabs(){
    	
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Input", createInputPanel() );
		tabbedPane.addTab( "KNN", createKnnPanel() );
		tabbedPane.addTab( "History", createHistoryPanel() );
		tabbedPane.addTab( "Dictionary", createDictionaryPanel() );
        
        frame.add(tabbedPane);
        
    }

}
