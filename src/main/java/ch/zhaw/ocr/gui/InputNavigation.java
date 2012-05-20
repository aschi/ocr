package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import ch.zhaw.ocr.gui.forms.SpringUtilities;


public class InputNavigation {
	private JPanel panel;
	private MainGui gui;
	private JTree tree;
	private DefaultMutableTreeNode top;
	
    public InputNavigation(MainGui gui) {
        this.gui = gui;        
        createPanel();
    }
	
	public InputNavigation() {
		createPanel();
	}
	
	private void createPanel() {
		panel = new JPanel(new BorderLayout());
        //panel.add(loadLists(), BorderLayout.CENTER);
        panel.add(loadImageTree(), BorderLayout.CENTER);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	private JComponent loadLists() {
		
		JPanel areaPanel = new JPanel(new SpringLayout());
		
		JLabel listImg = new JLabel("List of used Images:");
		JLabel listTxt = new JLabel("List of used Texts:");
		
		areaPanel.add(listImg);
		areaPanel.add(listTxt);
		
        SpringUtilities.makeCompactGrid(areaPanel, 2, 1, 10, 10, 10, 20);
    	
        return areaPanel;
		
	}
	
	private JTree loadImageTree() {
		
	    top = new DefaultMutableTreeNode("List of used Images:");
	    tree = new JTree(top);
	    getFileFromDir();
	    
	    tree.scrollPathToVisible(new TreePath(top.getLastLeaf().getPath()));
	    
	    return tree;
	}
	
	private void getFileFromDir() {
        File actual = new File("/home/ildril");
        for( File f : actual.listFiles()){
            top.add(new DefaultMutableTreeNode(f.getName()));
            System.out.println(f.getName());
        }
	}

}
