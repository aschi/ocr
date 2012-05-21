package ch.zhaw.ocr.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.zhaw.ocr.gui.forms.HistoryForm;
import ch.zhaw.ocr.gui.forms.SpringUtilities;
import ch.zhaw.ocr.gui.helper.TextFileFilter;


public class InputNavigation {
	
	protected static final String HISTORYPANEL = null;
	
	private JPanel panel;
	private JTree tree;
	private DefaultMutableTreeNode top;
	private MainGui gui;
	private HistoryForm form;
	
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
	    addListener();
	    return tree;
	}
	
	private void addListener() {
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					TreePath p = tree.getPathForLocation(e.getX(), e.getY());
					if (p != null ) {
						TreeNode node = (TreeNode) p.getLastPathComponent();
						File files = new File("/home/ildril");
						for (File s : files.listFiles()) {
							if (s.isFile() == true) {
							String k = node.toString();
							String st = s.getAbsolutePath();
							System.out.println("k: " + k.substring(k.lastIndexOf(0)+1, k.lastIndexOf('.')));
							System.out.println("st: " + st.substring(st.lastIndexOf('/')+1, st.lastIndexOf('.')));
							if (k.substring(k.lastIndexOf(0)+1, k.lastIndexOf('.')) == st.substring(st.lastIndexOf('/')+1, st.lastIndexOf('.'))) {
								gui.selectOverview(HISTORYPANEL);
								form.setText(st);
							}
							else {
								System.out.println("historypanel kann nicht geöffnet werden");
							}
						}
							else {
								System.out.println("no file in directory");
							}
						}

					}
					
				}
			}
			
		});
	}
	
	private void getFileFromDir() {
        File actual = new File("/home/ildril");
        for( File f : actual.listFiles()){
        	if (f.getAbsolutePath().toLowerCase().endsWith(".jpg")) {
            top.add(new DefaultMutableTreeNode(f.getName()));
        	}
        }
	}

}
