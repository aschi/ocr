package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.gui.MainGui;

public class DictionaryForm {
	
	private final static int SHOWENTRIES = 500;


	private JPanel panel;
	private JPanel areaPanel;
	
	private JTable wordsText;
	private DefaultTableModel model;
	
	private JButton nextButton;
	
	private Set<String> strings;
	Vector<Vector<String>> cols;
	Vector<String> colTitles;
	
	private MainGui gui;	
	private Dictionary dic;
	private List<String> charStrings;
	private int indexOfChar = -1;
	
    public DictionaryForm(MainGui gui) {
        this.gui = gui;
        nextButton = new JButton("Show next entries");
        this.dic = gui.getOcr().getDictionary();
    	charStrings = new ArrayList<String>(Arrays.asList("a", "b", "c", "d",
				"e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        createPanel();
    }
    
    public void createPanel() {
    	
    	panel = new JPanel(new BorderLayout());
    	panel.add(getAbcPanel(), BorderLayout.NORTH);
        areaPanel = new JPanel(new SpringLayout());
        
        wordsText = new JTable(20,70);
        wordsText.setModel(model);
        
        JScrollPane scrollArea = new JScrollPane (wordsText);
        areaPanel.add(scrollArea);
        
    	
    	nextButton.addActionListener(new NextButtonListener());
    	panel.add(nextButton, BorderLayout.SOUTH);
        
        SpringUtilities.makeCompactGrid(areaPanel, 1, 1, 10, 10, 10, 20);
    	panel.add(areaPanel, BorderLayout.CENTER);
    	
    }
    
    private Vector<Vector<String>> getDicEntries(String charString) {
    	cols = new Vector<Vector<String>>();
    	colTitles = new Vector<String>();
    	colTitles.add(charString);
    	colTitles.add("Häufigkeit");
    	
    	indexOfChar = 0;
    	strings = new TreeSet<String>();
    	for (String dicEntry : dic.getDictionary().keySet()) {
    		if (dicEntry.startsWith(charString)) {
    			strings.add(dicEntry);
    		}
    	}
    	
    	if (SHOWENTRIES <= strings.size()) {
    		indexOfChar = SHOWENTRIES;
    	}
    	
    	int i = 0;
    	for (String s : strings) {
    		Vector<String> col = new Vector<String>();
    		col.add(s);
    		col.add(dic.getDictionary().get(s) + "");
    		cols.add(col);
    		i ++;
    		if (i >= SHOWENTRIES) {
    			break;
    		}
    	}
    	
    	if (i >= SHOWENTRIES) {
    		nextButton.setVisible(true);
    	} else {
    		nextButton.setVisible(false);
    	}
    	
    	
    	return cols;
    }
    
    private Vector<Vector<String>> getMoreDicEntries() {
    	
    	int i = 0;
    	boolean hasMore = false;
    	for (String s : strings) {
    		if (i > indexOfChar) {
    			Vector<String> col = new Vector<String>();
    			col.add(s);
    			col.add(dic.getDictionary().get(s) + "");
    			cols.add(col);
    		}
    		i ++;
    		
    		if (i >= indexOfChar + SHOWENTRIES) {
    			indexOfChar += SHOWENTRIES;
    			hasMore = true;
    			break;
    		}
    		
    	}
    	
    	if (!hasMore) {
    		nextButton.setVisible(false);
    	}
    	
    	return cols;
    }
    
    private JPanel getAbcPanel() {
    	JPanel abcPanel = new JPanel();
    	
    	List<JButton> buttonList = createAbcButtons();
    	int i = 0;
    	for (JButton button : buttonList) {
    		abcPanel.add(button);
    		button.addActionListener(new AbcButtonListener(i));
    		i++;
    	}
    	
    	return abcPanel;
    }
    

    public JPanel getPanel() {
    	return panel;
    }
    
    private List<JButton> createAbcButtons() {
    	List<JButton> buttonList = new ArrayList<JButton>();
    	
    	for (String s : charStrings) {
    		JButton button = new JButton(s);
    		buttonList.add(button);
    	}
    	
    	return buttonList;
    }
    
    public class AbcButtonListener implements ActionListener {
    	
    	int alphabetNumber;
    	String charString;
    	
    	public AbcButtonListener(int alphabetNumber) {
    		super();
    		this.alphabetNumber = alphabetNumber;
    		charString = charStrings.get(alphabetNumber);
    	}

		@Override
		public void actionPerformed(ActionEvent e) {

			model.setDataVector(colTitles, getDicEntries(charString));
			
		}
    	
    }
    
    public class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setDataVector(colTitles, getMoreDicEntries());
			
		}
    	
    }

}



